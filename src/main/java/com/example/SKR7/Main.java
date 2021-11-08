/*
负责人：白靖
 */
package com.example.SKR7;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Main extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        从cookie中拿出Login
        如果Login存在且等于1则
        拿出cookie内的uid
        调用Main_Y存储过程
        调用User_Msg存储过程
        返回给前端每个表内和该用户最近访问的条目类似的访问量最高的前五个数据的图片和名字和用户图像和Gid和用户图像和用户名
        如果Login不存在或等于0则
        调用Main_N存储过程
        返回给前端每个表内访问量最高的前五个数据的图片和名字和Gid
         */

        //调用数据库主页留言查询功能存储过程Main_Query
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BBS?serverTimezone=GMT&characterEncoding=utf-8","root","12345678");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CallableStatement sql = null;
        JSONArray jsonArray = new JSONArray();
        //获取Login
        HttpSession session = req.getSession();
        int Login = (int)session.getAttribute("Login");
        if (Login == 1){
            int Uid = (int)session.getAttribute("Uid");
            //用户图像和用户名
            try {
                //调用数据库主页留言查询功能存储过程Main_Query
                sql = con.prepareCall("{call UserMsg(?)}");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                sql.setInt(1,Uid);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            ResultSet rs = null;
            try {
                rs = sql.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JSONObject jsonObjectUserMsg = new JSONObject();
            try {
                jsonObjectUserMsg.put("Uname",rs.getString("Uname"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                jsonObjectUserMsg.put("Uimgurl",rs.getString("Uimgurl"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            jsonArray.add(jsonObjectUserMsg);
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                sql.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //用户名和用户图像路径
            for (int i = 0; i < 5; i++) {
                try {
                    //调用数据库主页留言查询功能存储过程Main_Query
                    sql = con.prepareCall("{call Main_Y(?)}");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    sql.setInt(1,Uid);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    rs = sql.executeQuery();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                //图片路径和名字和Gid
                String imgUrl = null;
                try {
                    imgUrl = rs.getString("imgUrl");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String name = null;
                try {
                    name = rs.getString("name");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                int Gid = 0;
                try {
                    Gid = rs.getInt("Gid");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                switch (i){
                    case 1:
                        JSONObject jsonObjectVideo = new JSONObject();
                        jsonObjectVideo.put("Vurl",imgUrl);
                        jsonObjectVideo.put("Vname",name);
                        jsonObjectVideo.put("Gid",Gid);
                        jsonArray.add(jsonObjectVideo);
                        break;
                    case 2:
                        JSONObject jsonObjectPainting = new JSONObject();
                        jsonObjectPainting.put("Purl",imgUrl);
                        jsonObjectPainting.put("Pname",name);
                        jsonObjectPainting.put("Gid",Gid);
                        jsonArray.add(jsonObjectPainting);
                        break;
                    case 3:
                        JSONObject jsonObjectGSetting = new JSONObject();
                        jsonObjectGSetting.put("GSurl",imgUrl);
                        jsonObjectGSetting.put("GSname",name);
                        jsonObjectGSetting.put("Gid",Gid);
                        jsonArray.add(jsonObjectGSetting);
                        break;
                    case 4:
                        JSONObject jsonObjectGMusic = new JSONObject();
                        jsonObjectGMusic.put("GMurl",imgUrl);
                        jsonObjectGMusic.put("GMname",name);
                        jsonObjectGMusic.put("Gid",Gid);
                        jsonArray.add(jsonObjectGMusic);
                        break;
                    case 5:
                        JSONObject jsonObjectGAccessories = new JSONObject();
                        jsonObjectGAccessories.put("GAimgurl",imgUrl);
                        jsonObjectGAccessories.put("GAname",name);
                        jsonObjectGAccessories.put("Gid",Gid);
                        jsonArray.add(jsonObjectGAccessories);
                        break;
                }
                //以JSON格式返回上述数据
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter writer = resp.getWriter();
                writer.println(jsonArray);
                writer.flush();
                writer.close();
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    sql.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        else {
            for (int i = 0; i < 5; i++) {
                try {
                    //调用数据库主页留言查询功能存储过程Main_Query
                    sql = con.prepareCall("{call Main_N(?)}");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rs = null;
                try {
                    rs = sql.executeQuery();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                //图片路径和名字和Gid
                String imgUrl = null;
                try {
                    imgUrl = rs.getString("imgUrl");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String name = null;
                try {
                    name = rs.getString("name");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                int Gid = 0;
                try {
                    Gid = rs.getInt("Gid");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                switch (i) {
                    case 1:
                        JSONObject jsonObjectVideo = new JSONObject();
                        jsonObjectVideo.put("Vurl", imgUrl);
                        jsonObjectVideo.put("Vname", name);
                        jsonObjectVideo.put("Gid", Gid);
                        jsonArray.add(jsonObjectVideo);
                        break;
                    case 2:
                        JSONObject jsonObjectPainting = new JSONObject();
                        jsonObjectPainting.put("Purl", imgUrl);
                        jsonObjectPainting.put("Pname", name);
                        jsonObjectPainting.put("Gid", Gid);
                        jsonArray.add(jsonObjectPainting);
                        break;
                    case 3:
                        JSONObject jsonObjectGSetting = new JSONObject();
                        jsonObjectGSetting.put("GSurl", imgUrl);
                        jsonObjectGSetting.put("GSname", name);
                        jsonObjectGSetting.put("Gid", Gid);
                        jsonArray.add(jsonObjectGSetting);
                        break;
                    case 4:
                        JSONObject jsonObjectGMusic = new JSONObject();
                        jsonObjectGMusic.put("GMurl", imgUrl);
                        jsonObjectGMusic.put("GMname", name);
                        jsonObjectGMusic.put("Gid", Gid);
                        jsonArray.add(jsonObjectGMusic);
                        break;
                    case 5:
                        JSONObject jsonObjectGAccessories = new JSONObject();
                        jsonObjectGAccessories.put("GAimgurl", imgUrl);
                        jsonObjectGAccessories.put("GAname", name);
                        jsonObjectGAccessories.put("Gid", Gid);
                        jsonArray.add(jsonObjectGAccessories);
                        break;
                }
                //以JSON格式返回上述数据
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter writer = resp.getWriter();
                writer.println(jsonArray);
                writer.flush();
                writer.close();
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    sql.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

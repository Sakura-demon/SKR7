/*
负责人：白靖
 */
package com.example.SKR7;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Main")
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SKR7?serverTimezone=GMT&characterEncoding=utf-8","root","12345678");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CallableStatement sql = null;
        JSONArray jsonArray = new JSONArray();
        HttpSession session = req.getSession();
        //获取Login
        int Login = 1;
        if (session.getAttribute("Login") == null){
            Login = 0;
        }
        else {
            Login = Integer.parseInt(session.getAttribute("Login").toString());
        }
        JSONObject jsonObjectLogin = new JSONObject();
        jsonObjectLogin.put("Login",Login);
        jsonArray.add(jsonObjectLogin);

        /*
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
                        jsonObjectVideo.put("Login",0);
                        jsonArray.add(jsonObjectVideo);
                        break;
                    case 2:
                        JSONObject jsonObjectPainting = new JSONObject();
                        jsonObjectPainting.put("Purl", imgUrl);
                        jsonObjectPainting.put("Pname", name);
                        jsonObjectPainting.put("Gid", Gid);
                        jsonObjectPainting.put("Login",0);
                        jsonArray.add(jsonObjectPainting);
                        break;
                    case 3:
                        JSONObject jsonObjectGSetting = new JSONObject();
                        jsonObjectGSetting.put("GSurl", imgUrl);
                        jsonObjectGSetting.put("GSname", name);
                        jsonObjectGSetting.put("Gid", Gid);
                        jsonObjectGSetting.put("Login",0);
                        jsonArray.add(jsonObjectGSetting);
                        break;
                    case 4:
                        JSONObject jsonObjectGMusic = new JSONObject();
                        jsonObjectGMusic.put("GMurl", imgUrl);
                        jsonObjectGMusic.put("GMname", name);
                        jsonObjectGMusic.put("Gid", Gid);
                        jsonObjectGMusic.put("Login",0);
                        jsonArray.add(jsonObjectGMusic);
                        break;
                    case 5:
                        JSONObject jsonObjectGAccessories = new JSONObject();
                        jsonObjectGAccessories.put("GAimgurl", imgUrl);
                        jsonObjectGAccessories.put("GAname", name);
                        jsonObjectGAccessories.put("Gid", Gid);
                        jsonObjectGAccessories.put("Login",0);
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
         */
        ResultSet rs = null;
        for (int i = 1; i <= 5; i++) {
            try {
                //调用数据库主页留言查询功能存储过程Main_Query
                sql = con.prepareCall("{call Main_N(?)}");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                sql.setInt(1,i);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                rs = sql.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            switch (i) {
                case 1:
                    JSONArray jsonArray1 = new JSONArray();
                    while (true){
                        try {
                            if (!rs.next()) break;
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        JSONObject jsonObjectVideo = new JSONObject();
                        try {
                            jsonObjectVideo.put("Vurl", rs.getString("Vurl"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            jsonObjectVideo.put("Vname", rs.getString("Vname"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            jsonObjectVideo.put("Gid", rs.getInt("Gid"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        jsonArray1.add(jsonObjectVideo);
                    }
                    jsonArray.add(jsonArray1);
                    break;
                case 2:
                    JSONArray jsonArray2 = new JSONArray();
                    while (true){
                        try {
                            if (!rs.next()) break;
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        JSONObject jsonObjectPainting = new JSONObject();
                        try {
                            jsonObjectPainting.put("Purl", rs.getString("Purl"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            jsonObjectPainting.put("Pname", rs.getString("Pname"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            jsonObjectPainting.put("Gid", rs.getInt("Gid"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        jsonArray2.add(jsonObjectPainting);
                    }
                    jsonArray.add(jsonArray2);
                    break;
                case 3:
                    JSONArray jsonArray3 = new JSONArray();
                    while (true){
                        try {
                            if (!rs.next()) break;
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        JSONObject jsonObjectGSetting = new JSONObject();
                        try {
                            jsonObjectGSetting.put("GSimgurl", rs.getString("GSimgurl"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            jsonObjectGSetting.put("GSname", rs.getString("GSname"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            jsonObjectGSetting.put("Gid", rs.getInt("Gid"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        jsonArray3.add(jsonObjectGSetting);
                    }
                    jsonArray.add(jsonArray3);
                    break;
                case 4:
                    JSONArray jsonArray4 = new JSONArray();
                    while (true){
                        try {
                            if (!rs.next()) break;
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        JSONObject jsonObjectGMusic = new JSONObject();
                        try {
                            jsonObjectGMusic.put("GMimgurl", rs.getString("GMimgurl"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            jsonObjectGMusic.put("GMname", rs.getString("GMname"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            jsonObjectGMusic.put("Gid", rs.getInt("Gid"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        jsonArray4.add(jsonObjectGMusic);
                    }
                    jsonArray.add(jsonArray4);
                    break;
                case 5:
                    JSONArray jsonArray5 = new JSONArray();
                    while (true)
                    {
                        try {
                            if (!rs.next()) break;
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        JSONObject jsonObjectGAccessories = new JSONObject();
                        try {
                            jsonObjectGAccessories.put("GAimgurl", rs.getString("GAimgurl"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            jsonObjectGAccessories.put("GAname", rs.getString("GAname"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            jsonObjectGAccessories.put("Gid", rs.getInt("Gid"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        jsonArray5.add(jsonObjectGAccessories);
                    }
                    jsonArray.add(jsonArray5);
                    break;
            }
        }
        if (Login == 1){
            int Uid = Integer.parseInt(session.getAttribute("Uid").toString());
            //用户图像和用户名
            try {
                //调用数据库主页留言查询功能存储过程UserMsg
                sql = con.prepareCall("{call UserMsg(?)}");
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
            try {
                rs.next();
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
            /*
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
             */
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
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

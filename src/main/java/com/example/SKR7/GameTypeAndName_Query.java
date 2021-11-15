/*
负责人：许慎谨
 */
package com.example.SKR7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GameTypeAndName_Query extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        ResultSet rs = null;
        String Uimg=null;
        int Uid;
        HttpSession session = req.getSession();
        int Login = (int)session.getAttribute("Login");
        if (Login == 1) {
            int Uid = (int) session.getAttribute("Uid");
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
            Uimg = rs.getString("Uimg");
            Uid = rs.getInt("Uid");

        }
        /*
        从cookie中拿出Login
        如果Login = 1则增加调用UserMsg存储过程，返回用户名和用户图像路径给前端

         */

        String Game = session.getAttribute("Game");
        if ("Game".equals(TYPE)) {
            try {
                //调用GameType_Query存储过程
                sql = con.prepareCall("{call GameType_Query(?)}");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            try {
                //调用GameName_Query存储过程
                sql = con.prepareCall("{call GameName_Query(?)}");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            sql.setInt(1,Game);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //执行sql
        rs = null;
        try {
            rs = sql.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String Gimgurl = null;
        String Gname = null;
        int Gid = null;

        //获得结果集中的用户图片路径
        Gimg = rs.getString("Gimg");
        Gname = rs.getString("Gname");
        Gid = rs.getInt("Gid");

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjectFlag = new JSONObject();
        jsonObject.put("Uimg",Uimg);
        jsonObject.put("Uid",Uid);
        jsonObject.put("Gimg",Gimg);
        jsonObject.put("Gname",Gname);
        jsonObject.put("Gid",Gid);

        jsonArray.add(jsonObjectFlag);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(jsonArray);
        writer.flush();
        writer.close();
        //关闭sql
        try {
            sql.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //关闭数据库连接
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /*
        从cookie内拿出Game
        如果Game = "TYPE"
        调用GameTYPE_Query存储过程
        返回给前端游戏名和游戏图片路径和Gid

        如果Game = "NAME"
        调用GameName_Query存储过程
        返回给前端游戏名和游戏图片路径和Gid
         */
    }
}

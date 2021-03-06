/*
负责人：许慎谨
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

@WebServlet("/GameTypeAndName_Query")
public class GameTypeAndName_Query extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        从cookie中拿出Login
        如果Login = 1则增加调用UserMsg存储过程，返回用户名和用户图像路径给前端
         */
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
        //获取Login
        ResultSet rs = null;
        HttpSession session = req.getSession();
        String Game = (String) session.getAttribute("Game");
        if (Game.equals("TYPE"))
        {
            try {
                //调用GameType_Query存储过程
                sql = con.prepareCall("{call GameType_Query(?)}");
                sql.setString(1,(String) session.getAttribute("Gtype"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else
        {
            try {
                //调用GameName_Query存储过程
                sql = con.prepareCall("{call GameName_Query(?)}");
                sql.setString(1,(String) session.getAttribute("Gname"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //执行sql
        rs = null;
        try {
            rs = sql.executeQuery();
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
        JSONArray jsonArray1 = new JSONArray();
        while (true){
            try {
                if (!rs.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                JSONObject jsonObjectG = new JSONObject();
                jsonObjectG.put("Gname",rs.getString("Gname"));
                jsonObjectG.put("Gimgurl",rs.getString("Gimgurl"));
                jsonObjectG.put("Gid",rs.getInt("Gid"));
                jsonArray1.add(jsonObjectG);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("len",jsonArray1.size());
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);
        jsonArray.add(jsonArray1);
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
    }
}

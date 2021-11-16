/*
负责人：吴海波
 */
package com.example.SKR7;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Game_Query extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        从cookie内拿出Gid
        调用Game_Query存储过程
        返回图片路径和名字和Gid
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

       Cookie[] cookies = req.getCookies();
       Cookie cookie = cookies[0];
       int Gid = Integer.parseInt(cookie.getValue());
        String Pwd = req.getParameter("Pwd");
        try {
            //调用数据库主页留言查询功能存储过程UserMsg
            sql = con.prepareCall("{call Game_Query(?)}");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            sql.setInt(1,Gid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ResultSet rs = null;

        String imageUrl = null;
        String imageName = null;
        try {
            rs = sql.executeQuery();
            imageUrl = rs.getString("Uimgurl");
            imageName = rs.getString("Uname");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            int result = rs.getInt(0);
            if (result == 1) {
                JSONArray jsonArray = new JSONArray();
                JSONObject gidJsonObject = new JSONObject();
                JSONObject urlJsonObject = new JSONObject();
                JSONObject nameJsonObject = new JSONObject();
                gidJsonObject.put("Gid", Gid);
                urlJsonObject.put("Uimgurl", imageUrl);
                nameJsonObject.put("Uname", imageName);
                jsonArray.add(gidJsonObject);
                jsonArray.add(urlJsonObject);
                jsonArray.add(nameJsonObject);
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter writer = resp.getWriter();
                writer.println(jsonArray);
                writer.flush();
                writer.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

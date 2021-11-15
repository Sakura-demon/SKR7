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

public class SignIn extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*接收到前端发来的用户名和密码
        调用SignIn存储过程
        如果返回0
        返回0给前端
        如果返回1
        保存Uid和Login = 1到cookie里
        返回1给前端
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

        int Uid = Integer.parseInt(req.getParameter("Uid"));
        String Pwd = req.getParameter("Pwd");
        try {
            //调用数据库主页留言查询功能存储过程UserMsg
            sql = con.prepareCall("{call SignIn(?)}");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            sql.setInt(1,Uid);
            sql.setString(2, Pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = sql.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            int result = rs.getInt(0);
            if (result == 1) {
                JSONArray jsonArray = new JSONArray();
                JSONObject loginJsonObject = new JSONObject();
                loginJsonObject.put("Login", 1);
                jsonArray.add(loginJsonObject);
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter writer = resp.getWriter();
                writer.println(jsonArray);
                writer.flush();
                writer.close();
                Cookie loginCookie = new Cookie("Login", "1");
                Cookie uidCookie = new Cookie("Uid", Integer.toString(Uid));
                resp.addCookie(loginCookie);
                resp.addCookie(uidCookie);
            } else {
                Cookie loginCookie = new Cookie("Login", "0");
                resp.addCookie(loginCookie);
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

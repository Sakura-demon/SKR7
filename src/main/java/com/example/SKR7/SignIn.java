/*
负责人：吴海波
 */
package com.example.SKR7;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class SignIn extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        /*接收到前端发来的用 户名和密码
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
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SKR7?serverTimezone=GMT&characterEncoding=utf-8","root","12345678");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CallableStatement sql = null;
        try {
            //调用登陆功能存错过程SignIn
            sql = conn.prepareCall("{call SignIn()}");
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
            if (rs != null) {
                String result = rs.getString("result");
                String Uid = rs.getString("Uid");
                Cookie resultCookie = new Cookie("Login", "0");
                resultCookie.setPath("/");
                resultCookie.setComment("登陆结果");
                resultCookie.setMaxAge(24*60*60);
                resp.addCookie(resultCookie);
                if (result.equals("0")) {
                    resp.sendRedirect("/SignAndRegister.html");
                } else {
                    Cookie nameCookie = new Cookie("Uid", Uid);
                    nameCookie.setPath("/");
                    nameCookie.setComment("用户id");
                    nameCookie.setMaxAge(24*60*60);
                    resp.addCookie(resultCookie);
                    resp.addCookie(nameCookie);
                    resp.sendRedirect("/Main.html");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

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

public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        /*
        接收前端发来的用户名和密码
        调用Register存储过程
        如果返回1
        保存和Login = 1到cookie里
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

        JSONArray jsonArray = new JSONArray();
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("Uid");
        String psw = (String) session.getAttribute("psw");
        try {
            sql = conn.prepareCall("{call Register(?, ?)}");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ResultSet rs = null;
        JSONObject jsonObjectPswChange = new JSONObject();
        try {
            sql.setString(1, userId);
            sql.setString(2, psw);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            rs = sql.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (rs.getString("result").equals("1")) {
                Cookie resultCookie = new Cookie("Login", "1");
                resultCookie.setPath("/");
                resultCookie.setComment("登陆结果");
                resultCookie.setMaxAge(24*60*60);
                resp.addCookie(resultCookie);
                jsonObjectPswChange.put("result", 1);
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
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(jsonArray);
        writer.flush();
        writer.close();
        resp.sendRedirect("/Main.html");
    }
}

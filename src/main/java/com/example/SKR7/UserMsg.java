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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class UserMsg extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        /*
        从cookie中拿出Uid
        调用UserMsg存储过程
        返回用户名和用户图像路径
         */
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
        //获取Login
        HttpSession session = req.getSession();
        int Uid = (int)session.getAttribute("Uid");
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
        //以JSON格式返回上述数据
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObjectUserMsg);
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

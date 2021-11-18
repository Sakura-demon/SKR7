/*
负责人：雷雯雯
 */
package com.example.SKR7;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Password_Change")
public class Password_Change extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        接收前端传来的旧密码和新密码和确认密码
        判断新密码和确认密码是否一致
        如果不一致返回-1
        如果一致调用Password_Change存储过程
        返回调用返回结果给前端
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
        ResultSet rs = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        HttpSession session = req.getSession();
        int userId = Integer.parseInt(session.getAttribute("Uid").toString());
        String old_psw = req.getParameter("old_psw");
        String new_psw = req.getParameter("new_psw");
        String re_psw = req.getParameter("re_psw");
        if (new_psw.equals(re_psw)) {
            try {
                sql = conn.prepareCall("{call Password_Change(?, ?, ?,?)}");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                sql.setInt(1, userId);
                sql.setString(2, old_psw);
                sql.setString(3,new_psw);
                sql.registerOutParameter(4,Types.INTEGER);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                rs = sql.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                jsonObject.put("flag",sql.getInt("flag"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            jsonArray.add(jsonObject);

        } else {
            jsonObject.put("flag", -1);
            jsonArray.add(jsonObject);
        }
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
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

/*
负责人：雷雯雯
 */
package com.example.SKR7;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Password_Change extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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

        JSONArray jsonArray = new JSONArray();
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("Uid");
        String old_psw = (String) session.getAttribute("old_psw");
        String new_psw = (String) session.getAttribute("new_psw");
        String re_psw = (String) session.getAttribute("re_psw");
        if (new_psw.equals(re_psw)) {
            try {
                sql = conn.prepareCall("{call Password_Change(?, ?, ?)}");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            ResultSet rs = null;
            JSONObject jsonObjectPswChange = new JSONObject();
            try {
                sql.setString(1, userId);
                sql.setString(2, old_psw);
                sql.setString(3,new_psw);
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
                    jsonObjectPswChange.put("result", 1);
                } else {
                    jsonObjectPswChange.put("result", 0);
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
        } else {
            JSONObject jsonObjectPswChange = new JSONObject();
            jsonObjectPswChange.put("result", -1);
            jsonArray.add(jsonObjectPswChange);
        }

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(jsonArray);
        writer.flush();
        writer.close();
    }
}

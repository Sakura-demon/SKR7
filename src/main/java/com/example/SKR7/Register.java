/*
负责人：吴海波
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

@WebServlet("/Register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        接收前端发来的用户名和密码
        调用Register存储过程
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

        String Uname = req.getParameter("Uname");
        String Pwd = req.getParameter("Upassword");
        String Uimgurl = "Img/樱花招福御守.png";
        try {
            //调用数据库主页留言查询功能存储过程UserMsg
            sql = con.prepareCall("{call Register(?, ?,?,?)}");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            sql.setString(1,Uname);
            sql.setString(2, Pwd);
            sql.setString(3,Uimgurl);
            sql.registerOutParameter(4,Types.INTEGER);
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
            rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int flag = 0;
        try {
            flag = sql.getInt(4);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        HttpSession session = req.getSession();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        if (flag == 1)
        {
            session.setAttribute("Login",1);
            try {
                session.setAttribute("Uid",rs.getString("Uid"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            jsonObject.put("flag",1);
            jsonArray.add(jsonObject);
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.println(jsonArray);
            writer.flush();
            writer.close();
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

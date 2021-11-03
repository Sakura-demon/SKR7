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

public class Main extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        从cookie中拿出Login
        如果Login不存在或等于0则
        调用Main_N存储过程
        返回给前端每个表内访问量最高的前五个数据的图片和名字
        如果Login存在则
        拿出cookie内的uid
        调用Main_Y存储过程
        调用User_Msg存储过程
        返回给前端每个表内和该用户最近访问的条目类似的访问量最高的前五个数据的图片和名字和用户图像
         */

        //调用数据库主页留言查询功能存储过程Main_Query
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
        try {
            //调用数据库主页留言查询功能存储过程Main_Query
            sql = con.prepareCall("{call Main_Query()}");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = sql.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int Page = Integer.parseInt(req.getParameter("Page"));
        HttpSession session = req.getSession();
        JSONArray jsonArray = new JSONArray();

        int curPage = (int) session.getAttribute("curPage");
        JSONObject jsonObjectcurPage = new JSONObject();
        jsonObjectcurPage.put("curPage",curPage);
        jsonArray.add(jsonObjectcurPage);

        //以JSON格式返回上述数据
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

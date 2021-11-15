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

public class Logout extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        /*
        将cookie中的Login = 1设置为0
        返回1
         */

        //创建新的cookie
        Cookie cookie = new Cookie("Login", "1");
        //设置cookie有效时间
        cookie.setMaxAge(24*60*60);
        //添加cookie描述
        cookie.setComment("Login");
        //设置cookie有效路径为全局
        cookie.setPath("/");
        //将cookie写入浏览器
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.addCookie(cookie);

        //返回首页
        resp.sendRedirect("./Main.html");
    }
}
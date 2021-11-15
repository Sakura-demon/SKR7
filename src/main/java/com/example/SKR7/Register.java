/*
负责人：吴海波
 */
package com.example.SKR7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    }
}

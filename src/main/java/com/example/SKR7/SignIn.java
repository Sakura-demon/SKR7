/*
负责人：吴海波
 */
package com.example.SKR7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    }
}

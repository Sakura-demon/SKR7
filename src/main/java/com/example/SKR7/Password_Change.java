/*
负责人：雷雯雯
 */
package com.example.SKR7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    }
}

/*
负责人：许慎谨
 */
package com.example.SKR7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class History_Query extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        从cookie中拿出Uid
        接收前端发来的flag
        调用History_Query存储过程
        返回图片路径和名字和Vid/Pid/GSid/GMid/GAid给前端
         */
    }
}

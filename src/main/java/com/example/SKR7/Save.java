/*
负责人：雷雯雯
 */
package com.example.SKR7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Save extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        /*
        接收前端发来的新用户图像和新用户名
        将发来的用户图像下载保存到webapp的Img文件夹下，并获取新用户图像路径
        调用Save存储过程
        返回调用结果给前端
         */
    }
}
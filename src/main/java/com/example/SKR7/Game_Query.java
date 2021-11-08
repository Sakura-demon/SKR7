/*
负责人：吴海波
 */
package com.example.SKR7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Game_Query extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        /*
        从cookie内拿出Gid
        调用Game_Query存储过程
        返回图片路径和名字和Gid
         */
    }
}

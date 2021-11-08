/*
负责人：许慎谨
 */
package com.example.SKR7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GameTypeAndName_Query extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        /*
        从cookie中拿出Login
        如果Login = 1则增加调用UserMsg存储过程，返回用户名和用户图像路径给前端

        从cookie内拿出Game

        如果Game = "TYPE"
        调用GameTYPE_Query存储过程
        返回给前端游戏名和游戏图片路径和Gid

        如果Game = "NAME"
        调用GameName_Query存储过程
        返回给前端游戏名和游戏图片路径和Gid
         */
    }
}

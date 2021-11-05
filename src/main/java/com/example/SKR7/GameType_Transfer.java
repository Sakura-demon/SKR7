/*
负责人：许慎谨
 */
package com.example.SKR7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GameType_Transfer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String type = req.getParameter("name");
        Cookie c1 = new Cookie("Gid",name);
        Cookie c2 = new Cookie("Game","TYPE");
        resp.addCookie(c1);
        resp.addCookie(c2);
        /*
        接收前端发来游戏名
        将其保存到cookie内
        保存Game = “TYPE”到cookie中
        返回1给前端
         */
    }
}

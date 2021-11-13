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
        String type = req.getParameter("Gname");
        HttpSession session = req.getSession();
        session.setAttribute("Gid",name);
        session.setAttribute("Game","TYPE");
        /*
        接收前端发来游戏名
        将其保存到cookie内
        保存Game = “TYPE”到cookie中
         */
    }
}

/*
负责人：许慎谨
 */
package com.example.SKR7;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/GameType_Transfer")
public class GameType_Transfer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String Gtype = req.getParameter("Gtype");
        HttpSession session = req.getSession();
        session.setAttribute("Gtype",Gtype);
        session.setAttribute("Game","TYPE");

        /*
        接收前端发来游戏名
        将其保存到cookie内
        保存Game = “TYPE”到cookie中
        返回1给前端
         */
    }
}

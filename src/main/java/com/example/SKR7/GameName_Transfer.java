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

@WebServlet("/GameName_Transfer")
public class GameName_Transfer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String Gname = req.getParameter("Gname");
        HttpSession session = req.getSession();
        session.setAttribute("Gname",Gname);
        session.setAttribute("Game","NAME");
        /*
        接收前端发来游戏名
        将其保存到cookie内
        保存Game = “NAME”到cookie中
        返回1给前端
         */
    }
}

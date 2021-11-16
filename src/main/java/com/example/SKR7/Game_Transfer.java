/*
负责人：白靖
 */
package com.example.SKR7;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Game_Transfer")
public class Game_Transfer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        接收前端发来的Gid
        将Gid保存到cookie里
         */
        String Gid = req.getParameter("Gid");
        HttpSession session = req.getSession();
        session.setAttribute("Gid",Gid);
    }
}

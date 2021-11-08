/*
负责人：白靖
 */
package com.example.SKR7;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Game_Transfer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        /*
        接收前端发来的Gid
        将Gid保存到cookie里
        返回1给前端
         */
        int Gid = Integer.parseInt(req.getParameter("Gid"));
        HttpSession session = req.getSession();
        session.setAttribute("Gid",Gid);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjectFlag = new JSONObject();
        jsonObjectFlag.put("flag",1);
        jsonArray.add(jsonObjectFlag);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(jsonArray);
        writer.flush();
        writer.close();
    }
}

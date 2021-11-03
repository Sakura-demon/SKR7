/*
负责人：许慎谨
 */
package com.example.SKR7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class History_Delete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        /*
        判断Vid/Pid/GSid/GMid/GAid的存在性
        存在哪个id则接收哪个id
        如果Vid存在调用History_Delete存储过程时flag = 1
        如果Pid存在调用History_Delete存储过程时flag = 2
        如果GSid存在调用History_Delete存储过程时flag = 3
        如果GMid存在调用History_Delete存储过程时flag = 4
        如果GAid存在调用History_Delete存储过程时flag = 5
        返回调用结果给前端
         */
    }
}

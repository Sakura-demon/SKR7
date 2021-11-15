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
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BBS?serverTimezone=GMT&characterEncoding=utf-8","root","12345678");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CallableStatement sql = null;
        ResultSet rs = null;
        try {
            //调用History_Query存储过程
            sql = con.prepareCall("{call History_Delete(?,?,?)}");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        HttpSession session = req.getSession();
        int Uid = (int)session.getAttribute("Uid");
        //(int)session.getAttribute("Login");
        if(session.getAttribute("Vid")!=null){
            try {
                //传入Uid
                sql.setInt(1,Uid);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                sql.setInt(2,(int)session.getAttribute("Vid"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            int flag = 1;
            try {
                //传入flag
                sql.setInt(3,flag);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            rs = null;
            try {
                rs = sql.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(session.getAttribute("Pid")!=null){
            try {
                //传入Uid
                sql.setInt(1,Uid);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                sql.setInt(2,(int)session.getAttribute("Pid"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            int flag = 2;
            try {
                //传入flag
                sql.setInt(3,flag);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            rs = null;
            try {
                rs = sql.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(session.getAttribute("GSid")!=null){
            try {
                //传入Uid
                sql.setInt(1,Uid);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                sql.setInt(2,(int)session.getAttribute("GSid"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            int flag = 3;
            try {
                //传入flag
                sql.setInt(3,flag);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            rs = null;
            try {
                rs = sql.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(session.getAttribute("GMid")!=null){
            try {
                //传入Uid
                sql.setInt(1,Uid);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                sql.setInt(2,(int)session.getAttribute("GMid"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            int flag = 4;
            try {
                //传入flag
                sql.setInt(3,flag);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            rs = null;
            try {
                rs = sql.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(session.getAttribute("GAid")!=null){
            try {
                //传入Uid
                sql.setInt(1,Uid);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                sql.setInt(2,(int)session.getAttribute("GAid"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            int flag = 5;
            try {
                //传入flag
                sql.setInt(3,flag);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            rs = null;
            try {
                rs = sql.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            sql.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

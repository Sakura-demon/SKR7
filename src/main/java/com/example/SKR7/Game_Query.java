/*
负责人：吴海波
 */
package com.example.SKR7;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Game_Query")
public class Game_Query extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        从cookie内拿出Gid
        调用Game_Query存储过程
        返回图片路径和名字和不同id
         */
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SKR7?serverTimezone=GMT&characterEncoding=utf-8","root","12345678");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CallableStatement sql = null;
        ResultSet rs = null;
        HttpSession session = req.getSession();
        JSONArray jsonArray = new JSONArray();
        int Gid = Integer.parseInt(session.getAttribute("Gid").toString());
        for (int i = 0; i < 6; i++) {
            try {
                //调用数据库主页留言查询功能存储过程UserMsg
                sql = con.prepareCall("{call Game_Query(?,?)}");
                sql.setInt(1,Gid);
                sql.setInt(2,i);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String Gimgurl;
            String Vurl;
            String Vname;
            int Vid;
            String Purl;
            String Pname;
            int Pid;
            String GSimgurl;
            String GSname;
            int GSid;
            String GMimgurl;
            String GMname;
            int GMid;
            String GAimgurl;
            String GAname;
            int GAid;
            try {
                rs = sql.executeQuery();
                switch (i)
                {
                    case 0:
                        JSONObject jsonObjectG = new JSONObject();
                        while (rs.next()){
                            Gimgurl = rs.getString("Gimgurl");
                            jsonObjectG.put("Gimgurl",Gimgurl);
                        }
                        jsonArray.add(jsonObjectG);
                        break;
                    case 1:
                        JSONArray jsonArray1 = new JSONArray();
                        JSONObject jsonObjectV = new JSONObject();
                        while (rs.next()){
                            Vurl = rs.getString("Vurl");
                            Vname = rs.getString("Vname");
                            Vid = rs.getInt("Vid");
                            jsonObjectV.put("Vurl",Vurl);
                            jsonObjectV.put("Vname",Vname);
                            jsonObjectV.put("Vid",Vid);
                            jsonArray1.add(jsonObjectV);
                        }
                        jsonArray.add(jsonArray1);
                        break;
                    case 2:
                        JSONArray jsonArray2 = new JSONArray();
                        JSONObject jsonObjectP = new JSONObject();
                        while (rs.next()){
                            Purl = rs.getString("Purl");
                            Pname = rs.getString("Pname");
                            Pid = rs.getInt("Pid");
                            jsonObjectP.put("Purl",Purl);
                            jsonObjectP.put("Pname",Pname);
                            jsonObjectP.put("Pid",Pid);
                            jsonArray2.add(jsonObjectP);
                        }
                        jsonArray.add(jsonArray2);
                        break;
                    case 3:
                        JSONArray jsonArray3 = new JSONArray();
                        JSONObject jsonObjectGS = new JSONObject();
                        while (rs.next()){
                            GSimgurl = rs.getString("GSimgurl");
                            GSname = rs.getString("GSname");
                            GSid = rs.getInt("GSid");
                            jsonObjectGS.put("GSimgurl",GSimgurl);
                            jsonObjectGS.put("GSname",GSname);
                            jsonObjectGS.put("GSid",GSid);
                            jsonArray3.add(jsonObjectGS);
                        }
                        jsonArray.add(jsonArray3);
                        break;
                    case 4:
                        JSONArray jsonArray4 = new JSONArray();
                        JSONObject jsonObjectGM = new JSONObject();
                        while (rs.next()){
                            GMimgurl = rs.getString("GMimgurl");
                            GMname = rs.getString("GMname");
                            GMid = rs.getInt("GMid");
                            jsonObjectGM.put("GMimgurl",GMimgurl);
                            jsonObjectGM.put("GMname",GMname);
                            jsonObjectGM.put("GMid",GMid);
                            jsonArray4.add(jsonObjectGM);
                        }
                        jsonArray.add(jsonArray4);
                        break;
                    case 5:
                        JSONArray jsonArray5 = new JSONArray();
                        JSONObject jsonObjectGA = new JSONObject();
                        while (rs.next()){
                            GAimgurl = rs.getString("GAimgurl");
                            GAname = rs.getString("GAname");
                            GAid = rs.getInt("GAid");
                            jsonObjectGA.put("GAimgurl",GAimgurl);
                            jsonObjectGA.put("GAname",GAname);
                            jsonObjectGA.put("GAid",GAid);
                            jsonArray5.add(jsonObjectGA);
                        }
                        jsonArray.add(jsonArray5);
                        break;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(jsonArray);
        writer.flush();
        writer.close();
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
    }
}

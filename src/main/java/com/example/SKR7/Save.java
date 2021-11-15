/*
负责人：雷雯雯
 */
package com.example.SKR7;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Save extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        接收前端发来的新用户图像和新用户名
        将发来的用户图像下载保存到webapp的Img文件夹下，并获取新用户图像路径
        调用Save存储过程
        返回调用结果给前端
         */
        String Uname = req.getParameter("Uname");
        String stream = req.getParameter("Image");
        String imagePath = "/Img/" + Uname + ".png";
        File image = new File(imagePath);
        byte[] buff = Base64.decode(stream);

        File file = null;

        FileOutputStream fileOutputStream = null;

        try {

            file = File.createTempFile("tmp", ".jpg");

            fileOutputStream = new FileOutputStream(file);

            fileOutputStream.write(buff);

        } catch (Exception e) {

        }

        if (fileOutputStream != null) {

            try {

                fileOutputStream.close();

            } catch (Exception e) {

            }

        }
        try {

            if (image.exists()) //目录下有文件就删除

                image.delete();

            if (!image.getParentFile().exists())   //文件夹不存在创建文件夹

                image.getParentFile().mkdirs();

            FileInputStream fin = new FileInputStream(image);

            FileOutputStream fout = new FileOutputStream(image);

            byte[] myByteArray = new byte[(int) file.length()];

            int n = 0;

            while ((n = fin.read(myByteArray)) != -1)

                fout.write(myByteArray, 0, n);

            fout.close();

            fin.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BBS?serverTimezone=GMT&characterEncoding=utf-8", "root", "12345678");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CallableStatement sql = null;
        try {
            sql = con.prepareCall("{call Save(?)}");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ResultSet rs = null;
        try {
            sql.setString(1, imagePath);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = sql.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            String flag = rs.getString(0);
            JSONArray jsonArray = new JSONArray();
            JSONObject resultJSONObject = new JSONObject();
            resultJSONObject.put("Result", flag);
            jsonArray.add(resultJSONObject);
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.println(jsonArray);
            writer.flush();
            writer.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
    }
}

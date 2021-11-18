///*
//负责人：许慎谨
// */
//package com.example.SKR7;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class History_Query extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        int Uid = (int)session.getAttribute("Uid");
//        int flag = req.getParameter("flag");
//        String Uimg,name;
//        int id;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Connection con = null;
//        try {
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BBS?serverTimezone=GMT&characterEncoding=utf-8","root","12345678");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        CallableStatement sql = null;
//        try {
//            //调用History_Query存储过程
//            sql = con.prepareCall("{call History_Query(?,?)}");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            //传入Uid
//            sql.setInt(1,Uid);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            //传入flag
//            sql.setInt(2,flag);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        ResultSet rs = null;
//        try {
//            rs = sql.executeQuery();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            rs.next();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        Uimg = rs.getString("Uimg");
//        name = rs.getString("name");
//        id = rs.getInt("id");
//
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter writer = resp.getWriter();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("Uimg",Uimg);
//        jsonObject.put("name",name);
//        jsonObject.put("id",id);
//        writer.println(jsonObject);
//        writer.flush();
//        writer.close();
//        try {
//            rs.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            sql.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            con.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        /*
//        从cookie中拿出Uid
//        接收前端发来的flag
//        调用History_Query存储过程
//        返回图片路径和名字和Vid/Pid/GSid/GMid/GAid给前端
//         */
//    }
//}

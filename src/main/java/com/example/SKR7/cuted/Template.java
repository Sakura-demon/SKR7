package com.example.SKR7.cuted;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Template")
public class Template extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //连接数据库
        /*
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
         */
        //获取Cookie中的数据
        /*
        HttpSession session = req.getSession();
        int Uid = (int)session.getAttribute("Uid");
        */
        //获取前端发来的数据
        String flag = req.getParameter("flag");
        System.out.println(flag);
        /*
        //调用数据库主页留言查询功能存储过程UserMsg
        try {
            //调用数据库主页留言查询功能存储过程Main_Query
            sql = con.prepareCall("{call UserMsg(?,?)}");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //假设第一个参数为输入，则用下面这些代码输入参数
        try {
            sql.setInt(1,Uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //假设第二个参数为输出（不是数据库返回表而是特别用来检测某些情况用的特殊变量），则用下面这些代码注册输出参数，然后在执行sql后在用一个变量拿出数据来
        try {
            //注册输出参数
            sql.registerOutParameter(2,Types.INTEGER);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //执行sql
        ResultSet rs = null;
        try {
            rs = sql.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ////获取输出参数返回结果
        int flag = 0;
        try {
            //获取输出参数返回结果
            flag = sql.getInt("flag");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //假设只有一行结果集，获取结果集里的数据
        imgUrl = rs.getString("imgUrl");
        name = rs.getString("name");
        Gid = rs.getInt("Gid");
        //多行结果集可使用这个遍历结果集，然后逐行取值设置
        rs.next();
         */
        //将数据放入JSON格式对象里，并放入JSON格式数组
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imgUrl","Img/樱花招福御守.png");
        jsonObject.put("Gname","樱花");
        jsonObject.put("Gid",1);
        jsonArray.add(jsonObject);
        //以JSON格式返回上述数据
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(jsonArray);
        writer.flush();
        writer.close();
        /*
        //关闭结果集
        try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        //关闭sql
        try {
                sql.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        //关闭数据库连接
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
         */
    }
}

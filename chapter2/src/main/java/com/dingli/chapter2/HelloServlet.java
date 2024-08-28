package com.dingli.chapter2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
*@author: xbronze
*@date: 2024-08-28 07:58
*@description: 基于注解方式实现的Servlet
*/
@WebServlet(urlPatterns = "/HelloServlet")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        // 设置HttpServletResponse使用utf-8编码
//        resp.setCharacterEncoding("UTF-8");
//        // 通知浏览器使用utf-8解码
//        resp.setHeader("Content-Type","text/html;charset=utf-8");

        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().println("基于注解实现的Servlet程序！");
    }
}

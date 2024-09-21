package com.dingli.chapter5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xbronze
 * @date: 2024-09-21 16:43
 * @description: TODO
 */
@WebServlet(urlPatterns = "/MyServlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("username", "zhangsan");
        req.setAttribute("password", "123");
        req.getRequestDispatcher("/myjsp.jsp").forward(req, resp);
    }
}

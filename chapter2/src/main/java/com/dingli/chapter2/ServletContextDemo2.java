package com.dingli.chapter2;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: xbronze
 * @date: 2024-08-28 09:32
 * @description: TODO
 */
@WebServlet(urlPatterns = "/ServletContextDemo2")
public class ServletContextDemo2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        ServletContext servletContext = this.getServletContext();
        out.println(servletContext.getAttribute("data"));
        out.close();
    }
}

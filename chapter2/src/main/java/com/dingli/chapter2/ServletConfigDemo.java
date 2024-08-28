package com.dingli.chapter2;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xbronze
 * @date: 2024-08-28 09:15
 * @description: ServletConfig对象
 */
@WebServlet(urlPatterns = "/ServletConfigDemo", initParams = {
        @WebInitParam(name = "country", value = "China"),
        @WebInitParam(name = "city", value = "Beijing")
})
public class ServletConfigDemo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        ServletConfig servletConfig = this.getServletConfig();
        resp.getWriter().println("Country: " + servletConfig.getInitParameter("country") + ", City: " + servletConfig.getInitParameter("city"));
    }
}

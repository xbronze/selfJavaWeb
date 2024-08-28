package com.dingli.chapter2;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @author: xbronze
 * @date: 2024-08-28 09:21
 * @description: ServletContext对象
 */
@WebServlet(urlPatterns = "/ServletContextDemo1")
public class ServletContextDemo1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        ServletContext servletContext = this.getServletContext();
//        resp.getWriter().println("姓名：" + servletContext.getInitParameter("name") + "，工作：" + servletContext.getInitParameter("work"));
        PrintWriter out = resp.getWriter();
        Enumeration<String> initParamNames = servletContext.getInitParameterNames();
        while (initParamNames.hasMoreElements()) {
            String paramName = initParamNames.nextElement();
            String paramValue = servletContext.getInitParameter(paramName);
            out.println("paramName：" + paramName + "，paramValue：" + paramValue + "<br/>");
        }
        out.close();

        // Servlet之间共享数据
        servletContext.setAttribute("data", "此data参数的内容，是在ServletContextDemo1中设置的！");
    }
}

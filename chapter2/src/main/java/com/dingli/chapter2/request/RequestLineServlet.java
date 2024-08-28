package com.dingli.chapter2.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: xbronze
 * @date: 2024-08-28 10:51
 * @description: 获取请求行中相关信息
 */
@WebServlet(urlPatterns = "/RequestLineServlet")
public class RequestLineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        // 获取请求行的相关信息
        out.println("getMethod : " + req.getMethod() + "<br />");
        out.println("getRequestURI : " + req.getRequestURI() + "<br />");
        out.println("getQueryString:" + req.getQueryString() + "<br />");
        out.println("getProtocol : " + req.getProtocol() + "<br />");
        out.println("getContextPath:" + req.getContextPath() + "<br />");
        out.println("getPathInfo : " + req.getPathInfo() + "<br />");
        out.println("getPathTranslated : " + req.getPathTranslated() + "<br />");
        out.println("getServletPath:" + req.getServletPath() + "<br />");
        out.println("getRemoteAddr : " + req.getRemoteAddr() + "<br />");
        out.println("getRemoteHost : " + req.getRemoteHost() + "<br />");
        out.println("getRemotePort : " + req.getRemotePort() + "<br />");
        out.println("getLocalAddr : " + req.getLocalAddr() + "<br />");
        out.println("getLocalName : " + req.getLocalName() + "<br />");
        out.println("getLocalPort : " + req.getLocalPort() + "<br />");
        out.println("getServerName : " + req.getServerName() + "<br />");
        out.println("getServerPort : " + req.getServerPort() + "<br />");
        out.println("getScheme : " + req.getScheme() + "<br />");
        out.println("getRequestURL : " + req.getRequestURL() + "<br />");
    }
}

package com.dingli.chapter2.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * @author: xbronze
 * @date: 2024-08-28 11:12
 * @description: 获取请求参数-form表单
 */
@WebServlet(urlPatterns = "/RequestParamsServlet")
public class RequestParamsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 对前端form传入的参数设置UTF-8编码，避免中文乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        out.println("用户名：" + username + "，密码" + password + "<br/>");
        String[] hobbys = req.getParameterValues("hobby");
        if (hobbys != null && hobbys.length > 0) {
            out.println("爱好：");
            for (String hobby : hobbys) {
                out.println(hobby + " ");
            }
        }
    }
}

package com.dingli.chapter2experiment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xbronze
 * @date: 2024-08-28 16:37
 * @description: 模拟用户登录
 */
@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 简单的验证逻辑，实际应连接数据库
        if ("admin".equals(username) && "123456".equals(password)) {
            // 登录成功，将用户ID存入ServletContext
            this.getServletContext().setAttribute("userId", username);
            // 请求转发到欢迎页面
            req.getRequestDispatcher("/WelcomeServlet").forward(req, resp);
        } else {
            // 登录失败，重定向回登录页面
            resp.sendRedirect(this.getServletContext().getContextPath() + "/login.jsp?error=true");
        }
    }
}

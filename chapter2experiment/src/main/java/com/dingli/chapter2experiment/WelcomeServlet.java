package com.dingli.chapter2experiment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xbronze
 * @date: 2024-08-28 16:41
 * @description: 模拟用户登录
 */
@WebServlet(urlPatterns = "/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 检查用户是否已登录
        String userId = (String) this.getServletContext().getAttribute("userId");
        if (userId == null) {
            // 用户未登录，重定向到登录页面
            resp.sendRedirect("/login.jsp");
        } else {
            // 用户已登录，显示欢迎页面
            req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
        }
    }
}

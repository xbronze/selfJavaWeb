package com.dingli.chapter3experiment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xbronze
 * @date: 2024-08-28 17:42
 * @description: 模拟用户登录
 */
@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if ("admin".equals(username) && "123456".equals(password)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(this.getServletContext().getContextPath() + "/IndexServlet");
        } else {
            resp.getWriter().println("用户名或密码错误，登录失败！");
        }
    }
}

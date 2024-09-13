package com.dingli.chapter2experiment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xbronze
 * @date: 2024-08-28 16:41
 * @description: 模拟用户登录
 */
public class WelcomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 记录用户登录历史
        System.out.println("用户：" + this.getServletContext().getAttribute("username") + " 已成功登录！");
        // 转发到欢迎页面
        req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
    }
}

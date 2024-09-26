package com.dingli.chapter3experiment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author: xbronze
 * @date: 2024-08-28 17:37
 * @description: 用户登录成功
 */
@WebServlet(urlPatterns = "/IndexServlet")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.getWriter().println("您还没有登录，请<a href='" + this.getServletContext().getContextPath() + "/login.jsp'>登录</a>");
        } else {
            resp.getWriter().println("您已登录，欢迎你，" + user.getUsername() + "！");
            resp.getWriter().println("<a href='" + this.getServletContext().getContextPath() + "/LogoutServlet'>退出</a>");
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge(60); // 保存一分钟
            cookie.setPath("/chapter3experiment");
            resp.addCookie(cookie);
        }
    }


}

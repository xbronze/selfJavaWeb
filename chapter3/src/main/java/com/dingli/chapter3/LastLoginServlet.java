package com.dingli.chapter3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: xbronze
 * @date: 2024-09-26 15:20
 * @description: TODO
 */
@WebServlet(urlPatterns = "/LastLoginServlet")
public class LastLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        boolean existsLastLogin = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date());
        Cookie[] cookies = req.getCookies();
        if(cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("lastLogin")) {
                    String lastLoginTime = cookie.getValue();
                    existsLastLogin = true;
                    cookie.setValue(URLEncoder.encode(currentTime, "UTF-8"));
                    cookie.setMaxAge(60);
                    resp.addCookie(cookie);
                    resp.getWriter().println("欢迎回来，你上一次登录的时间是：" + URLDecoder.decode(lastLoginTime, "UTF-8"));
                    break;
                }
            }
        }

        if (!existsLastLogin) {
            Cookie cookie = new Cookie("lastLogin", URLEncoder.encode(currentTime, "UTF-8"));
            cookie.setMaxAge(60);
            resp.addCookie(cookie);
            resp.getWriter().println("欢迎登录");
        }

    }
}

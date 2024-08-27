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
 * @date: 2024-08-27 17:52
 * @description: TODO
 */
@WebServlet(urlPatterns = "/LastAccessServlet")
public class LastAccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //指定服务器输出内容的编码方式UTF-8，防止发生乱码
        resp.setContentType("text/html; charset=utf-8");
        // 获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(date);
        // 标识当前用户是否已经访问过系统， 默认没有访问过
        boolean existsCookie = false;
        // 获取所有cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                // 判断cookie是否设置过上次登录时间，如果设置过，就代表用户之前已经访问过
                if ("lastAccess".equals(cookie.getName())) {
                    // 如果匹配到lastAccess，代表用户已经访问过系统，existsCookie标识设置为true
                    existsCookie = true;
                    /**
                     * 至于为什么需要URL编码，因为在使用 SimpleDateFormat 时间格式化对象时，
                     * 由于"yyyy年MM月dd日 HH:mm:ss"中的" "( 空格 )参数异常，Cookie不支持空格，所以可以删掉空格，
                     * 但是为了美观，需要URL编码和解码实现数据的存储与转换。
                     */
                    resp.getWriter().write("欢迎回来，您上次访问的时间为：" + URLDecoder.decode(cookie.getValue(), "UTF-8"));
                    // 更新访问时间为当前时间
                    cookie.setValue(URLEncoder.encode(currentTime, "UTF-8"));
                    cookie.setMaxAge(60*60);  // 一个小时的有效期
                    resp.addCookie(cookie);
                    break;
                }
            }
        }
        // cookies为空，或者existsCookie标识为false，则代表用户从未访问过系统
        if (cookies == null || cookies.length == 0 || !existsCookie) {
            Cookie cookie = new Cookie("lastAccess", URLEncoder.encode(currentTime, "UTF-8"));
            cookie.setMaxAge(60*60);
            resp.addCookie(cookie);
            resp.getWriter().write("您好，欢迎您首次访问");
        }
    }
}

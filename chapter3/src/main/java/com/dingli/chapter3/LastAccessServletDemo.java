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
 * 教材示例
 */
@WebServlet(urlPatterns = "/LastAccessServletDemo")
public class LastAccessServletDemo extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //指定服务器输出内容的编码方式UTF-8，防止发生乱码
        response.setContentType("text/html;charset=utf-8");
        //获取所有cookie
        Cookie[] cookies = request.getCookies();
        //定义flag的boolean变量，用于判断cookies是否为空
        boolean flag = false;
        //遍历cookie数组
        if (cookies.length > 0 && cookies != null) {
            for (Cookie cookie : cookies) {
                //获取cookie的名称
                String name = cookie.getName();
                //判断名称是否是lastTime
                if ("lastTime".equals(name)) {
                    //有该cookie不是第一次访问
                    flag = true;
                    //响应数据
                    //获取cookie的value时间
                    String value = cookie.getValue();
                    System.out.println("解码前：" + value);
                    //URL解码
                    value = URLDecoder.decode(value, "utf-8");
                    System.out.println("解码后：" + value);
                    response.getWriter().write("欢迎回来，您上次访问时间为:"+value);
                    //设置cookie的value
                    //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
                    Date date = new Date();
                    SimpleDateFormat timesdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
                    String str_time = timesdf.format(date);
                    System.out.println("编码前：" + str_time);
                    //URL编码
                    str_time = URLEncoder.encode(str_time, "utf-8");
                    System.out.println("编码后：" + str_time);
                    cookie.setValue(str_time);
                    //设置cookie存活时间
                    cookie.setMaxAge(60 * 60 * 24 * 30);    //一个月
                    //加入当前cookie请求时间
                    response.addCookie(cookie);
                    break;
                }
            }
            //如果cookies中没有时间，也就是没有访问过
            if (cookies == null || cookies.length == 0 || flag == false) {
                //设置cookie的value
                //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss ");
                String str_date = sdf.format(date);
                System.out.println("编码前：" + str_date);
                //URL编码
                str_date = URLEncoder.encode(str_date, "utf-8");
                System.out.println("编码后：" + str_date);
                Cookie cookie = new Cookie("lastTime", str_date);
                //设置cookie存活时间
                cookie.setMaxAge(60 * 60 * 24 * 30);//一个月
                response.addCookie(cookie);
                response.getWriter().write("您好，欢迎您首次访问");
            }
        }

    }
}

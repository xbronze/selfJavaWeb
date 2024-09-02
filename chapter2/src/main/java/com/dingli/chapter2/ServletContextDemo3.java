package com.dingli.chapter2;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @author: xbronze
 * @date: 2024-09-02 11:46
 * @description: 读取Web应用下的资源文件
 */
@WebServlet(urlPatterns = "/ServletContextDemo3")
public class ServletContextDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        // 1.通过getResourceAsStream(String path)方式
        // 返回映射到某个资源文件的InputStream输入流对象。参数path传递规则和getResource()方法完全一致
        InputStream in = servletContext.getResourceAsStream("/WEB-INF/classes/config.properties");

        // 2.通过getRealPath(String path)
//        String path = servletContext.getRealPath("/WEB-INF/classes/config.properties");
//        InputStream in = new FileInputStream(path);

        Properties properties = new Properties();
        properties.load(in);
        PrintWriter out = resp.getWriter();
        out.println("country:" + properties.get("country") + "  city:" + properties.get("city"));
    }
}

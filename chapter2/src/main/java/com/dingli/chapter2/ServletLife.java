package com.dingli.chapter2;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xbronze
 * @date: 2024-08-28 09:00
 * @description: Servlet的生命周期
 */
@WebServlet(urlPatterns = "/ServletLife")
public class ServletLife extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("ServletLife executed...");
    }

    /**
     * 在Servlet的整个生命周期中，destroy()方法也只被调用一次。
     * 需要注意的是，Servlet对象一旦创建就会驻留在内存中等待客户端的访问，直到服务器关闭，或web应用被移除出容器时Servlet对象才会销毁。
     */
    @Override
    public void destroy() {
        System.out.println("destroy() executed...");
        super.destroy();
    }

    /**
     * init()方法只在第一次访问时执行，service()方法则在每次访问时都被执行。
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init() executed...");
        super.init(config);
    }

    /**
     * 对于Servlet的每一次访问请求，Servlet容器都会调用一次Servlet的service()方法，并且创建新的ServletRequest和ServletResponse对象
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service() executed...");
        super.service(req, res);
    }
}

package com.dingli.chapter2.forward;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xbronze
 * @date: 2024-08-28 10:59
 * @description: 请求转发
 */
@WebServlet(urlPatterns = "/RequestForwardServlet")
public class RequestForwardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        req.setAttribute("username", "张三");
        // 注意：getRequestDispatcher方法参数中的路径，不用包含 contextPath 的虚拟路径
        // 本示例中，路径地址是"/ResultServlet"，而不用包含虚拟路径 "/chapter2/ResultServlet"
        req.getRequestDispatcher("/ResultServlet").forward(req, resp);
    }
}

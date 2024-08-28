package com.dingli.chapter2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author: xbronze
 * @date: 2024-08-28 10:35
 * @description: HttpServletResponse发送响应消息体的两种方式 1.getOutputStream()  2.getWriter()
 */
@WebServlet(urlPatterns = "/PrintServlet")
public class PrintServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = "Hello world!";

        // getWriter()方式
//        PrintWriter writer = resp.getWriter();
//        writer.println(result);

        // getOutputStream()方式
        OutputStream os = resp.getOutputStream();
        os.write(result.getBytes());
    }
}

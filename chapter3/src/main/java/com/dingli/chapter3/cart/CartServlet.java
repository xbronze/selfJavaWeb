package com.dingli.chapter3.cart;

import com.dingli.chapter3.cart.entity.Cake;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-08-27 19:24
 * @description: TODO
 */
@WebServlet(urlPatterns = "/CartServlet")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        if (session.getAttribute("cart") == null) {
            out.write("对不起，您没有购买过任何蛋糕<br>");
        } else {
            out.write("您购买的蛋糕有：<br/>");
            List<Cake> list = (List<Cake>) session.getAttribute("cart");
            for (Cake cake : list) {
                out.write(cake.getName() + "<br/>");
            }
        }
    }
}

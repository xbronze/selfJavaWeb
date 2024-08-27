package com.dingli.chapter3.cart;

import com.dingli.chapter3.cart.entity.Cake;
import com.dingli.chapter3.cart.entity.CakeDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * @author: xbronze
 * @date: 2024-08-27 19:13
 * @description: TODO
 */
@WebServlet(urlPatterns = "/ListCakeServlet")
public class ListCakeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        Collection<Cake> cakes = CakeDB.getAll();
        out.write("本站提供的蛋糕有： <br/>");
        for (Cake cake : cakes) {
            out.write(cake.getName() + "<a href='PurchaseServlet?id="+ cake.getId() +"'>点击购买</a><br/>");
        }
    }
}

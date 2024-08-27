package com.dingli.chapter3.cart;

import com.dingli.chapter3.cart.entity.Cake;
import com.dingli.chapter3.cart.entity.CakeDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-08-27 19:19
 * @description: TODO
 */
@WebServlet(urlPatterns = "/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        String id = req.getParameter("id");
        Cake cake = CakeDB.getCake(id);

        HttpSession session = req.getSession();
        if (session.getAttribute("cart") == null) {
            List<Cake> list = new ArrayList<Cake>();
            list.add(cake);
            session.setAttribute("cart", list);
        } else {
            List<Cake> list = (List<Cake>) session.getAttribute("cart");
            if (!list.contains(cake)) {
                list.add(cake); // 防止重复添加
                session.setAttribute("cart", list);
            }
        }
        // 选择商品后，跳转已购买蛋糕的页面
        resp.sendRedirect(this.getServletContext().getContextPath() + "/CartServlet");
    }
}

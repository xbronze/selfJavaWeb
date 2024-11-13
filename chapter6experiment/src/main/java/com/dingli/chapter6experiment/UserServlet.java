package com.dingli.chapter6experiment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Alice", 25, "Female"));
        userList.add(new User("Bob", 30, "Male"));
        userList.add(new User("Charlie", 35, "Male"));

        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/user.jsp").forward(request, response);
    }
}
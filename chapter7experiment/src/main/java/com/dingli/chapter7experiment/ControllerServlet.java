package com.dingli.chapter7experiment;

import com.dingli.chapter7experiment.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xbronze
 * @date: 2024-11-20 09:23
 * @description: TODO
 */
@WebServlet(urlPatterns = "/ControllerServlet")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=UTF-8");
            // 获取用户注册时表单提交的参数信息
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            String email = request.getParameter("email");
            // 将获取的参数封装到注册表单相关的RegisterFormBean类中
            RegisterFormBean formBean = new RegisterFormBean();
            formBean.setName(name);
            formBean.setPassword(password);
            formBean.setPassword2(password2);
            formBean.setEmail(email);
            // 验证参数填写是否符合要求，不符合，转发到register.jsp重新填写，
            if (!formBean.validate()) {
                request.setAttribute("formBean", formBean);
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
            // 参数填写符合要求，则将数据封装到UserBean类中，省略...
            UserBean userBean = new UserBean(name, password, email);
            boolean b = DBUtil.getInstance().insertUser(userBean); // 调用DBUtil类
            // 如果返回为false，注册的用户已存在，重定向到register.jsp重新填写，省略
            response.getWriter().print("恭喜你注册成功，3秒钟自动跳转");
            request.getSession().setAttribute("userBean", userBean);
            response.setHeader("refresh","3;url=loginSuccess.jsp");

        }
}

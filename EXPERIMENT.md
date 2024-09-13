## 实验案例

---------

### 实验一 Java Web入门程序实践（第一章 Java Web概述）

#### 1.案例内容

首先搭建项目所需要的环境，然后通过IDEA编辑器创建Web application项目（Web项目），启动项目，让同学们直观感受一下Web项目的初步效果。项目的演示过程，可以给同学们讲解一下javax.servlet和jakarta.servlet两种依赖包的不同，以及所对应的Tomcat版本。

#### 2.案例分析

配置环境变量：JDK17、Maven、Tomcat9、IDEA

项目创建：使用IDEA编辑器，【File】—>【New Project】—>【Jakarta EE】，模板Template选择“Web application”。



### 实验二 用户登录与欢迎页面（第二章 Servlet基础）

#### 1.案例内容

通过一个实际案例来综合展示`Servlet`的两种配置方式、`ServletContext`的数据共享、`HttpServletResponse`的请求重定向（Redirect）和请求转发（Forward）的功能。以下是一个基于Java Servlet的简单案例设计，旨在帮助学生理解这些概念并动手实践。

实现一个简单的用户登录系统，使用`ServletContext`共享登录状态（如用户username）。登录成功后，通过请求转发将用户导向欢迎页面。如果用户未登录尝试直接访问欢迎页面，则通过请求重定向回到登录页面。

#### 2.案例分析

##### （1）. 环境准备

- 确保已经安装了Java开发环境（JDK）和Tomcat服务器。
- 使用 IntelliJ IDEA创建一个新的动态Web项目。

##### （2）. 创建Servlet

- **LoginServlet.java**：处理用户登录请求。
- **WelcomeServlet.java**：处理欢迎页面的请求，仅对登录用户开放。

##### （3）. 创建JSP页面

- **login.jsp**：登录表单页面。
- **welcome.jsp**：显示欢迎信息的页面，仅登录用户可见。

##### （4）. 实现LoginServlet

通过注解的方式配置LoginServlet

```java
package com.dingli.chapter2experiment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 简单的验证逻辑，实际应连接数据库
        if ("admin".equals(username) && "123456".equals(password)) {
            // 登录成功，将用户ID存入ServletContext
            this.getServletContext().setAttribute("username", username);
            // 请求转发到欢迎页面
            req.getRequestDispatcher("/welcomeServlet").forward(req, resp);
        } else {
            // 登录失败，重定向回登录页面
            resp.sendRedirect(this.getServletContext().getContextPath() + "/login.jsp?error=true");
        }
    }
}
```

##### （5）. 实现WelcomeServlet

通过XML文件的方式配置WelcomeServlet

```java
package com.dingli.chapter2experiment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WelcomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 记录用户登录历史
        System.out.println("用户：" + this.getServletContext().getAttribute("username") + " 已成功登录！");
        // 转发到欢迎页面
        req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
    }
}

```

##### （6）. 创建JSP页面

**login.jsp**

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <form action="/chapter2experiment/LoginServlet" method="post">
    Username:<input type="text" name="username"/><br/>
    Password:<input type="password" name="password"/><br/>
    <input type="submit" value="Login"/>
  </form>
</body>
</html>

```

**welcome.jsp**

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>Welcome, <%=((String)application.getAttribute("username")) %></h1>
</body>
</html>
```

注意：在JSP中使用`application`对象访问`ServletContext`。

##### （7）. 部署和测试

- 将项目部署到Tomcat服务器。
- 访问`http://localhost:8080/YourProjectName/login.jsp`进行测试。



### 实验三 Session实现购物车功能（第三章 会话与会话技术）

#### 1.案例内容

![购物车流程图](https://img.picgo.net/2024/08/28/1fd61ffde254f49109f9d9bfd3264f076850a46d74df784e.png)

图中描述的是购物车的实现流程，当用户使用浏览器访问某个网站的蛋糕列表页面时，如果购买某个蛋糕，那么首先会判断蛋糕是否存在，如果存在就加入购物车，跳转到购物车中所购买蛋糕的列表页面。否则，返回蛋糕列表页面。

#### 2.案例分析

创建封装蛋糕信息的类，在chapter3项目下新建一个名称为com.dingli.chapter3.cart.entity的包，在该包中创建一个名称为Cake的类。

创建数据库模拟类，在com.dingli.chapter3.cart.entity包中创建一个名称为CakeDB的类，该类用于模拟保存所有蛋糕的数据库。

创建Servlet，创建一个名称为ListCakeServlet的Servlet类，该Servlet用于显示所有可购买蛋糕的列表，通过单击“购买”链接，便可将指定的蛋糕添加到购物车中。

创建一个名称为PurchaseServlet的Servlet类，该类实现了两个功能，一个是将用户购买的蛋糕信息保存到Session对象中，一个是在用户购买蛋糕结束后，将页面重定向到用户已经购买的蛋糕列表。

创建一个名称为CartServlet的Servlet类，该类主要用于展示用户已经购买的蛋糕列表。

在IDEA中启动Tomcat服务器，在浏览器中输入地址“http://localhost:8080/chapter3/ListCakeServlet”访问ListCakeServlet。



### 实验三 模拟用户登录（第三章 会话与会话技术）

#### 1.案例内容

通过所学Session知识，学会如何使用Session技术实现用户登录的功能。为了使读者可以更直观的了解用户登录的流程，接下来，通过一张图来描述用户登录的流程，具体如下图所示：

![模拟用户登录流程图](https://img.picgo.net/2024/08/28/7e34de1230aed8927a545620e9c90cc20250477ee645df1d.png)

上图中描述了用户登录的整个流程，当用户访问某个网站的首界面时，首先会判断用户是否登录，如果已经登录则在首界面中显示用户登录信息，否则进入登录页面，完成用户登录功能，然后显示用户登录信息。在用户登录的情况下，如果单击用户登录界面中的“退出”时，就会注销当前用户的信息，返回首界面。

#### 2.案例分析

创建封装用户信息类，在chapter3项目的src目录下的com.dingli.chapter3.login包中编写一个名称为User的类。

创建Servlet，在com.dingli.chapter3.login包中编写IndexServlet类，该类用于显示网站的首界面。

创建Servlet，在com.dingli.chapter3.login包中编写LoginServlet，该Servlet用于显示用户登录成功后的界面。

创建Servlet，在com.dingli.chapter3.login包中编写LogoutServlet，该Servlet用于显示用户登录成功后的界面。

创建登录页面，在chapter3项目的web目录下创建一个名称为login.jsp的页面，该页面中包含用户登录表单信息。





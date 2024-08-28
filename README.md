# Java Web 程序设计



## 一、实验案例

### 实验一 Java Web入门程序实践（第一章 Java Web概述）

#### 1.案例内容

首先搭建项目所需要的环境，然后通过IDEA编辑器创建Web application项目（Web项目），启动项目，让同学们直观感受一下Web项目的初步效果。项目的演示过程，可以给同学们讲解一下javax.servlet和jakarta.servlet两种依赖包的不同，以及所对应的Tomcat版本。

#### 2.案例分析

配置环境变量：JDK17、Maven、Tomcat9、IDEA

项目创建：使用IDEA编辑器，【File】—>【New Project】—>【Jakarta EE】，模板Template选择“Web application”。



### 实验二 用户登录与欢迎页面（第二章 Servlet基础）

#### 1.案例内容

设计一节Servlet入门实验课程，我们可以通过一个实际案例来综合展示`ServletContext`的数据共享、`HttpServletResponse`的请求重定向（Redirect）和请求转发（Forward）的功能。以下是一个基于Java Servlet的简单案例设计，旨在帮助学生理解这些概念并动手实践。

实现一个简单的用户登录系统，使用`ServletContext`共享登录状态（如用户ID）。登录成功后，通过请求转发将用户导向欢迎页面。如果用户未登录尝试直接访问欢迎页面，则通过请求重定向回到登录页面。

#### 2.案例分析

##### （1）. 环境准备

- 确保已经安装了Java开发环境（JDK）和Tomcat服务器。
- 使用IDE（如Eclipse, IntelliJ IDEA等）创建一个新的动态Web项目。

##### （2）. 创建Servlet

- **LoginServlet.java**：处理用户登录请求。
- **WelcomeServlet.java**：处理欢迎页面的请求，仅对登录用户开放。

##### （3）. 创建JSP页面

- **login.jsp**：登录表单页面。
- **welcome.jsp**：显示欢迎信息的页面，仅登录用户可见。

##### （4）. 实现LoginServlet

```java
@WebServlet("/login")  
public class LoginServlet extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        String username = request.getParameter("username");  
        String password = request.getParameter("password");  
  
        // 简单的验证逻辑，实际应连接数据库  
        if ("admin".equals(username) && "123456".equals(password)) {  
            // 登录成功，将用户ID存入ServletContext  
            getServletContext().setAttribute("userId", username);  
            // 请求转发到欢迎页面  
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WelcomeServlet");  
            dispatcher.forward(request, response);  
        } else {  
            // 登录失败，重定向回登录页面  
            response.sendRedirect(this.getServletContext().getContextPath() + "login.jsp");  
        }  
    }  
}
```

##### （5）. 实现WelcomeServlet

```java
@WebServlet("/WelcomeServlet")  
public class WelcomeServlet extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // 检查用户是否已登录  
        String userId = (String) getServletContext().getAttribute("userId");  
        if (userId == null) {  
            // 用户未登录，重定向到登录页面  
            response.sendRedirect("login.jsp");  
        } else {  
            // 用户已登录，显示欢迎页面  
            RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.jsp");  
            dispatcher.forward(request, response);  
        }  
    }  
}
```

##### （6）. 创建JSP页面

**login.jsp**

```html
<form action="login" method="post">  
    Username: <input type="text" name="username"><br>  
    Password: <input type="password" name="password"><br>  
    <input type="submit" value="Login">  
</form>
```

**welcome.jsp**

```html
<html>  
<body>  
    <h1>Welcome, <%=((String)application.getAttribute("userId")) %></h1>  
</body>  
</html>
```

注意：在JSP中使用`application`对象访问`ServletContext`。

##### （7）. 部署和测试

- 将项目部署到Tomcat服务器。
- 访问`http://localhost:8080/YourProjectName/login.jsp`进行测试。

通过此实验，学生将能够理解`ServletContext`在跨Servlet间共享数据的作用，以及`HttpServletResponse`的`sendRedirect`和`RequestDispatcher`的`forward`方法在处理请求重定向和请求转发时的不同用法。



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



## 二、问题总结

### `javax.servlet` 和 `jakarta.servlet`的关系

javax.servlet 和 jakarta.servlet 是 Java Servlet API 的两个版本。

Java Servlet API 是由 Sun Microsystems（现在是 Oracle）开发和维护的，其包名以 javax.servlet 开头。从 Java EE 8 开始，Servlet API 的维护权转交给了 Eclipse Foundation 的 Jakarta EE 社区，因此，在最新的 Jakarta EE 版本中，Servlet API 的包名已经更改为 jakarta.servlet。

javax.servlet 是旧版本的包名，而 jakarta.servlet 是新版本的包名。这个变化是为了反映 Jakarta EE 作为一个独立的开源项目，并且将标准 Java 技术规范的发展移交给了 Jakarta EE 社区。

虽然包名发生了变化，但是 Servlet API 的核心功能和用法并没有大的改变。大多数代码可以无缝地迁移到 jakarta.servlet 包下，只需要修改导入语句和相应的依赖。

所以，javax.servlet 和 jakarta.servlet 的区别仅在于包名的变化，代表不同版本的 Java Servlet API，也就意味着在代码中使用javax.servlet包或者使用jakarta.servlet包，而不需要修改代码。

### 使用`jakarta.servlet`注意事项

‌Tomcat 9不支持Jakarta EE‌。

Tomcat 9是基于Java EE 8（现在是Jakarta EE 8）规范的，并且完全支持Servlet 4.0。尽管JDK 9和JDK 10在某些情况下也可以与Tomcat 9一起工作，但Tomcat 9官方推荐使用JDK 1.8或更新的长期支持（LTS）版本，如JDK 11或更高版本。此外，Tomcat 10及以后的版本实现了Jakarta EE平台规范，而Tomcat 9及之前的版本实现的是Java EE平台规范。这表明Tomcat 9版本只兼容javax包，而不支持Jakarta包。因此，在SpringBoot项目中，如果内嵌的Tomcat容器版本为9.x，那么它不支持Jakarta，只能使用javax‌。





### PrintWriter类的`write()`方法、`print()`方法、`println()`方法的不同

这三种方法都是重写了抽象类Writer里面的write方法：

1. 重载的write()方法只能输出字符（char）数组或字符串即与字符相关的数据。当使用write()输出数字时，数字会被自动转换成其ASCII对应的字符输出，而使用print()则不会进行这样的转换。

2. print()方法可以将各种类型的数据转换成字符串的形式输出，即可以接收任意类型的参数，包括Object、int等，并将它们转换成字符串形式输出到响应流。并且能够连续输出多个值，并能够在输出的数值之间自动添加空格。

3. println()方法在print()方法除了写入指定的字符串外，此方法还会在字符串后自动添加一个行分隔符，并换行。

综上所述，如果需要连续写入大量紧凑数据，write()可能更适合；print()方法因其灵活性和方便性，在输出混合类型数据及需要自动空格分隔时更为适用；而若需输出易于阅读且格式良好的文本则 println()可能是更好的选择。通过合理选择这三种方法，可以更有效地管理和控制应用程序的数据输出。

当输出内容有中文时就用write()效率较高点; 



### `response.setContentType("text/html; charset=utf-8") `会使`PrintWriter.println()`不能换行



`response.setContentType("text/html;charset=UTF-8")` 设置响应的内容类型为 HTML，并指定了字符编码为 UTF-8。这通常用于在 HTTP 响应中设置正确的内容类型和字符编码，以确保浏览器能正确显示动态生成的内容。

### 关于HTTP的重定向

HTTP1.0在介绍302时说，如果客户端发出POST请求后，收到服务端的302状态码，那么不能自动的向新的URI发送重复请求，必须跟用户确认是否该重发，因为第二次POST时，环境可能已经发生变化，POST操作会不符合用户预期。但是，很多浏览器在这种情况下都会把POST请求变为GET请求。

HTTP1.1在介绍302时说，如果客户端发出非GET、HEAD请求后，收到服务端的302状态码，那么就不能自动的向新URI发送重复请求，除非得到用户的确认。但是，很多浏览器都把302当作303处理了（注意，303是HTTP1.1才加进来的，其实从HTTP1.0进化到HTTP1.1，浏览器什么都没动），它们获取到HTTP响应报文头部的Location字段信息，并发起一个GET请求。

 从上面的介绍可以知道，HTTP1.1和HTTP1.0的302状态码意义是一样的，浏览器对它的处理也是一样的。POST方法的重定向在未询问用户的情况下就变成GET，这种不符合文档规范的问题依然存在。实践在前而文档在后，HTTP1.1把这种POST变GET的行为纳入了RFC文档：HTTP1.1新加入303和307状态码。

> 文档中规定303状态码的响应，也就是上边提到的现在浏览器对302状态码的处理：POST重定向为GET。

> HTTP1.1文档中307状态码则相当于HTTP1.0文档中的302状态码，当客户端的POST请求收到服务端307状态码响应时，需要跟用户询问是否应该在新URI上发起POST方法，也就是说，307是不会把POST转为GET的。

为兼容很多HTTP1.1之前的浏览器，服务端在需要发出303状态码时，会选择用302状态码替代；而对于307的处理，则需要在响应实体中包含信息，以便不能处理307状态码的用户有能力在新URI中发起重复请求，也就是说，把重定向的页面展示给用户，让用户去点重定向URI链接（URI现在基本就是URL）。

当前如果是现在的主流浏览器，收到307的状态会继续以post方式去访问新的URL。

在serlvlet中，需要重定的时候，我们都习惯调用response.sendRedirect来实现。

但是我们往往没有去想，这个函数底层做了什么？

其实很简单，这个函数的功能，类似于：

```java
response.setStatus(302);  
response.setHeader("Location", "URL");
```

但是302这个状态码，意味着浏览器会以get方式向新的URL发起请求。如果原先的请求是post方式的，也会因为重定向后，改成get方式。

```java
response.setStatus(307);  
response.setHeader("Location", "URL");  
```

我们可以通过在servlet中自己设置状态码和header中的location以及其他一些header参数，来实现post方法重定向后依然使用post方式向新的URL发起请求。
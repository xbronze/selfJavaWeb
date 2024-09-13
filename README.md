

## `javax.servlet` 和 `jakarta.servlet`的关系

javax.servlet 和 jakarta.servlet 是 Java Servlet API 的两个版本。

Java Servlet API 是由 Sun Microsystems（现在是 Oracle）开发和维护的，其包名以 javax.servlet 开头。从 Java EE 8 开始，Servlet API 的维护权转交给了 Eclipse Foundation 的 Jakarta EE 社区，因此，在最新的 Jakarta EE 版本中，Servlet API 的包名已经更改为 jakarta.servlet。

javax.servlet 是旧版本的包名，而 jakarta.servlet 是新版本的包名。这个变化是为了反映 Jakarta EE 作为一个独立的开源项目，并且将标准 Java 技术规范的发展移交给了 Jakarta EE 社区。

虽然包名发生了变化，但是 Servlet API 的核心功能和用法并没有大的改变。大多数代码可以无缝地迁移到 jakarta.servlet 包下，只需要修改导入语句和相应的依赖。

所以，javax.servlet 和 jakarta.servlet 的区别仅在于包名的变化，代表不同版本的 Java Servlet API，也就意味着在代码中使用javax.servlet包或者使用jakarta.servlet包，而不需要修改代码。

## 使用`jakarta.servlet`注意事项

Tomcat 9不支持Jakarta E。

Tomcat 9是基于Java EE 8（现在是Jakarta EE 8）规范的，并且完全支持Servlet 4.0。尽管JDK 9和JDK 10在某些情况下也可以与Tomcat 9一起工作，但Tomcat 9官方推荐使用JDK 1.8或更新的长期支持（LTS）版本，如JDK 11或更高版本。此外，Tomcat 10及以后的版本实现了Jakarta EE平台规范，而Tomcat 9及之前的版本实现的是Java EE平台规范。这表明Tomcat 9版本只兼容javax包，而不支持Jakarta包。因此，在SpringBoot项目中，如果内嵌的Tomcat容器版本为9.x，那么它不支持Jakarta，只能使用javax‌。



## 继承`HttpServlet`的方式属于*模板方法设计模式*

模板方法设计模式是一种行为设计模式，它定义了一个操作中的算法的框架，并允许子类在不改变算法结构的情况下重新定义某些步骤。在 Java Servlet 的上下文中，HttpServlet类就是一个典型的模板方法设计模式的实现。它提供了一个处理HTTP请求的标准流程，但允许子类根据需要覆盖特定的处理步骤。例如，service方法是一个模板方法，它首先判断请求的类型（GET、POST等），然后调用相应的处理方法（如doGet、doPost等）。如果子类需要改变请求的处理方式，它可以提供自己的实现来覆盖父类的默认行为。这种方式使得开发人员可以专注于实现特定的逻辑，而不需要关心算法的整体结构，从而提高了代码的复用性和可维护性‌。

![Servlet的运行机制](https://img.picgo.net/2024/09/11/Servletc3f4fa1bccc4fce0.jpg)

## PrintWriter类的`write()`方法、`print()`方法、`println()`方法的不同

这三种方法都是重写了抽象类Writer里面的write方法：

1. 重载的write()方法只能输出字符（char）数组或字符串即与字符相关的数据。当使用write()输出数字时，数字会被自动转换成其ASCII对应的字符输出，而使用print()则不会进行这样的转换。

2. print()方法可以将各种类型的数据转换成字符串的形式输出，即可以接收任意类型的参数，包括Object、int等，并将它们转换成字符串形式输出到响应流。并且能够连续输出多个值，并能够在输出的数值之间自动添加空格。

3. println()方法在print()方法除了写入指定的字符串外，此方法还会在字符串后自动添加一个行分隔符，并换行。

综上所述，如果需要连续写入大量紧凑数据，write()可能更适合；print()方法因其灵活性和方便性，在输出混合类型数据及需要自动空格分隔时更为适用；而若需输出易于阅读且格式良好的文本则 println()可能是更好的选择。通过合理选择这三种方法，可以更有效地管理和控制应用程序的数据输出。

当输出内容有中文时就用write()效率较高点; 



## `response.setContentType("text/html; charset=utf-8") `会使`PrintWriter.println()`不能换行

`response.setContentType("text/html;charset=UTF-8")` 设置响应的内容类型为 HTML，并指定了字符编码为 UTF-8。这通常用于在 HTTP 响应中设置正确的内容类型和字符编码，以确保浏览器能正确显示动态生成的内容。



## 关于HTTP的重定向

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



## JSP的\<%@include%>指令和\<jsp:include>动作元素的区别

\<%@include%>指令是在JSP页面转换成Java Servlet字节码文件之前，将JSP代码插入其中。它是在编译阶段执行，它包含的文件如果发生变化，必须重新将JSP页面转译成Java Servlet文件，也就是说明它是先包含，再统一编译完成的，一般用于加载页面显示后就再也不变的内容，如页眉、背景、标题等。

\<jsp:include>动作元素是在请求处理阶段执行的，它是在执行时才对包含的文件进行处理，也就是说明动作元素是先编译再包含，因此JSP页面和它所包含的文件在逻辑上和语法上是独立的，通常用来包含经常需要改动的文件，如动态时间。
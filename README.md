


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


## 在Web开发中，Session对象的主要作用以及如何实现用户状态跟踪

在Web开发中，Session对象的主要作用是用于在服务器端存储用户会话信息，以实现跨多个页面请求或访问期间跟踪用户的状态。Session提供了一种机制，允许服务器在多个请求之间保持对用户的识别和数据存储，而无需在客户端（如浏览器）存储敏感信息（如用户凭证）。

Session实现用户状态跟踪的方式：

1. 会话标识（Session ID）：当用户首次访问网站时，服务器会创建一个新的Session，并为其分配一个唯一的标识符（Session ID）。这个Session ID通常通过Cookie或URL重写的方式发送给客户端。如果通过Cookie，Session ID会被存储在用户的浏览器中，并在随后的请求中自动发送给服务器。
2. 服务器端存储：服务器会维护一个存储区域（如内存、数据库或文件系统等），用于存储与每个Session ID相关联的数据。这些数据可以包括用户认证信息、购物车内容、用户偏好设置等任何需要在多个请求之间保持的数据。
3. 请求处理：每当客户端（如浏览器）发送请求到服务器时，它都会包含Session ID（如果通过Cookie发送）。服务器使用这个Session ID来检索与之关联的Session数据，从而能够识别用户并访问其会话状态。
4. 会话管理：服务器还负责Session的管理，包括创建新Session、检索现有Session、更新Session数据以及销毁不再需要的Session（如用户注销或Session超时）。

通过这种方式，Session对象使得Web应用能够跨多个页面请求跟踪用户的状态，从而提供更加丰富和个性化的用户体验。然而，也需要注意Session的安全性和性能问题，比如防止Session劫持、合理设置Session超时时间以及优化Session存储机制等。


## JSP的\<%@include%>指令和\<jsp:include>动作元素的区别

\<%@include%>指令是在JSP页面转换成Java Servlet字节码文件之前，将JSP代码插入其中。它是在编译阶段执行，它包含的文件如果发生变化，必须重新将JSP页面转译成Java Servlet文件，也就是说明它是先包含，再统一编译完成的，一般用于加载页面显示后就再也不变的内容，如页眉、背景、标题等。

\<jsp:include>动作元素是在请求处理阶段执行的，它是在执行时才对包含的文件进行处理，也就是说明动作元素是先编译再包含，因此JSP页面和它所包含的文件在逻辑上和语法上是独立的，通常用来包含经常需要改动的文件，如动态时间。

## JSP多线程的的执行方式为什么可以极大降低对系统资源的消耗？

JSP多线程的执行方式之所以能够极大降低对系统资源的消耗，主要归因于以下几个关键因素：

#### 一、线程共享资源

- JSP文件在首次被请求时，会被服务器端的JSP引擎转换为Servlet程序，并编译为class文件，然后由Java虚拟机（JVM）解释执行。
- 对于同一个JSP文件，当多个客户端同时请求时，服务器不会为每个请求都创建一个新的Servlet实例，而是会创建多个线程来处理这些请求。这些线程共享同一个Servlet实例，从而减少了内存和资源的占用。

#### 二、多线程并发处理

- 多线程允许JSP页面在同时处理多个请求时，通过线程切换和并发执行，充分利用CPU和IO资源。
- 当一个线程在等待IO操作（如数据库查询、文件读写）完成时，CPU可以切换到其他线程继续执行，从而提高整体资源利用率和系统吞吐量。

#### 三、减少资源开销

- 使用多线程可以减少创建和销毁线程的开销。在传统的单线程模型中，每个请求都需要创建一个新的线程来处理，这会带来较大的资源消耗和性能损失。
- 而JSP多线程模型通过线程池等技术，可以重用已创建的线程，从而降低了线程创建和销毁的频率，减少了资源开销。

#### 四、高效的资源管理

- JSP引擎和Servlet容器通常会对资源进行有效的管理，包括线程池的管理、内存的优化、缓存机制等。
- 这些优化措施可以进一步提高多线程执行的效率，降低系统资源的消耗。

#### 五、实例变量与线程安全

- 需要注意的是，虽然多线程可以提高系统性能，但也带来了线程安全的问题。特别是当多个线程共享同一个实例变量时，可能会出现数据不一致的情况。
- 因此，在JSP编程中，需要谨慎使用实例变量，并尽量使用局部变量或线程安全的集合类来避免线程安全问题。

综上所述，JSP多线程的执行方式通过线程共享资源、多线程并发处理、减少资源开销、高效的资源管理和谨慎处理线程安全问题等手段，可以极大降低对系统资源的消耗，提高系统的并发性能和响应速度。


## Servlet接口的HttpServletRequest对象能直接接收json数据吗?

HttpServletRequest 对象本身并不直接支持接收 JSON 数据。HttpServletRequest 主要用于处理 HTTP 请求，包括获取请求参数、请求头、请求方法等信息。当客户端（如微信小程序）发送一个包含 JSON 数据的 POST 请求时，这些数据通常作为请求体（request body）发送。

要在 Servlet 中接收 JSON 数据，你需要做以下几件事情：

- 读取请求体：使用 HttpServletRequest 的 getReader() 方法获取一个 BufferedReader，然后读取请求体中的字符流。这通常是一个包含 JSON 数据的字符串。
- 解析 JSON 数据：使用 JSON 解析库（如 Jackson、Gson 或 org.json）将读取到的 JSON 字符串解析为 Java 对象。

下面是一个简单的示例，展示了如何在 Servlet 中接收和解析 JSON 数据：

```java
import java.io.BufferedReader;  
import java.io.IOException;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import com.fasterxml.jackson.databind.JsonNode;  
import com.fasterxml.jackson.databind.ObjectMapper;  
  
@WebServlet("/receiveJson")  
public class JsonReceiverServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // 创建一个ObjectMapper实例，用于解析JSON数据  
        ObjectMapper objectMapper = new ObjectMapper();  
  
        // 读取请求体中的JSON数据  
        BufferedReader reader = request.getReader();  
        StringBuilder sb = new StringBuilder();  
        String line;  
        while ((line = reader.readLine()) != null) {  
            sb.append(line);  
        }  
        String jsonString = sb.toString();  
  
        // 解析JSON数据为JsonNode对象（或者你可以直接解析为自定义的Java对象）  
        JsonNode jsonNode = objectMapper.readTree(jsonString);  
  
        // 从JsonNode对象中提取数据（这里只是示例，具体取决于你的JSON结构）  
        String username = jsonNode.get("username").asText();  
        String email = jsonNode.get("email").asText();  
  
        // ... 在这里处理解析后的数据  
  
        // 设置响应内容类型和状态码  
        response.setContentType("application/json");  
        response.setStatus(HttpServletResponse.SC_OK);  
  
        // 构建响应数据（这里只是示例）  
        String responseJson = "{\"message\":\"JSON data received successfully\"}";  
  
        // 写入响应数据到响应体  
        response.getWriter().write(responseJson);  
    }  
}
```

在这个示例中，我们创建了一个名为 JsonReceiverServlet 的 Servlet，它处理 POST 请求并读取请求体中的 JSON 数据。我们使用 Jackson 库来解析 JSON 数据，并将其转换为 JsonNode 对象（你也可以直接将其解析为自定义的 Java 对象）。然后，我们从 JsonNode 对象中提取数据，并构建响应数据返回给客户端。

> 请注意，你需要将 Jackson 库（或其他你选择的 JSON 解析库）添加到你的项目依赖中。如果你使用的是 Maven，你可以在 pom.xml 文件中添加相应的依赖。

## 关于BufferedReader

#### 1、什么是 BufferedReader ？

BufferedReader 是一个缓冲字符输入流，该流可以对 FileRead 进行包装，提供一个缓存数组，一次将数据按照一定规则读取到缓存区中，输入流每次读取文件数据时都需要将数据进行字符编码，而 BufferedReader 的出现，降低了输入流访问数据源的次数，将一定大小的数据一次读取到缓存区并进行字符编码，从而提高 IO 的效率。

#### 2、为什么使用 BufferedReader ？

BufferedReader 从字符输入流中读取文本，缓冲字符，以便有效地读取字符、数组和行。可以指定缓冲区大小，也可以使用默认大小（8192）。对于大多数用途，默认值足够大。
一般来说，对Reader发出的每个读请求都会导致对底层字符或字节流发出相应的读请求。因此，建议将 BufferedReader 封装在任何 read() 操作可能代价高昂的 Reader(如FileReaders和InputStreamReaders ) 周围。
例如：

```java
BufferedReader in = new BufferedReader(new FileReader("foo.in"));
```

将缓冲来自指定文件的输入。如果没有缓冲，每次调用 read() 或 readLine() 都可能导致从文件中读取字节，转换为字符，然后返回，这可能非常低效。
使用DataInputStream进行文本输入的程序可以通过使用适当的BufferedReader替换每个DataInputStream来进行本地化。

#### 3、BufferedReader 的构造函数

```java
/**
 * Creates a buffering character-input stream that uses an input buffer of
 * the specified size.
 *
 * @param  in   A Reader
 * @param  sz   Input-buffer size
 *
 * @exception  IllegalArgumentException  If {@code sz <= 0}
 */
public BufferedReader(Reader in, int sz) {
    super(in);
    if (sz <= 0)
        throw new IllegalArgumentException("Buffer size <= 0");
    this.in = in;
    cb = new char[sz];
    nextChar = nChars = 0;
}

/**
 * Creates a buffering character-input stream that uses a default-sized
 * input buffer.
 *
 * @param  in   A Reader
 */
public BufferedReader(Reader in) {
    this(in, defaultCharBufferSize);
}

```

构造函数简析：

```java
public BufferedReader(Reader in, int sz); // 创建一个BufferedRead 缓冲字符流对象，使用指定大小的缓存区。
public BufferedReader(Reader in); // 创建一个BufferedRead 缓冲字符流对象，使用默认大小（8192）的缓存区。

```

#### 4、BufferedReader 的公共方法

```java
int read();  // 读取单个字符。
int read(char[] cbuf, int off, int len);  // 将指定范围内的字符读入数组。
String readLine();  // 读取一个文本行。
boolean    ready();  // 判断此流是否可读。
void reset();  // 将流重置到最新的标记。
long skip(long n);  // 跳过字符。
void close(); // 关闭该流并释放与之关联的所有资源。
void mark(int readAheadLimit); // 标记流中的当前位置。
boolean markSupported(); // 判断此流是否支持 mark() 操作（它一定支持）。

```

## el表达式不生效问题

修改web.xml，改成新的，要与Tomcat中的web.xml的版本一致。原因是Servlet2.4版本前默认不支持el表达式。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
</web-app>

```
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/9/21
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
empty运算符：<br/>
<% request.setAttribute("user1", "");%>
<% request.setAttribute("user2", null);%>
<br/>
判断user1是否为空：${empty user1} <br/>
判断user2是否为空：${empty user2} <br/>
判断不存在的user3是否为空：${empty user3} <br/>
<hr/>

访问常量：<br/>
\${1}: ${1}   <br/> <!-- int  -->
\${"ok"}: ${"ok"}  <br/> <!-- String，要加引号 -->
\${false}: ${false}  <br/> <!-- boolean -->
\${null}: ${null}  <br/>  <!-- null -->
<hr/>

访问变量：<br/>
\${name=1}: ${name=1}  <br/> <!-- EL表达式中的变量不用先声明|定义，可直接使用。比如此处未声明 int name -->
\${name}: ${name}  <br/>  <!-- 访问变量的值 -->
<!--以Java获取值，如果变量不存在或值是null，，会报空指针异常。El表达式则不会，EL表达式会输出空串。-->
<hr/>

<% int a = 10, b = 20, c = 30;%>

算术运算符：<br/>
\${a+b}: ${a+b} <br/>
\${a-b}: ${a-b} <br/>
\${a*b}: ${a*b}  <br/>
\${a/b}: ${a/b}  或  ${a div b} <br/>
\${a%b} 或 \${a mod b}: ${a%b}  或  ${a mod b}  <br/>
<hr/>

比较运算符：<br/>
<!--返回boolean值，为避免与html标签<>冲突，可使用英文简写。-->
\${a==b}    或  \${a eq b}: ${a==b} 或 ${a eq b}    <br/>  <!--equals-->
\${a!=b}  或  \${a ne b} : ${a!=b}  或  ${a ne b}   <br/>  <!--not equals-->
\${a\<\b}  或  \${a lt b} : ${a<b}  或  ${a lt b}   <br/> <!--less than-->
\${a<=b}  或  \${a  le  b} : ${a<=b}  或  ${a  le  b}  <br/> <!--less equals-->
\${a>b}  或  \${a gt  b} : ${a>b}  或  ${a gt  b}    <br/> <!--greater than-->
\${a>=b}  或  \${a ge b} : ${a>=b}  或  ${a ge b} <br/>  <!--greater equals-->
<hr/>


逻辑运算符：<br/>
\${a && b}  或   \${a and b} : ${a && b}  或   ${a and b}<br/>
\${a || b}   或  \${a or b} : ${a || b}   或  ${a or b}<br/>
\${!a}   或   \${not  a} : ${!a}   或   ${not  a}<br/>
<hr/>

三目运算符：<br/>
\${a == 10 ? b : c}: ${a == 10 ? b : c}<br/>
<hr/>

empty运算符：<br/>
\${empty  every}: ${empty  every}   <br/>  <!--判断变量a是否为空，返回boolean值-->
<!--若变量a不存在，返回true；-->
<!--若变量a存在，但值为null，返回true；-->
<!--若变量a是集合类型（List、Set、Map），值不为null，但却是空集合，返回true。-->
<hr/>

</body>
</html>

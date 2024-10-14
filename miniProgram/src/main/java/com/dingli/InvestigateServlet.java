package com.dingli;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author: xbronze
 * @date: 2024-10-14 09:45
 * @description: 一个简单的示例，展示了如何在 Servlet 中接收和解析 JSON 数据：
 */
@WebServlet("/InvestigateServlet")
public class InvestigateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 解决控制台输出的结果中文乱码的问题
        req.setCharacterEncoding("UTF-8");

        // 创建一个ObjectMapper实例，用于解析JSON数据
        ObjectMapper objectMapper = new ObjectMapper();

        // 读取请求体中的JSON数据
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonString = sb.toString();

        // 解析JSON数据为JsonNode对象（或者你可以直接解析为自定义的Java对象）
//        JsonNode jsonNode = objectMapper.readTree(jsonString);
        // 从JsonNode对象中提取数据（这里只是示例，具体取决于你的JSON结构）
//        String username = jsonNode.get("username").asText();
//        System.out.println("username: " + username);

        // 解析为自定义的Java对象
        Investigate investigate = objectMapper.readValue(jsonString, Investigate.class);
        System.out.println(investigate);


        // ... 在这里处理解析后的数据

        // 设置响应内容类型和状态码
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);

        // 构建响应数据（这里只是示例）
        String responseJson = "{\"message\":\"JSON data received successfully\"}";

        // 写入响应数据到响应体
        resp.getWriter().write(responseJson);
    }
}

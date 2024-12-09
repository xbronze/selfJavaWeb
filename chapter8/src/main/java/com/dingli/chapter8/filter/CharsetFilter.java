package com.dingli.chapter8.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author: xbronze
 * @date: 2024-12-09 16:59
 * @description: 过滤器，统一设置请求的响应方式为 text/html， 编码方式为 utf-8
 */
@WebFilter(filterName = "charsetFilter", urlPatterns = "/hello/*")
public class CharsetFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

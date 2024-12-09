package com.dingli.chapter8.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: xbronze
 * @date: 2024-12-01 16:38
 * @description: TODO
 */
@WebFilter(filterName = "MyFilter", urlPatterns = "/hello/*")
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.getWriter().write("Hello MyFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

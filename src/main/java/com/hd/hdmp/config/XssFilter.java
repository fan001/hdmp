package com.hd.hdmp.config;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fanzhenxing
 * @create 2018/6/15 5:19 PM
 */
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
                (HttpServletRequest) request);
        System.out.println("bbbbbbb.........");
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8888");
        //response.setHeader("Access-Control-Allow-Origin", "http://zbzkboy.cn");
        servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        servletResponse.setHeader("Access-Control-Max-Age", "3600");
        servletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(xssRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

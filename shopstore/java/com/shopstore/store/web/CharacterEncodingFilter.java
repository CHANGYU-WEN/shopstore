package com.shopstore.store.web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

//在web.xml文件中配置中文過濾器
//@WebFilter(filterName = "CharacterEncodingFilter")
public class CharacterEncodingFilter implements Filter
{
    private String encoding;


    public void init(FilterConfig config) throws ServletException
    {
        encoding = config.getInitParameter("encoding");
    }

    public void destroy()
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        if(encoding!=null)
        {
            request.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }
}

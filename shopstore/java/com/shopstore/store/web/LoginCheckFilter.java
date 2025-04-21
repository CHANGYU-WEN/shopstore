package com.shopstore.store.web;


import com.shopstore.store.domain.Customer;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "LoginCheckFilter",urlPatterns={"/Controller","*.jsp"})
public class LoginCheckFilter implements Filter
{
    public void init(FilterConfig config) throws ServletException
    {

    }

    public void destroy()
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp =(HttpServletResponse) response;
        Customer customer = (Customer) req.getSession().getAttribute("customer");


        String action = req.getParameter("action");

        if(customer == null && !"login".equals(action) && !"registerInit".equals(action))
        {
            request.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

        chain.doFilter(request, response);
    }
}

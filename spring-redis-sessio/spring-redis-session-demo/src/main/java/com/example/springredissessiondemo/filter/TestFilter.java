package com.example.springredissessiondemo.filter;

import com.example.springredissessiondemo.entity.User;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2022-10-30-21:14
 */
@Component
public class TestFilter implements Filter {

    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static Random random = new Random();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        System.out.println("session is " + session + " and sesssion id is " + session.getId());
        String delete = httpServletRequest.getParameter("delete");
        if (Objects.equals("true", delete)) {
            session.invalidate();
            System.out.println("session is " + session + " and sesssion id is " + session.getId());
        }
        Object user = session.getAttribute("user");

        if (Objects.isNull(user)) {
            User build = User.builder().id(String.valueOf(atomicInteger.getAndIncrement())).name("testName" + random.nextInt(1000)).build();
            session.setAttribute("user", build);
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

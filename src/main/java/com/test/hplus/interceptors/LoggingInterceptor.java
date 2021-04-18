package com.test.hplus.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class LoggingInterceptor implements HandlerInterceptor {
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //get all cookies
        //log session id:
        //log the request path

        String sessionId = null;

        if (null != request.getCookies()) {
            for (Cookie cookie : request.getCookies()) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                }
            }
        }

        System.out.println("Incoming request data log: session: " + sessionId +
                " at " + new Date() + " for " + request.getRequestURI());
        request.setAttribute("counter", counter.incrementAndGet());
        System.out.println("Incoming request counter = " + counter);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("in post handle");
    }
}

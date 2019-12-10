package com.og.ogfrauddetect.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String userPermission = (String) request.getSession().getAttribute("userPermission");

        boolean result = userPermission.contains("7");
        if (!result) {
            response.sendRedirect("/error");
            return false;
        }
        return true;
    }
}
package com.revature.planetarium.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.revature.planetarium.exception.AuthenticationFailed;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute("user") != null){
            return true;
           } else {
            throw new AuthenticationFailed("please login before interaction with application");
           }

    }
    
}

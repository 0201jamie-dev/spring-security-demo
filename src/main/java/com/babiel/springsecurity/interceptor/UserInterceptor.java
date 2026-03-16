package com.babiel.springsecurity.interceptor;

import com.babiel.springsecurity.model.UserEntity;
import com.babiel.springsecurity.model.UserPrincipal;
import com.babiel.springsecurity.service.UserService;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    public static boolean isUserLogged() {
        try {
            return !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser");
        } catch (Exception e) {
            return false;
        }
    }

    public static Date getLastPasswordChange(Principal principal) {
        return new Date();
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        HttpSession session = request.getSession();

        if (!isUserLogged()) {
            return true;
        }

        UserPrincipal userPrincipal =  (UserPrincipal) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();

        session.setAttribute("username", userPrincipal.getUsername());

        if (userPrincipal.isPasswordExpired() && !Objects.equals(request.getRequestURI(), "/passwordExpired")) {
            response.sendRedirect("/passwordExpired");
        }

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {

        LOG.info("[postHandle][" + request + "]");
    }
}

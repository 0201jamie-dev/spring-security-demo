package com.babiel.springsecurity.interceptor;

import com.babiel.springsecurity.model.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Slf4j
public class PasswordExpiredInterceptor implements HandlerInterceptor {

    public static boolean isUserLogged() {
        try {
            return !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser");
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        if (!isUserLogged()) {
            return true;
        }

        UserPrincipal userPrincipal =  (UserPrincipal) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();

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

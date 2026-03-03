package com.babiel.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("")
    public String displayIndex() {
        return "index";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/index")
    public String displayUser() {
        return "user";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/index")
    public String displayAdmin() {
        return "admin";
    }
}

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

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("")
    public String displayIndex() {
        return "index";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user/index")
    public String displayUser() {
        return "user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/index")
    public String displayAdmin() {
        return "admin";
    }

}

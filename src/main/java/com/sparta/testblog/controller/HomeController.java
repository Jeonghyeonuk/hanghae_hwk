package com.sparta.testblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@CrossOrigin(origins = "http://localhost:8080")
public class HomeController {
    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/user/loginView")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }
}
package com.spring.golub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String main() {
        return "main_page/main.html";
    }

    @RequestMapping("/home")
    public String mainPage() {
        return "home_page/home.html";
    }
}

package com.Jun.request_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/new")
    public String run() {
        return "new";
    }

    @GetMapping("/")
    public String run2() {
        return "home";
    }
}

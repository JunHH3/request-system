package com.Jun.request_system.controller;

import com.Jun.request_system.dto.RequestForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RequestController {

    @PostMapping("/requests")
    public String run(RequestForm form) {
        System.out.println("title: " + form.getTitle());
        System.out.println("content: " + form.getContent());
        System.out.println("writer: " + form.getWriter());
        return "redirect:/";
    }
}

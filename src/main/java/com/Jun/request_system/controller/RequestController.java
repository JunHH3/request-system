package com.Jun.request_system.controller;

import com.Jun.request_system.dto.RequestForm;
import com.Jun.request_system.repository.RequestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RequestController {

    private final RequestRepository requestRepository;

    public RequestController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @PostMapping("/requests")
    public String run(RequestForm form) {
        System.out.println("title: " + form.getTitle());
        System.out.println("content: " + form.getContent());
        System.out.println("writer: " + form.getWriter());
        requestRepository.save(form.toEntity());
        return "redirect:/";
    }
}

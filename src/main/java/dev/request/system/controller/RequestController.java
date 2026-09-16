package dev.request.system.controller;

import dev.request.system.dto.RequestForm;
import dev.request.system.entity.Request;
import dev.request.system.repository.RequestRepository;
import dev.request.system.service.RequestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/requests/new")
    public String newRequest(){
        return "request/new";
    }

    @PostMapping("/requests")
    public String createRequest(RequestForm form){
        requestService.createRequest(form);
        return "redirect:/requests/new";
    }
}

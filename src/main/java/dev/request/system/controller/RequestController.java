package dev.request.system.controller;

import dev.request.system.dto.RequestForm;
import dev.request.system.entity.Request;
import dev.request.system.repository.RequestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RequestController {

    private final RequestRepository requestRepository;

    public RequestController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @GetMapping("/requests/new")
    public String newRequest(){
        return "request/new";
    }

    @PostMapping("/requests")
    public String createRequest(RequestForm form){

        Request request = new Request(form.getTitle(), form.getContent());

        requestRepository.save(request);

        return "redirect:/requests/new";
    }
}

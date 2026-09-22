package dev.request.system.controller;

import dev.request.system.dto.RequestForm;
import dev.request.system.entity.Request;
import dev.request.system.service.RequestService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/requests")
    public String listRequest(Model model){
        Iterable<Request> requests = requestService.findAllRequests();
        model.addAttribute("requests", requests);
        return "request/list";
    }

    @GetMapping("/requests/{id}")
    public String detailRequest(@PathVariable Long id, Model model) {
        Request request = requestService.findByRequestId(id);
        model.addAttribute("request", request);
        return "request/detail";
    }

    @PostMapping("/requests")
    public String createRequest(@Valid RequestForm form, BindingResult result){

        if (result.hasErrors()) {
            return "request/new";
        }

        requestService.createRequest(form);
        return "redirect:/requests/new";
    }
}

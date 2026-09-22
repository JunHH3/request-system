package dev.request.system.review;

import dev.request.system.controller.RequestController;
import dev.request.system.dto.RequestForm;
import dev.request.system.service.RequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

public class Day3Review {

    private final RequestService requestService;

    public Day3Review(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/requests")
    public String createRequest(@Valid RequestForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "request/new";
        }

        requestService.createRequest(form);
        return "redirect:/requests/new";
    }

    @WebMvcTest(RequestController.class)
    public RequestTest(){

        @Autowired private MockMvc mockMvc;
        @MockitoBean private RequestService requestService1;



    }
}


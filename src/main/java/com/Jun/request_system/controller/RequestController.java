package com.Jun.request_system.controller;

import com.Jun.request_system.dto.RequestForm;
import com.Jun.request_system.entity.Request;
import com.Jun.request_system.repository.RequestRepository;
import jdk.jshell.Snippet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ObjectInputFilter;

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

    @GetMapping("/requests")
    public String list(Model model) {
        Iterable<Request> requests = requestRepository.findAll();
        model.addAttribute("requests", requests);
        return "requests/list";
    }

    @GetMapping("/requests/{id}")
    public String show(@PathVariable Long id, Model model) {
        Request request = requestRepository.findById(id).orElse(null);
        model.addAttribute("request", request);
        return "requests/show";
    }

    @GetMapping("/requests/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Request request = requestRepository.findById(id).orElse(null);
        model.addAttribute("request", request);
        return "requests/edit";
    }

    @PostMapping("/requests/{id}/edit")
    public String update(@PathVariable Long id, RequestForm form) {
        Request request = requestRepository.findById(id).orElse(null);
        request.update(form.getTitle(), form.getContent(), form.getWriter());
        requestRepository.save(request);
        return "redirect:/requests/" + id;
    }

    @PostMapping("/requests/{id}/delete")
    public String delete(@PathVariable Long id) {
        requestRepository.deleteById(id);
        return "redirect:/requests";
    }

    @PostMapping("/requests/{id}/status")
    public String status(@PathVariable Long id, @RequestParam String status) {
        Request request = requestRepository.findById(id).orElse(null);
        request.changeStatus(status);
        requestRepository.save(request);
        return "redirect:/requests/" + id;
    }
}

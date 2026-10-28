package dev.request.system.controller;

import dev.request.system.dto.RequestCreateDto;
import dev.request.system.entity.Request;
import dev.request.system.service.RequestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
public class RequestApiController {

    private final RequestService requestService;

    public RequestApiController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/api/ping")
    public Map<String, String> ping(){
        return Map.of("status", "ok");
    }

    @PostMapping("/api/requests/preview")
    public Map<String, String > preview(
            @RequestBody RequestCreateDto dto
            ){
        return Map.of("title", dto.title());
    }

    @PostMapping("/api/requests")
    public Map<String, Object> create(@RequestBody RequestCreateDto dto) {
        Request saved = requestService.createRequest(
                dto.title(), dto.content()
        );

        return Map.of(
                "id", saved.getId(),
                "status", saved.getRequestStatus().name()
        );
    }
}

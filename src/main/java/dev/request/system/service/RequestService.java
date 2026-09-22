package dev.request.system.service;

import dev.request.system.dto.RequestForm;
import dev.request.system.entity.Request;
import dev.request.system.repository.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void createRequest(RequestForm form) {
        Request request = new Request(form.getTitle(), form.getContent());
        requestRepository.save(request);
    }

    public Iterable<Request> findAllRequests() {
        return requestRepository.findAll();
    }

    public Request findByRequestId(Long id) {
        return requestRepository.findById(id).orElseThrow();
    }
}

package dev.request.system.service;

import dev.request.system.dto.RequestForm;
import dev.request.system.entity.Request;
import dev.request.system.repository.RequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void createRequest(RequestForm form){
        Request request = new Request(form.getTitle(), form.getContent());
        requestRepository.save(request);

    }
}

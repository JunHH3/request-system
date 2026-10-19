package dev.request.system.service;

import dev.request.system.dto.RequestForm;
import dev.request.system.entity.Request;
import dev.request.system.exception.RequestNotFoundException;
import dev.request.system.repository.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));
    }

    @Transactional
    public void updateRequest(Long id, RequestForm form) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));

        request.update(form.getTitle(), form.getContent());
    }

    public void deleteRequest(Long id){
        requestRepository.deleteById(id);
    }

    @Transactional
    public void startProgress(Long id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));

        request.startProgress();
    }

    @Transactional
    public void completeRequest(Long id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));

        request.complete();
    }

}

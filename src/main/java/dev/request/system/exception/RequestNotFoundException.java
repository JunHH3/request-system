package dev.request.system.exception;

public class RequestNotFoundException extends RuntimeException {
    public RequestNotFoundException(Long id) {
        super("요청을 찾을 수 없습니다. ID: " + id);
    }
}

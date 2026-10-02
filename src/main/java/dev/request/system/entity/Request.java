package dev.request.system.entity;

import jakarta.persistence.*;

@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    protected Request(){

    }

    public Request(String title, String content) {
        this.title = title;
        this.content = content;
        this.requestStatus = RequestStatus.RECEIVED;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void startProgress(){
        if (this.requestStatus != RequestStatus.RECEIVED) {
            throw new IllegalStateException();
        }
        this.requestStatus = RequestStatus.IN_PROGRESS;
    }

    public void complete(){
        if (this.requestStatus != RequestStatus.IN_PROGRESS) {
            throw new IllegalStateException();
        }
        this.requestStatus = RequestStatus.COMPLETED;
    }
}

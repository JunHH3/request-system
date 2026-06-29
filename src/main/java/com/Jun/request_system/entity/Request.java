package com.Jun.request_system.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String writer;
    private String status;
    private LocalDateTime createdAt;

    public Request () {
    }

    public Request(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.status = "접수";
        this.createdAt = LocalDateTime.now();
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

    public String getWriter() {
        return writer;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}

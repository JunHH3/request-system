package com.Jun.request_system.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Request {

    /** 1. 필드 영역 **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String writer;
    private String status;
    private LocalDateTime createdAt;


    /** 2. 생성자 영역 **/
    public Request () {
    }

    public Request(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.status = "접수";
        this.createdAt = LocalDateTime.now();
    }

    /** 3. Getter 영역 **/
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

    /** 4. 기능 메서드 영역 **/
    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void changeStatus(String status) {
        this.status = status;
    }
}

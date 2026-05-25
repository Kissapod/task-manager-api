package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private boolean done;
    private LocalDateTime createdAt;
    private String createdBy;
    private String priority;

    // Конструктор
    public Task(String title, boolean done, String createdBy, String priority) {
        this.title = title;
        this.done = done;
        this.createdAt = LocalDateTime.now();
        this.createdBy = createdBy;
        this.priority = priority;
    }

    public Task() {

    }

    // Геттеры и сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getTime() {
        return createdAt;
    }

    public void setTime(LocalDateTime time) {
        this.createdAt = time;
    }

    public String getCreator() {
        return createdBy;
    }

    public void setCreator(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getPriority(){
        return priority;
    }
}
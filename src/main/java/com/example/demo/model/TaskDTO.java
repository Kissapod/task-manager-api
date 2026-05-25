package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;

public class TaskDTO {
    private Integer id;

    @NotBlank(message = "Title must not be empty")
    private String title;

    private boolean done;
    private String priority;

    // Конструктор
    public TaskDTO(Integer id, String title, boolean done, String priority) {
        this.id = id;
        this.title = title;
        this.done = done;
        this.priority = priority;
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

    public String getPriority(){
        return priority;
    }
}

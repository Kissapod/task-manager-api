package com.example.demo.exception;

public class TaskNotFoundException extends NotFoundException{
    public TaskNotFoundException(String message) {
        super(message);
    }
}

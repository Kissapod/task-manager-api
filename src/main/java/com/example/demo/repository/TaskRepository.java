package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByDone(boolean done);
    List<Task> findByPriority(String priority);
    // Не нужно писать код для CRUD операций. Spring Data JPA всё сделает за нас.

}
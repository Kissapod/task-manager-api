package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.model.TaskDTO;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Получить все задачи
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(
            @RequestParam(required = false) Boolean done,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        if (done != null){
            return  ResponseEntity.ok(taskService.getTasksByDone(done));
        }
        return ResponseEntity.ok(taskService.getAllTasks(page, size));
    }

    // Получить задачу по ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable int id) {
        Task task = taskService.getTaskById(id);
        if (task == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        TaskDTO taskDTO = taskService.convertToDTO(task);
        return ResponseEntity.ok(taskDTO);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TaskDTO>> getByDone(@RequestParam boolean done) {
        return ResponseEntity.ok(taskService.getTasksByDone(done));
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<TaskDTO>> getSortedTasks(@RequestParam String field) {
        return  ResponseEntity.ok(taskService.getTasksSorted(field));
    }

    @GetMapping("/priority")
    public  ResponseEntity<List<TaskDTO>> getTasksByPriority(@RequestParam String priority) {
        return  ResponseEntity.ok(taskService.getTasksByPriority(priority));
    }

        // Добавить новую задачу
    @PostMapping
    public ResponseEntity<String> addTask(@RequestBody @Valid TaskDTO taskDTO) {
        Task task = taskService.convertToEntity(taskDTO);
        taskService.addTask(task);

        return ResponseEntity.status(HttpStatus.CREATED).body("Task Created");
    }

    // Обновить задачу
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Integer id, @RequestBody TaskDTO taskDTO) {
        Task task = taskService.convertToEntity(taskDTO);
        boolean updated =  taskService.updateTask(id, task);

        if (!updated) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Not Found");
        }

        return ResponseEntity.ok("Task Updated");
    }

    // Удалить задачу
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        boolean deleted = taskService.deleteTask(id);

        if (!deleted) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Not Found");
        }

        return ResponseEntity.ok("Task Deleted");
    }
}
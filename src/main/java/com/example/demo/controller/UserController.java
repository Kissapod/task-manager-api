package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.model.TaskDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody User user){
        userService.register(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDTO> getUserTasks(@PathVariable Integer id){
        return userService.getTasksByUser(id);
    }
}

package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.model.TaskDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void register(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found: " + id));
    }

    public List<TaskDTO> getTasksByUser(Integer userId) {
        User user = getUserById(userId);

        if (user == null) {
            return List.of();
        }

        List<Task> tasks = user.getTasks();

        return tasks.stream()
                .map(task -> new TaskDTO(
                        task.getId(),
                        task.getTitle(),
                        task.isDone(),
                        task.getPriority()
                ))
                .toList();
    }
}

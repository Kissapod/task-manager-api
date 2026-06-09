package com.example.demo.service;

import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.model.TaskDTO;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Получить все задачи
    public List<TaskDTO> getAllTasks(int page, int size) {
        Page<Task> tasksPage = taskRepository.findAll(PageRequest.of(page, size));
        return convertToDTOList(tasksPage.getContent());
    }

    public List<TaskDTO> getTasksByDone(boolean done){
        List<Task> tasks = taskRepository.findByDone(done);
        return convertToDTOList(tasks);
    }

    public List<TaskDTO> getTasksByPriority(String priority){
        List<Task> tasks = taskRepository.findByPriority(priority);
        return convertToDTOList(tasks);
    }

    public List<TaskDTO> getTasksSorted(String field){
        List<Task> tasks = taskRepository.findAll(Sort.by(field));
        return convertToDTOList(tasks);
    }

    public TaskDTO convertToDTO (Task task){
        return new TaskDTO(task.getId(), task.getTitle(), task.isDone(), task.getPriority() );
    }

    public List<TaskDTO> convertToDTOList(List<Task> tasks){
        return tasks.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Преобразование TaskDTO в Task
    public Task convertToEntity(TaskDTO taskDTO) {
        return new Task(
                taskDTO.getTitle(),
                taskDTO.isDone(),
                "defaultUser",
                taskDTO.getPriority()
                );  // Используем данные из TaskDTO
    }

    // Добавить новую задачу
    public void addTask(Task task) {
        System.out.println("SAVING: " + task.getTitle());

        taskRepository.save(task);
    }

    // Получить задачу по ID
    public Task getTaskById(Integer id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }


    // Обновить задачу
    public boolean updateTask(Integer id, Task updatedTask) {
        Task existing = getTaskById(id);

        if (existing != null) {
            existing.setTitle(updatedTask.getTitle());
            existing.setDone(updatedTask.isDone());

            taskRepository.save(existing);
            return true;
        }

        return false;
    }

    // Удалить задачу
    public boolean deleteTask(Integer id) {
        if (taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
package com.masongarrett.taskmanagementsystem.controller;

import com.masongarrett.taskmanagementsystem.model.Task;
import com.masongarrett.taskmanagementsystem.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/")
    public Task createTask(@RequestBody Task task) {
        taskService.save(task);
        return task;
    }

    @GetMapping("/")
    public List<Task> getAllTasks() {
        return taskService.get();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable long id) {
        Task task = taskService.get(id);
        if (task == null) {
            throw new RuntimeException("Task not found for the ID: " + id);
        }
        return task;
    }

    @PutMapping("/")
    public Task updateTask(@RequestBody Task task) {
        taskService.save(task);
        return task;
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable long id) {
        taskService.delete(id);
        return "Task has been deleted with ID: " + id;
    }
}

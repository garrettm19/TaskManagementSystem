package com.masongarrett.taskmanagementsystem.controller;

import com.masongarrett.taskmanagementsystem.model.TaskMeta;
import com.masongarrett.taskmanagementsystem.service.TaskMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-meta")
public class TaskMetaController {

    @Autowired
    private TaskMetaService taskMetaService;

    @PostMapping("/")
    public TaskMeta createTaskMeta(@RequestBody TaskMeta taskMeta) {
        taskMetaService.save(taskMeta);
        return taskMeta;
    }

    @GetMapping("/")
    public List<TaskMeta> getAllTaskMeta() {
        return taskMetaService.get();
    }

    @GetMapping("/{id}")
    public TaskMeta getTaskMetaById(@PathVariable long id) {
        TaskMeta taskMeta = taskMetaService.get(id);
        if (taskMeta == null) {
            throw new RuntimeException("TaskMeta not found for ID: " + id);
        }
        return taskMeta;
    }

    @PutMapping("/")
    public TaskMeta updateTaskMeta(@RequestBody TaskMeta taskMeta) {
        taskMetaService.save(taskMeta);
        return taskMeta;
    }

    @DeleteMapping("/{id}")
    public String deleteTaskMeta(@PathVariable long id) {
        taskMetaService.delete(id);
        return "TaskMeta has been deleted with ID: " + id;
    }
}

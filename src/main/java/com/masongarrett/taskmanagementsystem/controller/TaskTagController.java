package com.masongarrett.taskmanagementsystem.controller;

import com.masongarrett.taskmanagementsystem.model.TaskTag;
import com.masongarrett.taskmanagementsystem.service.TaskTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-tags")
public class TaskTagController {

    @Autowired
    private TaskTagService taskTagService;

    @PostMapping("/")
    public TaskTag createTaskTag(@RequestBody TaskTag taskTag) {
        taskTagService.save(taskTag);
        return taskTag;
    }

    @GetMapping("/")
    public List<TaskTag> getAllTaskTags() {
        return taskTagService.get();
    }

    @GetMapping("/{id}")
    public TaskTag getTaskTagById(@PathVariable long id) {
        TaskTag taskTag = taskTagService.get(id);
        if (taskTag == null) {
            throw new RuntimeException("TaskTag not found for ID: " + id);
        }
        return taskTag;
    }

    @PutMapping("/")
    public TaskTag updateTaskTag(@RequestBody TaskTag taskTag) {
        taskTagService.save(taskTag);
        return taskTag;
    }

    @DeleteMapping("/{id}")
    public String deleteTaskTag(@PathVariable long id) {
        taskTagService.delete(id);
        return "TaskTag has been deleted with ID: " + id;
    }
}

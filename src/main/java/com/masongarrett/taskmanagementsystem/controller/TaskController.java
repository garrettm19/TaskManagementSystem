package com.masongarrett.taskmanagementsystem.controller;

import com.masongarrett.taskmanagementsystem.model.Task;
import com.masongarrett.taskmanagementsystem.service.TaskNotFoundException;
import com.masongarrett.taskmanagementsystem.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/state/{state}")
    public List<Task> getTasksByState(@PathVariable String state) {
        // Call the service method to retrieve tasks by state
        List<Task> tasks = taskService.getTasksByState(state);
        return tasks;
    }

    @PostMapping("/updateTaskPosition/{taskId}")
    public ResponseEntity<String> updateTaskPosition(@PathVariable Long taskId, @RequestBody String newState) {
        try {
            // Call the service method to update the task's state in the database
            taskService.updateTaskState(taskId, newState);

            return ResponseEntity.ok("Task state updated successfully");
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found with ID: " + taskId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update task state");
        }
    }

    @PostMapping("/createTask")
    public ModelAndView createTask(@RequestParam("title") String title,
                                   @RequestParam("tag") String tag,
                                   @RequestParam("description") String description,
                                   @RequestParam("state") String state,
                                   HttpSession session) {
        // Retrieve the userId from the session
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            // Handle the case where the userId is not found in the session (e.g., redirect to an error page)
            return new ModelAndView("error"); // Change this to your error page
        }

        // Now, you have the userId, and you can proceed to create the Task
        Task task = new Task(userId, title, description, state, tag);

        // Save the task to the database
        taskService.save(task);

        // Redirect to the "taskmanager" view
        ModelAndView modelAndView = new ModelAndView("taskmanager");

        // Optionally, you can add any additional model attributes here if needed
        modelAndView.addObject("userId", userId);

        return modelAndView;
    }

}

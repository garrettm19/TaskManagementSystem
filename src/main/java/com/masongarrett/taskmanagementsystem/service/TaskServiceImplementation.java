package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.dao.TaskDAO;
import com.masongarrett.taskmanagementsystem.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImplementation implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Transactional
    @Override
    public List<Task> get() {
        return taskDAO.get();
    }

    @Transactional
    @Override
    public Task get(long id) {
        return taskDAO.get(id);
    }

    @Transactional
    @Override
    public void save(Task task) {
        taskDAO.save(task);
    }

    @Transactional
    @Override
    public void delete(long id) {
        taskDAO.delete(id);
    }

    @Transactional
    @Override
    public List<Task> getTasksByState(String state) {
        return taskDAO.getByState(state);
    }

    @Override
    public void updateTaskState(Long taskId, String newState) throws TaskNotFoundException {
        // Log the taskId for debugging
        System.out.println("Task ID: " + taskId);

        // Retrieve the task by its ID from the database
        Optional<Task> optionalTask = Optional.ofNullable(taskDAO.get(taskId));

        // Log the result of the retrieval
        System.out.println("Task retrieval result: " + optionalTask);

        Task task = optionalTask.orElse(null);

        if (task != null) {
            // Update the task's state
            task.setState(newState);

            // Save the updated task back to the database
            taskDAO.save(task);
        } else {
            throw new TaskNotFoundException("Task not found with ID: " + taskId);
        }
    }

}
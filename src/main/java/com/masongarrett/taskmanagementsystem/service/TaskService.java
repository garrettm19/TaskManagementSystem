package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> get();
    Task get(long id);
    void save(Task task);
    void delete(long id);
    public List<Task> getTasksByState(String state);
    public void updateTaskState(Long taskId, String newState) throws TaskNotFoundException;

}
package com.masongarrett.taskmanagementsystem.dao;

import com.masongarrett.taskmanagementsystem.model.Task;

import java.util.List;

public interface TaskDAO {

    List<Task> get();
    Task get(long id);
    void save(Task task);
    void delete(long id);
}

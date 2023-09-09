package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.dao.TaskDAO;
import com.masongarrett.taskmanagementsystem.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
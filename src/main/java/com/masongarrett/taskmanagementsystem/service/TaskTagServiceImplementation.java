package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.dao.TaskTagDAO;
import com.masongarrett.taskmanagementsystem.model.TaskTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskTagServiceImplementation implements TaskTagService {

    @Autowired
    private TaskTagDAO taskTagDAO;

    @Transactional
    @Override
    public List<TaskTag> get() {
        return taskTagDAO.get();
    }

    @Transactional
    @Override
    public TaskTag get(long id) {
        return taskTagDAO.get(id);
    }

    @Transactional
    @Override
    public void save(TaskTag taskTag) {
        taskTagDAO.save(taskTag);
    }

    @Transactional
    @Override
    public void delete(long id) {
        taskTagDAO.delete(id);
    }
}

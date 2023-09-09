package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.dao.TaskMetaDAO;
import com.masongarrett.taskmanagementsystem.model.TaskMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskMetaServiceImplementation implements TaskMetaService {

    @Autowired
    private TaskMetaDAO taskMetaDAO;

    @Transactional
    @Override
    public List<TaskMeta> get() {
        return taskMetaDAO.get();
    }

    @Transactional
    @Override
    public TaskMeta get(long id) {
        return taskMetaDAO.get(id);
    }

    @Transactional
    @Override
    public void save(TaskMeta taskMeta) {
        taskMetaDAO.save(taskMeta);
    }

    @Transactional
    @Override
    public void delete(long id) {
        taskMetaDAO.delete(id);
    }
}

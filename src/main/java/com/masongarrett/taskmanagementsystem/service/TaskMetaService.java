package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.model.TaskMeta;

import java.util.List;

public interface TaskMetaService {

    List<TaskMeta> get();
    TaskMeta get(long id);
    void save(TaskMeta taskMeta);
    void delete(long id);
}

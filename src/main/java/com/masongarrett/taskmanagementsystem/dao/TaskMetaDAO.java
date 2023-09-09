package com.masongarrett.taskmanagementsystem.dao;

import com.masongarrett.taskmanagementsystem.model.TaskMeta;

import java.util.List;

public interface TaskMetaDAO {

    List<TaskMeta> get();
    TaskMeta get(long id);
    void save(TaskMeta taskMeta);
    void delete(long id);
}

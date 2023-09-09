package com.masongarrett.taskmanagementsystem.dao;

import com.masongarrett.taskmanagementsystem.model.TaskTag;

import java.util.List;

public interface TaskTagDAO {

    List<TaskTag> get();
    TaskTag get(long id);
    void save(TaskTag taskTag);
    void delete(long id);
}

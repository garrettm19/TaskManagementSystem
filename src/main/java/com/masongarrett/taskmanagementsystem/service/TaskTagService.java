package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.model.TaskTag;

import java.util.List;

public interface TaskTagService {

    List<TaskTag> get();
    TaskTag get(long id);
    void save(TaskTag taskTag);
    void delete(long id);
}

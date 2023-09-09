package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.model.Activity;

import java.util.List;

public interface ActivityService {

    List<Activity> get();
    Activity get(long id);
    void save(Activity activity);
    void delete(long id);
}

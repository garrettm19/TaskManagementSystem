package com.masongarrett.taskmanagementsystem.dao;

import com.masongarrett.taskmanagementsystem.model.Activity;

import java.util.List;

public interface ActivityDAO {

    List<Activity> get();
    Activity get(long id);
    void save(Activity activity);
    void delete(long id);
}

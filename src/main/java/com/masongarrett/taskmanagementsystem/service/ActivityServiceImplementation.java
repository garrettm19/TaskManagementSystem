package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.dao.ActivityDAO;
import com.masongarrett.taskmanagementsystem.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityServiceImplementation implements ActivityService {

    @Autowired
    private ActivityDAO activityDAO;

    @Transactional
    @Override
    public List<Activity> get() {
        return activityDAO.get();
    }

    @Transactional
    @Override
    public Activity get(long id) {
        return activityDAO.get(id);
    }

    @Transactional
    @Override
    public void save(Activity activity) {
        activityDAO.save(activity);
    }

    @Transactional
    @Override
    public void delete(long id) {
        activityDAO.delete(id);
    }
}

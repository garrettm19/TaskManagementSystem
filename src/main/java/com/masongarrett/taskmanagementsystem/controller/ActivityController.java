package com.masongarrett.taskmanagementsystem.controller;

import com.masongarrett.taskmanagementsystem.model.Activity;
import com.masongarrett.taskmanagementsystem.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping("/")
    public Activity createActivity(@RequestBody Activity activity) {
        activityService.save(activity);
        return activity;
    }

    @GetMapping("/")
    public List<Activity> getAllActivities() {
        return activityService.get();
    }

    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable long id) {
        Activity activity = activityService.get(id);
        if (activity == null) {
            throw new RuntimeException("Activity not found for ID: " + id);
        }
        return activity;
    }

    @PutMapping("/")
    public Activity updateActivity(@RequestBody Activity activity) {
        activityService.save(activity);
        return activity;
    }

    @DeleteMapping("/{id}")
    public String deleteActivity(@PathVariable long id) {
        activityService.delete(id);
        return "Activity has been deleted with ID: " + id;
    }
}

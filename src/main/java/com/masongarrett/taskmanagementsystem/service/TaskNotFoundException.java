package com.masongarrett.taskmanagementsystem.service;

public class TaskNotFoundException extends Throwable {
    public TaskNotFoundException(String s) {
        super(s);
    }

    public TaskNotFoundException(String s, Throwable cause) {
        super(s, cause);
    }
}

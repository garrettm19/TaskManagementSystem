package com.masongarrett.taskmanagementsystem.service;

import java.util.List;

import com.masongarrett.taskmanagementsystem.model.User;

public interface UserService {

    List<User> get();
    User get(int id);
    void save(User user);
    void delete(int id);
}

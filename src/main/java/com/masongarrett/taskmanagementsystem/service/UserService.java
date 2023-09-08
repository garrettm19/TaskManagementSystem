package com.masongarrett.taskmanagementsystem.service;

import java.util.List;

import com.masongarrett.taskmanagementsystem.model.User;

public interface UserService {

    List<User> get();
    User get(long id);
    void save(User user);
    void delete(long id);
}

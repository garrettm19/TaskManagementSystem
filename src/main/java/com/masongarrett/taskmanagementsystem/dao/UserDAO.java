package com.masongarrett.taskmanagementsystem.dao;

import java.util.List;

import com.masongarrett.taskmanagementsystem.model.User;

public interface UserDAO {

    List<User> get();
    User get(long id);
    void save(User user);
    void delete(long id);
}

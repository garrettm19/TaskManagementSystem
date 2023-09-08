package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.dao.UserDAO;
import com.masongarrett.taskmanagementsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //@Service means that the class connects the controller to the DAO and performs the complex operations
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Transactional //@Transactional is a tool used for managing database transactions and ensuring data integrity.
    @Override //@Transactional is placed here because of multiple reasons: 1. The DAO file is used only for database interactions, so it would be fitting in this file (Service)
    public List<User> get() { // 2. ACID principles... if there is a method in here that requires two separate DAO functions and one fails then we will not be guaranteeing Atomocity. (Half the transaction will be successful)
        return userDAO.get();
    }

    @Transactional
    @Override
    public User get(long id) {
        return userDAO.get(id);
    }

    @Transactional
    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }
}

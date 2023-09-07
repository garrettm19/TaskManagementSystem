package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.dao.UserDAO;
import com.masongarrett.taskmanagementsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Transactional
    @Override
    public List<User> get() {
        return userDAO.get();
    }

    @Transactional
    @Override
    public User get(int id) {
        return null;
    }

    @Transactional
    @Override
    public void save(User user) {

    }

    @Transactional
    @Override
    public void delete(int id) {

    }
}

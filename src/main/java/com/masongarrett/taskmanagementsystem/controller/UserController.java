package com.masongarrett.taskmanagementsystem.controller;

import com.masongarrett.taskmanagementsystem.model.User;
import com.masongarrett.taskmanagementsystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User save(@RequestBody User user) {
        userService.save(user);
        return user;
    }
    @GetMapping("/user")
    public List<User> get() {
        return userService.get();
    }
}

package com.masongarrett.taskmanagementsystem.controller;

import com.masongarrett.taskmanagementsystem.model.User;
import com.masongarrett.taskmanagementsystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //@RestController means the class will handle HTTP requests (It will be an API Endpoint)
@RequestMapping("/api") //@RequestMapping("/api") organizes everything in the class to be under the path /api
public class UserController {

    @Autowired //@AutoWired is saying "Hey, I need this, and please make sure it's available for me."
    private UserService userService;

    /* [This is the code that AutoWired is creating behind the scenes which performs dependency injection]
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    } */

    @PostMapping("/user") //@PostMapping means that the method handles HTTP Post requests (create), ("/user") specifies the endpoint
    public User save(@RequestBody User user) { //@RequestBody is saying "Hey, the important data you need is inside the request body, so pay attention to it."
        userService.save(user);
        return user;
    }

    @GetMapping("/user") //@GetMapping means that the method handles HTTP Get requests (read)
    public List<User> get() {
        return userService.get();
    }

    @GetMapping("/user/{id}")
    public User get(@PathVariable long id) { //@PathVariable is looking for a long that is placed right after the websites address {id}
        User userObj = userService.get(id);
        if (userObj == null) {
            throw new RuntimeException("Employee not found for the Id: " + id);
        }
        return userObj;
    }

    @PutMapping("/user") //@PutMapping means that the method handles HTTP Put requests (update)
    public User update(@RequestBody User userObj) {
        userService.save(userObj);
        return userObj;
    }

    @DeleteMapping("/user/{id}") //@DeleteMapping means that the method handles HTTP Delete requests (delete)
    public String delete(@PathVariable long id) {
        userService.delete(id);
        return "Employee has been deleted with Id: " + id;
    }
}

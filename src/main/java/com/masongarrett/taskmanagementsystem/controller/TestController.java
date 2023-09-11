package com.masongarrett.taskmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/taskmanager")
    public String taskmanager() {
        return "taskmanager";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}

package com.masongarrett.taskmanagementsystem.controller;

import com.masongarrett.taskmanagementsystem.model.User;
import com.masongarrett.taskmanagementsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public ModelAndView addUser(@RequestParam("email") String email, @RequestParam("pass1") String pass1, @RequestParam("pass2") String pass2, User user, HttpSession session) {
        ModelAndView mv;

        // Check if any of the input values are null
        if (email == null || pass1 == null || pass2 == null) {
            mv = new ModelAndView("login"); // Stay on the "register" page if there's an error
            mv.addObject("error", "Please provide all required information.");
        } else if (!pass1.equals(pass2)) {
            mv = new ModelAndView("login"); // Stay on the "register" page if there's an error
            mv.addObject("error", "Passwords do not match.");
        } else if (pass1.length() < 5) {
            mv = new ModelAndView("login"); // Stay on the "register" page if there's an error
            mv.addObject("error", "Password must be at least 5 characters long.");
        } else {
            // Check if the email is already in use
            User userCheck = userService.findUserByEmail(email);
            if (userCheck != null) {
                mv = new ModelAndView("login"); // Stay on the "register" page if there's an error
                mv.addObject("error", "Oops! There is already a user registered with the email provided.");
            } else {
                // Set the password in the User object
                user.setPassword(pass1);
                Date currentDate = new Date(System.currentTimeMillis());
                user.setRegisteredAt(currentDate);
                userService.save(user);
                mv = new ModelAndView("dummy"); // Redirect to "dummy" upon successful registration
                mv.addObject("error", "User has been successfully registered.");

                // Add user-related information to the session
                session.setAttribute("email", email);
            }
        }
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login_user(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, ModelMap modelMap) {
        ModelAndView mv = new ModelAndView("login"); // Default view name is "login"

        // Check if email and password are null or empty
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            mv.addObject("error", "Email and password are required.");
            return mv; // Stay on the "login" page with the error message
        }

        User auser = userService.findByEmailPassword(email, password);

        if (auser != null) {
            String uemail = auser.getEmail();
            String upass = auser.getPassword();

            if (email.equalsIgnoreCase(uemail) && password.equalsIgnoreCase(upass)) {
                session.setAttribute("email", email);
                return new ModelAndView("taskmanager"); // Redirect to "taskmanager" upon successful login
            } else {
                mv.addObject("error", "Incorrect password.");
                return mv; // Stay on the "login" page with the error message
            }
        } else {
            mv.addObject("error", "Email not found.");
            return mv; // Stay on the "login" page with the error message
        }
    }

    @GetMapping(value = "/logout")
    public String logout_user(HttpSession session)
    {
        session.removeAttribute("email");
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/dummy")
    public String dummy()
    {
        return "dummy";
    }

}
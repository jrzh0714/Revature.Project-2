package com.jteam.project_2.controllers;

import com.jteam.project_2.models.User;
import com.jteam.project_2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable int id) {
        return userService.getUserById(id).toString();
    }

    @GetMapping("/user/all")
    public String getAllUsers(){
        return userService.findAll().toString();
    }
}

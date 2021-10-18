package com.jteam.project_2.controllers;

import com.jteam.project_2.models.User;
import com.jteam.project_2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id) {
        return userService.getUserById(id).toString();
    }

    @GetMapping("/all")
    public String getAllUsers(){
        return userService.findAll().toString();
    }
}

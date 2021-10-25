package com.jteam.project_2.controllers;

import com.jteam.project_2.models.User;
import com.jteam.project_2.models.UserDemographic;
import com.jteam.project_2.services.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private UserService userService;



    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return (User) Hibernate.unproxy(userService.getUserById(id));
    }

    @GetMapping("/{id}/{gender}")
    public User changeUserGender(@PathVariable int id,@PathVariable int gender) {
        UserDemographic test = new UserDemographic();
        test.setId(id);
        test.setGender(gender);
        User changedUser = userService.getUserById(id);
        test.setUser(changedUser);
        changedUser.setDemographic(test);
        return userService.save(changedUser);
    }

    /**
     * Updates all user demographics at once based on input from the client side
     * @param userDemographic contains the user-specified demographics values
     * @return A User ResponseEntity with Http Status code
     */
    @PostMapping(path="/updateDemographics",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> changeUserDemographics(@RequestBody UserDemographic userDemographic){
        User user = userService.getUserById(userDemographic.getId());
        ResponseEntity<User> returnuser = new ResponseEntity<>(user, HttpStatus.NOT_FOUND);

        if (userDemographic.getId() != 0){
            user.setDemographic(userDemographic);
            userService.save(user);
            returnuser = new ResponseEntity<>(user, HttpStatus.OK);

        }

        return returnuser;
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/userByUsername/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }
    @GetMapping("/usernameById/{id}")
    public String getUsernameById(@PathVariable int id){
        return userService.getUsernameById(id);
    }

    @GetMapping("/userExists/{username}")
    public boolean userExists(@PathVariable String username){
        return userService.userExists(username);
    }
}

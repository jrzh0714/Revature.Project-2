package com.jteam.project_2.controllers;

import com.jteam.project_2.controllers.post_objects.LoginAttempt;
import com.jteam.project_2.controllers.reply_objects.JWTHandler;
import com.jteam.project_2.models.User;
import com.jteam.project_2.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginControllerTesting {

    private LoginController testLoginController;

    @BeforeEach
    public void init() {
        UserService mockUserService = mock(UserService.class);
        User mockUser = mock(User.class);

        when(mockUserService.getUserByUsername("Dave")).thenReturn(mockUser);

        when(mockUser.getHash()).thenReturn("$2a$11$R6nrr5o.674gY9TlsoXHqeUcohS3bCPDJJ4ZSnCrHzW8BxqBURgU6");

        testLoginController = new LoginController(mockUserService);
    }

    @Test
    public void loginTestSuccess() {
        LoginAttempt testLogin = new LoginAttempt();
        testLogin.setUsername("Dave");
        testLogin.setPassword("abc123");

        assertNotEquals(new ResponseEntity<>(null, HttpStatus.NOT_FOUND), testLoginController.login(testLogin),"Login Failed!");
    }

    @Test
    public void loginTestBadPass() {
        LoginAttempt testLogin = new LoginAttempt();
        testLogin.setUsername("Dave");
        testLogin.setPassword("wrong");

        assertEquals(new ResponseEntity<>(null, HttpStatus.NOT_FOUND), testLoginController.login(testLogin),"Bad login Succeeded!");
    }

    @Test
    public void loginTestBadUser() {
        LoginAttempt testLogin = new LoginAttempt();
        testLogin.setUsername("Daveeee");
        testLogin.setPassword("abc123");

        assertEquals(new ResponseEntity<>(null, HttpStatus.NOT_FOUND), testLoginController.login(testLogin), "Bad login Succeeded!");
    }
}

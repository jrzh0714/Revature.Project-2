package com.jteam.project_2.controllers;

import com.jteam.project_2.controllers.post_objects.LoginAttempt;
import com.jteam.project_2.controllers.reply_objects.JWT;
import com.jteam.project_2.models.User;
import com.jteam.project_2.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void generateTokenTest() {
        JWT testToken = testLoginController.generateToken("testuser");
        String checkToken = testLoginController.getJwtUsername(testToken);
        assertEquals("testuser", checkToken, "Output token incorrect!");
    }

    @Test
    public void checkSecret() throws NoSuchFieldException, IllegalAccessException {
        Class cls = testLoginController.getClass();
        Field field = cls.getDeclaredField("secret");
        field.setAccessible(true);

        assertNotNull((String)field.get(testLoginController), "null secret key!");
    }

    @Test
    public void loginTestSuccess() {
        LoginAttempt testLogin = new LoginAttempt();
        testLogin.setUsername("Dave");
        testLogin.setPassword("abc123");

        assertNotNull(testLoginController.login(testLogin),"Login Failed!");
    }

    @Test
    public void loginTestBadPass() {
        LoginAttempt testLogin = new LoginAttempt();
        testLogin.setUsername("Dave");
        testLogin.setPassword("wrong");

        assertNull(testLoginController.login(testLogin),"Bad login Succeeded!");
    }

    @Test
    public void loginTestBadUser() {
        LoginAttempt testLogin = new LoginAttempt();
        testLogin.setUsername("Daveeee");
        testLogin.setPassword("abc123");

        assertNull(testLoginController.login(testLogin),"Bad login Succeeded!");
    }
}

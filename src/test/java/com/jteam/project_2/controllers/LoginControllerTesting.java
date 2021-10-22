package com.jteam.project_2.controllers;

import com.jteam.project_2.models.User;
import com.jteam.project_2.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class LoginControllerTesting {

    private LoginController testLoginController;

    @BeforeEach
    public void init() {
        UserService mockUserService = mock(UserService.class);
        testLoginController = new LoginController(mockUserService);
    }

    @Test
    public void generateTokenTest() {
        String testToken = testLoginController.generateToken("testuser");
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
}

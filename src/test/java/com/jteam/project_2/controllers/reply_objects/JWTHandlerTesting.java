package com.jteam.project_2.controllers.reply_objects;

import com.jteam.project_2.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JWTHandlerTesting {
    private JWTHandler testJWTHandler;

    @BeforeEach
    public void init() {
        testJWTHandler = new JWTHandler();
    }

    @Test
    public void tokenConstructTest() {
        JWTHandler testToken = new JWTHandler("testuser");
        String checkToken = testToken.getUsername();
        assertEquals("testuser", checkToken, "Output token incorrect!");
    }

    @Test
    public void getUsernameBadSecret() {
        testJWTHandler.setToken("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFiZCJ9.IdHxc5qFNZ0ehLhzQM8YxzARq0wYh-3YY91F-3fJ0z0");
        String checkToken = testJWTHandler.getUsername();
        assertNull(checkToken, "Did not reject bad key!");
    }

    @Test
    public void checkSecret() throws NoSuchFieldException, IllegalAccessException {
        Class cls = testJWTHandler.getClass();
        Field field = cls.getDeclaredField("secret");
        field.setAccessible(true);

        assertNotNull((String) field.get(testJWTHandler), "null secret key!");
    }

    @Test
    public void checkTokenValidTestGood() {
        testJWTHandler = new JWTHandler("testUser");
        UserService mockUserService = mock(UserService.class);
        when(mockUserService.userExists("testUser")).thenReturn(true);
        assertTrue(testJWTHandler.checkTokenValid(mockUserService), "Good token refused!");
    }

    @Test
    public void checkTokenValidTestExpired() {
        testJWTHandler.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXJvbGQiLCJpYXQiOjE2MzUxNzY2MzYsImV4cCI6MTYzNTE4MDIzNn0.-J2ZdNPt7q_t0wTso5t8EIuuNi8xTo93FOzr3WwuFBA");
        UserService mockUserService = mock(UserService.class);
        when(mockUserService.userExists("testUser")).thenReturn(true);
        assertFalse(testJWTHandler.checkTokenValid(mockUserService), "Expired token allowed!");
    }

    @Test
    public void checkTokenValidTestNoUser() {
        testJWTHandler = new JWTHandler("testUser");
        UserService mockUserService = mock(UserService.class);
        when(mockUserService.userExists("testUser")).thenReturn(false);
        assertFalse(testJWTHandler.checkTokenValid(mockUserService), "Bad user allowed!");
    }
}


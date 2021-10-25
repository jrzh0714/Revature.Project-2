package com.jteam.project_2.controllers.reply_objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

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
    public void checkSecret() throws NoSuchFieldException, IllegalAccessException {
        Class cls = testJWTHandler.getClass();
        Field field = cls.getDeclaredField("secret");
        field.setAccessible(true);

        assertNotNull((String)field.get(testJWTHandler), "null secret key!");
    }

    @Test
    public void checkBadSecret() {
        testJWTHandler.setToken("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFiZCJ9.IdHxc5qFNZ0ehLhzQM8YxzARq0wYh-3YY91F-3fJ0z0");
        String checkToken = testJWTHandler.getUsername();
        assertNull(checkToken, "Did not reject bad key!");
    }
}

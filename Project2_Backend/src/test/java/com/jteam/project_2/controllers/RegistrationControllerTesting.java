package com.jteam.project_2.controllers;

import com.jteam.project_2.models.User;
import com.jteam.project_2.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationControllerTesting {

    private RegistrationController testRegistrationController;
    private UserService mockUserService;
    private User mockUser;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @BeforeEach
    public void init() {
        mockUserService = mock(UserService.class);
        mockUser = mock(User.class);
        testRegistrationController = new RegistrationController(mockUserService);
    }

    @Test
    public void registerTest() {
        when(mockUser.getHash()).thenReturn("password");

        ResponseEntity<User> testResponseEntity = testRegistrationController.register(mockUser);

        verify(mockUserService).save(userCaptor.capture());
        User newUser = userCaptor.getValue();

        assertSame(mockUser, newUser, "mockUser does not match newUser!");
        assertSame(mockUser, testResponseEntity.getBody(), "mockUser does not match response body!");
        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode(), "Response has bad status code!");
    }
}

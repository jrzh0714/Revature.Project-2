package com.jteam.project_2.controllers;

import com.jteam.project_2.models.User;
import com.jteam.project_2.models.UserDemographic;
import com.jteam.project_2.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.InitBinder;


import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserControllerTesting {

    private UserController testUserController;
    private UserService mockUserService;
    private User mockUser;

    @Captor
    ArgumentCaptor<UserDemographic> userDemographicCaptor;

    @BeforeEach
    public void init() {
        mockUserService = mock(UserService.class);
        mockUser = mock(User.class);
        testUserController = new UserController(mockUserService);

        when(mockUserService.getUserById(5)).thenReturn(mockUser);
        when(mockUserService.save(mockUser)).thenReturn(mockUser);
    }

    @Test
    public void changeUserGenderTest() {
        int testId = 5;
        int testGender = 2;

        User testUser = testUserController.changeUserGender(testId, testGender);

        verify(mockUser).setDemographic(userDemographicCaptor.capture());
        UserDemographic newUserDemographic = userDemographicCaptor.getValue();

        assertEquals(testId, newUserDemographic.getId(), "UserDemographic id incorrect!");
        assertEquals(testGender, newUserDemographic.getGender(), "UserDemographic gender incorrect!");
        assertSame(mockUser, newUserDemographic.getUser(), "UserDemographic user incorrect!");
        assertSame(mockUser, testUser, "changeUserGender returned wrong user!");
        verify(mockUserService).save(mockUser);
    }

    @Test
    public void changeUserDemographicsTest() {
        UserDemographic mockUserDemographic = mock(UserDemographic.class);
        when(mockUserDemographic.getId()).thenReturn(5);

        ResponseEntity<User> testResponseEntity = testUserController.changeUserDemographics(mockUserDemographic);

        verify(mockUser).setDemographic(mockUserDemographic);
        verify(mockUserService).save(mockUser);
        assertSame(mockUser, testResponseEntity.getBody(), "Incorrect ResponseEntity body!");
        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode(), "Incorrect Status code!");
    }
}

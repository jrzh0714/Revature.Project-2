package com.jteam.project_2.controllers.post_objects;

import lombok.Data;

/**
 * Stores info on an incoming login attempt
 */
@Data
public class LoginAttempt {
    private String password;
    private String username;
}

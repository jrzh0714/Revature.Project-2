package com.jteam.project_2.controllers;

import com.jteam.project_2.controllers.post_objects.LoginAttempt;
import com.jteam.project_2.controllers.reply_objects.JWTHandler;
import com.jteam.project_2.models.User;
import com.jteam.project_2.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Date;

@RestController
@RequestMapping(path = "")
public class LoginController {

    private UserService userService;

    private String secret;

    private static int tokenLife = 3600;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;

        secret = System.getenv("Feed_JWT_Secret");
    }

    /**
     * Checks login info and returns a JWT if accepted.
     * @param loginAttempt Holds the username and password of the user.
     * @return A ResponseEntity containing a JWT.
     */
    @PostMapping(path="/login",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTHandler> login(@RequestBody LoginAttempt loginAttempt) {
        User loginUser = userService.getUserByUsername(loginAttempt.getUsername());
        ResponseEntity<JWTHandler> jwtresponse = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        if(checkPassword(loginUser, loginAttempt.getPassword())) {
            JWTHandler token = new JWTHandler(loginAttempt.getUsername());

            jwtresponse = new ResponseEntity<>(token, HttpStatus.OK);
        }
        return jwtresponse;
    }

    /**
     * Checks a password and user combo.
     * @param user The user that is receiving the login request.
     * @param password The input password.
     * @return True if password correct, false if incorrect.
     */
    public boolean checkPassword(User user, String password) {
        if (user != null) {
            String hashedPassword = user.getHash();
            return BCrypt.checkpw(password, hashedPassword);
        } else {
            return false;
        }
    }

}

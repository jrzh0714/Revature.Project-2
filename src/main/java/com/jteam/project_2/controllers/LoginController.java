package com.jteam.project_2.controllers;

import com.jteam.project_2.controllers.post_objects.LoginAttempt;
import com.jteam.project_2.controllers.reply_objects.JWT;
import com.jteam.project_2.models.User;
import com.jteam.project_2.models.UserDemographic;
import com.jteam.project_2.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping(path = "login")
public class LoginController {

    private UserService userService;

    private String secret;

    private static int tokenLife = 3600;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;

        secret = System.getenv("Feed_JWT_Secret");
    }

    @PostMapping(path="/login",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JWT login(@RequestBody LoginAttempt loginAttempt) {
        User loginUser = userService.getUserByUsername(loginAttempt.getUsername());

        if(checkPassword(loginUser, loginAttempt.getPassword())) {
            return generateToken(loginAttempt.getUsername());
        }
        return null;
    }

    public JWT generateToken(String username) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        String newToken = Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenLife * 1000)).signWith(key, SignatureAlgorithm.HS256).compact();

        JWT newJWT = new JWT();
        newJWT.setToken(newToken);

        return newJWT;
    }

    public String getJwtUsername(JWT jwt) {
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(jwt.getToken()).getBody().getSubject();
    }

    public boolean checkPassword(User user, String password) {
        if (user != null) {
            String hashedPassword = user.getHash();
            return BCrypt.checkpw(password, hashedPassword);
        } else {
            return false;
        }
    }

}

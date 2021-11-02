package com.jteam.project_2.controllers.reply_objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jteam.project_2.services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.crypto.SecretKey;
import java.util.Date;

@Data
@NoArgsConstructor
public class JWTHandler {
    String token;
    private static String secret;
    private static int tokenLife = 3600;

    static {
        secret = System.getenv("Feed_JWT_Secret");
    }

    public JWTHandler(String username) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        token = Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenLife * 1000)).signWith(key, SignatureAlgorithm.HS256).compact();
    }

    /**
     * Gets the username from the JWT. Returns null if the signature is invalid.
     * @return The username from the JWT. Null if the signature is invalid
     */
    @JsonIgnore
    public String getUsername() {
        String username;
        try {
            username = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();
        } catch(SignatureException e) {
            System.out.println("JWT Incorrect!");       // TODO - Add logging
            username = null;
        } catch(ExpiredJwtException e) {
            System.out.println("JWT expired!");
            username = null;
        }
        return username;
    }

    /**
     * Checks whether a JWT is valid.
     * @param userService Used to check for the username.
     * @return True if the JWT is valid, false otherwise.
     */
    @JsonIgnore
    public boolean checkTokenValid(UserService userService) {
        String username = getUsername();
        if(username != null && userService.userExists(username)) {
            return true;
        } else {
            return false;
        }
    }

}

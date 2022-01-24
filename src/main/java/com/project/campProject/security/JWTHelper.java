package com.project.campProject.security;

import com.project.campProject.entities.User;
import com.project.campProject.requests.UserRequest;
import com.project.campProject.services.UserService;
import io.jsonwebtoken.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

public class JWTHelper {
    public static boolean isValidToken(String token){
        JwtTokenProvider prov = new JwtTokenProvider();
        try {
            Jwts.parser().setSigningKey("harmonieApp").parseClaimsJws(token);
            Date expiration = Jwts.parser().setSigningKey("harmonieApp").parseClaimsJws(token).getBody().getExpiration();
            return true;

        } catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return  false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }

    }

    public static boolean isValidLogin(User user, UserRequest loginRequest, PasswordEncoder passwordEncoder){

        String hashedPwdFromDB = user.getPassword();
        String passwordFromUser = passwordEncoder.encode(loginRequest.getPassword());
        String jwtToken = "";
        if (passwordEncoder.matches(loginRequest.getPassword(), hashedPwdFromDB)) {
            return true;
        } else {
            return false;
        }
    }

    public static String generateToken(User user, int validInMinutes ){
        String jwtToken = Jwts.builder().setSubject(Long.toString(user.getId()))
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + validInMinutes * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, "harmonieApp").compact();
        return jwtToken;
    }
}

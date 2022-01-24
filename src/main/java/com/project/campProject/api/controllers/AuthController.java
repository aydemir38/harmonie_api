package com.project.campProject.api.controllers;


import com.project.campProject.entities.User;
import com.project.campProject.requests.UserRequest;
import com.project.campProject.responses.AuthResponse;
import com.project.campProject.security.JWTHelper;
import com.project.campProject.security.JwtTokenProvider;
import com.project.campProject.services.UserService;
import io.jsonwebtoken.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, UserService userService,
                          PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }



    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserRequest loginRequest) {
        User user = userService.getOneUserByUserName(loginRequest.getUserName());
        if (JWTHelper.isValidLogin(user,loginRequest,passwordEncoder)){
            String token = JWTHelper.generateToken(user,60);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setMessage("Bearer " + token);
            authResponse.setUserId(user.getId());
            return authResponse;

        } else {
            AuthResponse response = new AuthResponse();
            response.setMessage("User not valid");
            return response;
        }


    }

    @GetMapping("/test")
    public String test(@RequestHeader("auth") String token){
        if (JWTHelper.isValidToken(token)){
            return "Data to user";
        } else {
            return "Not valid -- login";
        }

    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest registerRequest) {
        AuthResponse authResponse = new AuthResponse();
        if (userService.getOneUserByUserName(registerRequest.getUserName()) != null) {
            authResponse.setMessage("Username ist vorhanden !  Username already in use !");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }


        User user = new User();
        user.setUserName(registerRequest.getUserName());
        String pass = passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(pass);
        userService.saveOneUser(user);
        authResponse.setMessage(" Benutzer erfolgreich registriert .  User successfully registered.");
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);


    }


}

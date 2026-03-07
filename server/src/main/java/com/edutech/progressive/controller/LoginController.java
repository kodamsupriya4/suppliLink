package com.edutech.progressive.controller;

import com.edutech.progressive.dto.LoginRequest;
import com.edutech.progressive.dto.LoginResponse;
import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.jwt.JwtUtil;
// import com.edutech.progressive.jwt.JwtUtil;
import com.edutech.progressive.service.LoginService;

// import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

    
    private final AuthenticationManager authManager;
    private final JwtUtil jwt;
    private final LoginService loginService;

    @Autowired
    public LoginController(AuthenticationManager authManager,JwtUtil jwt, LoginService loginService) {
        this.authManager = authManager;
        this.jwt = jwt;
        this.loginService = loginService;
    }

    @PostMapping("/register")
    public ResponseEntity<Supplier> registerUser(@RequestBody Supplier user) {
        // return null;
        Supplier created = loginService.createUser(user);
        return ResponseEntity.status(201).body(created);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        // return null;
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String token = jwt.generateToken(principal, loginRequest.getUsername());
        return ResponseEntity.status(200).body(new LoginResponse(token));
    }
} 
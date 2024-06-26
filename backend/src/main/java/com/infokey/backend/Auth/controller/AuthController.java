package com.infokey.backend.Auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.infokey.backend.Auth.service.AuthService;
import com.infokey.backend.User.request.UserAccountLogin;
import com.infokey.backend.User.request.UserAccountRegister;


@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService userService) {
        this.authService = userService;
    }

    @PostMapping("/register")
    public  ResponseEntity<Void> register(@RequestBody UserAccountRegister userAccount) {
        authService.registerAccount(userAccount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserAccountLogin userAccountLogin) {
        String token = authService.loginAccount(userAccountLogin);
        return ResponseEntity.ok(token);
    }
}

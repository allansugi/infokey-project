package com.infokey.backend.Auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infokey.backend.User.UserAccount;
import com.infokey.backend.User.UserAccountLogin;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService userService) {
        this.authService = userService;
    }

    @PostMapping("/register")
    private ResponseEntity<Void> register(@RequestBody UserAccount userAccount) {
        authService.registerAccount(userAccount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestBody UserAccountLogin userAccountLogin) {
        String token = authService.loginAccount(userAccountLogin);
        return ResponseEntity.ok(token);
    }
}

package com.infokey.backend.Auth;

import com.infokey.backend.User.UserAccount;
import com.infokey.backend.User.UserAccountLogin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private ResponseEntity<Void> login(@RequestBody UserAccountLogin userAccountLogin) {
        authService.loginAccount(userAccountLogin);
        return ResponseEntity.noContent().build();
    }
}

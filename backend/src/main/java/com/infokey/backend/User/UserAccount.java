package com.infokey.backend.User;

import org.springframework.security.crypto.password.PasswordEncoder;

public record UserAccount(String id, String username, String email, String password) {
    public UserAccount withEncodedPassword(PasswordEncoder passwordEncoder) {
        return new UserAccount(id(), username(), email(), passwordEncoder.encode(password()));
    }
}

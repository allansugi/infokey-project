package com.infokey.backend.Password.controller;

import org.springframework.web.bind.annotation.RestController;

import com.infokey.backend.Password.builder.GeneratePasswordBuilder;
import com.infokey.backend.Password.builder.PasswordGenerator;
import com.infokey.backend.Password.request.PasswordRequirement;
import com.infokey.backend.Password.response.GeneratePasswordResponse;
import com.infokey.backend.Password.service.PasswordService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/v1/password")
public class PasswordController {

    private PasswordService service;

    public PasswordController(PasswordService service) {
        this.service = service;
    }

    @GetMapping("/generate")
    private ResponseEntity<GeneratePasswordResponse> generatePassword(
        @RequestParam String length, 
        @RequestParam String lower,
        @RequestParam String upper,
        @RequestParam String number,
        @RequestParam String special) 
    {
        PasswordRequirement requirement = new PasswordRequirement(
            Integer.parseInt(length), 
            Boolean.parseBoolean(lower), 
            Boolean.parseBoolean(upper), 
            Boolean.parseBoolean(number),
            Boolean.parseBoolean(special)
        );

        GeneratePasswordResponse response = service.generateRandomPassword(requirement);

        return ResponseEntity.ok(response);
    }
}

package com.infokey.backend.Password.service;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.infokey.backend.Password.builder.GeneratePasswordBuilder;
import com.infokey.backend.Password.builder.PasswordBuilder;
import com.infokey.backend.Password.builder.PasswordGenerator;
import com.infokey.backend.Password.request.PasswordRequirement;
import com.infokey.backend.Password.response.GeneratePasswordResponse;

@Service
public class PasswordServiceSecure implements PasswordService {

    /**
     * configure password info from password requirement 
     * into password builder and add secure random
     * @param builder
     * @param requirement
     */
    private void constructPassword(PasswordBuilder builder, PasswordRequirement requirement) {
        builder.setLength(requirement.length());
        builder.setLower(requirement.lowercase());
        builder.setUpper(requirement.uppercase());
        builder.setNumber(requirement.numeric());
        builder.setSpecial(requirement.special());
        builder.setRandom(new SecureRandom());
    }

    @Override
    public GeneratePasswordResponse generateRandomPassword(PasswordRequirement requirement) {
        GeneratePasswordBuilder builder = new GeneratePasswordBuilder();
        constructPassword(builder, requirement);
        PasswordGenerator generator = builder.build();
        String generatedPassword = generator.generate();
        return new GeneratePasswordResponse(generatedPassword);
    }

}

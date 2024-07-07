package com.infokey.backend.Password.service;

import com.infokey.backend.Password.request.PasswordRequirement;
import com.infokey.backend.Password.response.GeneratePasswordResponse;

public interface PasswordService {
    GeneratePasswordResponse generateRandomPassword(PasswordRequirement requirement);
}

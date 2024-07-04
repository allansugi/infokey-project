package com.infokey.backend.Auth.service;

import com.infokey.backend.Auth.LoginResponse;
import com.infokey.backend.User.request.UserAccountLogin;
import com.infokey.backend.User.request.UserAccountRegister;

public interface AuthService {
    void registerAccount(UserAccountRegister userAccount);
    LoginResponse loginAccount(UserAccountLogin userAccountLogin);
}

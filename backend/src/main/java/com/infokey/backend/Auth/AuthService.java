package com.infokey.backend.Auth;

import com.infokey.backend.User.UserAccountLogin;
import com.infokey.backend.User.UserAccountRegister;

public interface AuthService {
    void registerAccount(UserAccountRegister userAccount);
    String loginAccount(UserAccountLogin userAccountLogin);
}

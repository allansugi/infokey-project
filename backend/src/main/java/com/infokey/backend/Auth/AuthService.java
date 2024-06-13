package com.infokey.backend.Auth;

import com.infokey.backend.User.UserAccount;
import com.infokey.backend.User.UserAccountLogin;

public interface AuthService {
    void registerAccount(UserAccount userAccount);
    String loginAccount(UserAccountLogin userAccountLogin);
}

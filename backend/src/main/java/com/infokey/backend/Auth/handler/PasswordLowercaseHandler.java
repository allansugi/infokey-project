package com.infokey.backend.Auth.handler;

import com.infokey.backend.User.request.UserAccountRegister;

public class PasswordLowercaseHandler extends RegisterHandler {

    @Override
    public boolean check(UserAccountRegister register) {
        if (register.password().matches("^.*[a-z]+.*$")) {
            return checkNext(register);
        }

        return false;
    }
    
}

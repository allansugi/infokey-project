package com.infokey.backend.Auth.handler;

import com.infokey.backend.User.request.UserAccountRegister;

public class PasswordLengthHandler extends RegisterHandler {

    @Override
    public boolean check(UserAccountRegister register) {
        if (register.password().length() >= 8) {
            return checkNext(register);
        }
        
        return false;
    }

}

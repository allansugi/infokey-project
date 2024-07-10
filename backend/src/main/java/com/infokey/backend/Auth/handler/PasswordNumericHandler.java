package com.infokey.backend.Auth.handler;

import com.infokey.backend.User.request.UserAccountRegister;

public class PasswordNumericHandler extends RegisterHandler {

    @Override
    public boolean check(UserAccountRegister register) {
        if (register.password().matches("^.*[0-9]+.*$")) {
            return checkNext(register);
        }
        
        return false;
    }

}

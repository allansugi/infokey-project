package com.infokey.backend.Auth.handler;

import com.infokey.backend.User.request.UserAccountRegister;

public class PasswordUppercaseHandler extends RegisterHandler {
    
    @Override
    public boolean check(UserAccountRegister register) {
        if (register.password().matches("^.*[A-Z]+.*$")) {
            return checkNext(register);
        }

        return false;
    }

}

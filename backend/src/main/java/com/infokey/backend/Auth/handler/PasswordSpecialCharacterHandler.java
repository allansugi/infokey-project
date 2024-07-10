package com.infokey.backend.Auth.handler;

import com.infokey.backend.User.request.UserAccountRegister;

public class PasswordSpecialCharacterHandler extends RegisterHandler {

    @Override
    public boolean check(UserAccountRegister register) {
        if (register.password().matches("^.*[!@#\\$%\\^\\&*\\)\\(+=._-`~{}\\[\\]:;\"'<>,/?|\\\\]+.*$")) {
            return checkNext(register);
        }

        return false;
    }

}

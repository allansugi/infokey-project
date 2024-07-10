package com.infokey.backend.Auth.handler;

import com.infokey.backend.User.request.UserAccountRegister;

public abstract class RegisterHandler {
    private RegisterHandler nextHandler;

    public void setNextHandler(RegisterHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    public static RegisterHandler link(RegisterHandler first, RegisterHandler... chain) {
        RegisterHandler head = first;
        for (RegisterHandler handler: chain) {
            head.setNextHandler(handler);
            head = handler;
        }
        return first;
    }

    public abstract boolean check(UserAccountRegister register);

    public boolean checkNext(UserAccountRegister register) {
        if (nextHandler == null) {
            return true;
        }
        return nextHandler.check(register);
    }
}

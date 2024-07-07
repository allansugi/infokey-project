package com.infokey.backend.Password.request;

public record PasswordRequirement(int length, boolean lowercase, boolean uppercase, boolean numeric, boolean special) {

}

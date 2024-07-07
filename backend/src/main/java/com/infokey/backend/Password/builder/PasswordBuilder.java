package com.infokey.backend.Password.builder;

import java.util.Random;

public interface PasswordBuilder {
    void setLength(int length);
    void setLower(boolean chooseLower);
    void setUpper(boolean chooseUpper);
    void setSpecial(boolean chooseSpecial);
    void setRandom(Random random);
    void setNumber(boolean number);
}

package com.infokey.backend.Password.builder;

import java.util.Random;

public class GeneratePasswordBuilder implements PasswordBuilder {

    private int length;
    private boolean lower;
    private boolean upper;
    private boolean number;
    private boolean special;
    private Random random;

    @Override
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void setLower(boolean chooseLower) {
       this.lower = chooseLower;
    }

    @Override
    public void setUpper(boolean chooseUpper) {
       this.upper = chooseUpper;
    }

    @Override
    public void setSpecial(boolean chooseSpecial) {
        this.special = chooseSpecial;
    }

    @Override
    public void setRandom(Random random) {
        this.random = random;
    }

    @Override
    public void setNumber(boolean number) {
        this.number = number;
    }

    public PasswordGenerator build() {
        return new PasswordGenerator(length, lower, upper, number, special, random);
    }
}

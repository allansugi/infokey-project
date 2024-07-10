package com.infokey.backend.Password.builder;

import java.util.Random;

public class PasswordGenerator {
    private int length;
    private boolean lower;
    private boolean upper;
    private boolean number;
    private boolean special;
    private Random random;

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SPECIAL = "!#$%&()*+,-./:;<=>?@[]^_`{|}~";
    private static final String NUMBER = "0123456789";

    public PasswordGenerator(
        int length, 
        boolean lower, 
        boolean upper, 
        boolean number,
        boolean special,
        Random random) 
    {
        this.length = length;
        this.lower = lower;
        this.upper = upper;
        this.number = number;
        this.special = special;
        this.random = random;
    }

    public String generate() {
        StringBuilder password = new StringBuilder(length);
        String allString = "";
        if (lower) {
            allString += LOWER;
            password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        }

        if (upper) {
            allString += UPPER;
            password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        }

        if (number) {
            allString += NUMBER;
            password.append(NUMBER.charAt(random.nextInt(NUMBER.length())));
        }

        if (special) {
            allString += SPECIAL;
            password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));
        }

        int count = password.length();

        for (int i = count; i <= length; i++) {
            password.append(allString.charAt(random.nextInt(allString.length())));
        }

        return shuffle(password.toString());
    }

    /**
     * perform random swapping for n times
     * where n is input string length
     * @param input
     * @return random string
     */
    private String shuffle(String input) {
        char[] arr = input.toCharArray();
        
        for (int i = arr.length - 1; i > 0; i--) {
            int index = random.nextInt(i);
            char temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }

        return String.valueOf(arr);
    }
}

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
    private static String ALL_STRING =  "";

    public static void setALL_STRING(String aLL_STRING) {
        ALL_STRING += aLL_STRING;
    }

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
        this.special = special;
        this.random = random;
    }

    public String generate() {
        StringBuilder password = new StringBuilder(length);

        if (lower) {
            setALL_STRING(LOWER);
            password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        }

        if (upper) {
            setALL_STRING(UPPER);
            password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        }

        if (number) {
            setALL_STRING(NUMBER);
            password.append(NUMBER.charAt(random.nextInt(NUMBER.length())));
        }

        if (special) {
            setALL_STRING(SPECIAL);
            password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));
        }

        int count = password.length();

        for (int i = count; i <= length; i++) {
            password.append(ALL_STRING.charAt(random.nextInt(ALL_STRING.length())));
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

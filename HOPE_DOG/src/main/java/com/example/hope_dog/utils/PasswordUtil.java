package com.example.hope_dog.utils;

import java.util.Random;

public class PasswordUtil {

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
    private static final int PASSWORD_LENGTH = 10;
    private static final Random random = new Random();

    public static String generateTempPassword() {
        StringBuilder pw = new StringBuilder();
        int length = CHARS.length();

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            pw.append(CHARS.charAt(random.nextInt(length)));
        }

        return pw.toString();
    }
}

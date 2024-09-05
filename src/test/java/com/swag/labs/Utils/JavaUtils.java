package com.swag.labs.Utils;

public class JavaUtils {
    private static java.util.Random random = new java.util.Random();

    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String ALPHANUMERIC = ALPHABETS + NUMBERS;
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+";

    public static String generateRandom(int length, String type) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        String characters = "";
        switch (type.toLowerCase()) {
            case "name":
                characters = ALPHABETS;
                break;
            case "number":
                characters = NUMBERS;
                break;
            case "alphanumeric":
                characters = ALPHANUMERIC;
                break;
            default:
                characters = ALPHABETS + SPECIAL_CHARACTERS;
        }

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        return sb.toString();
    }
}

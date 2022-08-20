package org.util;

import java.util.Scanner;

public class Input {

        static Scanner scanner = new Scanner(System.in);
    public static String scanner() {
        return scanner.nextLine();
    }

    public static long longScanner() {
        String input = scanner.nextLine();
        if (isDigit(input))
            return Long.parseLong(input);
        else return -1;
    }

    private static boolean isDigit(String input) {
        if (input.equals("")||input.charAt(0) == '-')
            return false;
        for (char c : input.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }
    public static int intScanner() {
        String input = scanner.nextLine();
        if (isDigit(input))
            return Integer.parseInt(input);
        else return -1;
    }
}

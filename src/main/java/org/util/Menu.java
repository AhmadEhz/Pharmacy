package org.util;

public class Menu {
    static boolean exit = false;

    public static void mainMenu() {
        while (!exit) {
            Print.mainMenu();
            switch (Input.scanner()) {
                case "1" -> signInMenu();
                case "2" -> signUpMenu();
                case "0" -> exit = true;
                default -> Print.invalidEntry();
            }
        }
    }

    private static void signUpMenu() {
        String username = enterUsername();
        String password = enterPassword();
        // TODO : check username and password

    }


    private static void signInMenu() {
        String username = enterUsername();
        String password = enterPassword();
        // TODO : check username and password
    }

    private static String enterPassword() {
        Print.enterPassword();
        return Input.scanner();
    }

    private static String enterUsername() {
        Print.enterUsername();
        return Input.scanner();
    }
}

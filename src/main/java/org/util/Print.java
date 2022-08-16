package org.util;

public class Print {
    public static void welcome() {
        System.out.println("Welcome!");
    }
    public static void enterNumber() {
        System.out.println("Enter a number:");
    }
    public static void mainMenu() {
        System.out.println("""
                1- sign in
                2- sign up
                0- exit""");
        enterNumber();
    }
    public static void enterUsername() {
        System.out.println("Enter your username:");
    }
    public static void enterPassword() {
        System.out.println("Enter your password:");
    }

    public static void invalidEntry() {
        System.out.println("Invalid Entry!");
    }
}

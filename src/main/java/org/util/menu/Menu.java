package org.util.menu;

import org.util.Input;
import org.util.Print;

public class Menu {

    public static void mainMenu() {
        print("Welcome!");
        while (true) {
            Print.mainMenu();
            String input = Input.scanner();
            switch (input) {
                case "1" -> AdminMenu.login();

                case "2" -> PatientMenu.login();
                case "3" -> PatientMenu.signUp();
                case "0" -> {
                    return;
                }
                default -> print("Invalid entry!");
            }
        }
    }

    public static void print(Object o) {
        System.out.println(o);
    }

}

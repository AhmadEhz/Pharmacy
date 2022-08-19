package org.util.menu;

import org.entity.Admin;
import org.entity.Patient;
import org.service.AdminService;
import org.service.PatientService;
import org.util.Input;
import org.util.Print;

public class Menu {
    static boolean exit = false;
    static PatientService patientService = new PatientService();
    static AdminService adminService = new AdminService();

    public static void mainMenu() {
        Print.welcome();
        while (true) {
            Print.mainMenu();
            String input = Input.scanner();
            switch (input) {
                case "1" -> {
                    AdminMenu.login();
                }
                case "2" -> PatientMenu.login();
                case "3" -> PatientMenu.signUp();
                case "0" -> {
                    return;
                }
                default -> Print.invalidEntry();
            }
        }
    }

    public static String enterUsername(boolean checkUserName) {
        while (true) {
            Print.enterUsername();
            String input = Input.scanner();
            if(input.equals("0"))
                return null;
            Patient patient = new Patient();
            patient.setUsername(input);
            if (checkUserName && patientService.checkUsername(patient)) {
                Print.usernameExist();
            } else return input;
        }

    }

    private static boolean checkUsername() {
        return true;
    }

    private static boolean checkUser(String password) {
        return true;
    }

}

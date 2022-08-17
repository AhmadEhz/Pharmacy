package org.util;

import org.entity.Patient;
import org.service.PatientService;

public class Menu {
    static boolean exit = false;

    public static void mainMenu() {
        Print.welcome();
        while (true) {
            Print.mainMenu();
            String input = Input.scanner();
            switch (input) {
                case "1" -> {
                    adminLogin();
                }
                case "2" -> patientLogin();
                case "0" -> {
                    return;
                }
                default -> Print.invalidEntry();
            }
        }
    }

    private static void adminLogin() {
        while(true)
        Print.enterUsername();
    }

    public static void patientLogin() {
        while (true) {
            Print.enterUsername();
            String username = Input.scanner();
            String password;
            if (username.equals("0"))
                return;
            Patient patient = null;
            if (checkUsername()) {
                password = enterPassword(username);
                if (checkUser(password)) {
                    patient = new Patient(username, password);
                    patientMenu(patient);
                    return;
                }
                else Print.invalidPassword();
            }
        }
    }

    private static void patientMenu(Patient patient) {
        while (!exit) {
            PatientService patientService = new PatientService();
            Print.patientMenu();
            String input = Input.scanner();
            switch (input) {
                case "1" -> {

                }
                case "2" -> Print.patientPrescription(patientService.seePrescriptions(patient.getId()));

                case "3" -> {
                    String sss = null;
                }
                case "4" -> {

                }
                case "0" -> exit = true;
            }
        }
        exit = false;
    }

    private static boolean checkUsername() {
        return true;
    }

    private static boolean checkUser(String password) {
        return true;
    }


    private static String enterPassword(String username) {
        Print.enterPassword();
        return Input.scanner();
    }
}

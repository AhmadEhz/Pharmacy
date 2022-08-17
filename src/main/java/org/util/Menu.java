package org.util;

import org.entity.Admin;
import org.entity.Patient;
import org.service.AdminService;
import org.service.PatientService;

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
                    adminLogin();
                }
                case "2" -> patientLogin();
                case "3" -> patientSignUp();
                case "0" -> {
                    return;
                }
                default -> Print.invalidEntry();
            }
        }
    }

    private static void patientSignUp() {
        while (true) {
            Patient patient = new Patient();
            Print.enterUsername();
            String input = Input.scanner();
            patient.setUsername(input);
            if (patientService.checkUsername(patient)) ;

        }
    }

    public static String enterUsername(boolean checkUserName) {
        while (true) {
            Print.enterUsername();
            String input = Input.scanner();
            Print.enterUsername();
            Patient patient = new Patient();
            patient.setUsername(input);
            if (checkUserName && patientService.checkUsername(patient)) {
                Print.usernameExist();
            } else return input;
        }

    }

    private static void adminLogin() {
        while (true) {
            Admin admin = new Admin();
            admin.setUsername(enterUsername(false));
            admin = enterPassword(admin);
            if(admin==null)
                continue;
            adminMenu(admin);
            return;
        }


    }

    private static void adminMenu(Admin admin) {

    }

    public static void patientLogin() {
        while (true) {
            Patient patient = new Patient();
            patient.setUsername(enterUsername(false));
            patient = enterPassword(patient);
            if(patient==null)
                continue;
            patientMenu(patient);
            return;
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


    private static Patient enterPassword(Patient patient) {
        while (true) {
            Print.enterPassword();
            String input = Input.scanner();
            if (input.equals("0"))
                return null;
            patient.setPassword(input);
            if (patientService.isExist(patient))
                return patient;
            else Print.invalidPassword();
        }
    }

    private static Admin enterPassword(Admin admin) {
        while (true) {
            Print.enterPassword();
            String input = Input.scanner();
            if (input.equals("0"))
                return null;
            admin.setPassword(input);
            if(adminService.isExist(admin))
                return admin;
            else Print.invalidPassword();
        }
    }
}

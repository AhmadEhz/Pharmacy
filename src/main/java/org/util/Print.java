package org.util;

import org.entity.PrescriptionList;

public class Print {
    public static void welcome() {
        System.out.println("Welcome!");
    }
    public static void enterNumber() {
        System.out.println("Enter a number:");
    }
    public static void enterUsername() {
        System.out.println("Enter your username (0 for exit) :");
    }
    public static void enterPassword() {
        System.out.println("Enter your password (0 for exit) :");
    }

    public static void invalidEntry() {
        System.out.println("Invalid Entry!");
    }

    public static void patientMenu() {
        System.out.println("""
                1- Add prescription
                2- See confirmed prescriptions
                3- Edit prescription
                4- Delete prescription
                0- Exit""");
        enterNumber();
    }

    public static void patientPrescription(PrescriptionList prescriptionList) {
        System.out.println(prescriptionList);
    }

    public static void mainMenu() {
        System.out.println("""
                1- Login admin
                2- Login patient
                0- Exit""");
        enterNumber();
    }

    public static void invalidPassword() {
        System.out.println("Invalid password!");
    }

    public static void usernameExist() {
        System.out.println("Username is exist!");
    }
}

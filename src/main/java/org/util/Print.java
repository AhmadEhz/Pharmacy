package org.util;

import org.entity.Prescription;

import javax.naming.spi.ObjectFactory;

public class Print {

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
                3- Edit pending prescription
                4- Delete pending prescription
                5- See rejected prescriptions
                0- Exit""");
        enterNumber();
    }

    public static void mainMenu() {
        System.out.println("""
                1- Login admin
                2- Login patient
                3- Signup patient
                0- Exit""");
        enterNumber();
    }

    public static void adminMenu() {
        System.out.println("""
                1- Show all prescriptions
                2- Show all pending prescriptions
                3- Show all rejected prescriptions
                4- Show all confirmed prescriptions
                0- Exit
                """);
        enterNumber();
    }

    public static void editLoadedPrescription() {
        System.out.println("""
                1- Edit all drugs
                2- Edit one drug
                3- Set prescription status
                0- Submit prescription and exit
                X- Exit and abort""");
        enterNumber();
    }

    public static void editDrug(boolean doesExist) {
        System.out.println("""
                1- Set price
                2- Change to\040""" + doesExistToString(doesExist) +"\n"+ """
                0- Submit drug and exit
                X- Exit and abort""");
        enterNumber();
    }

    private static String doesExistToString(boolean doesExist) {
        if (doesExist)
            return "Not available";
        else return "Available";
    }

    public static void setPrescriptionStatus() {
        System.out.println("""
                1- Confirm
                2- Reject
                3- Pending
                0- Exit.""");
    }
}

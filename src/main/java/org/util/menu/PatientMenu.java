package org.util.menu;

import org.entity.*;
import org.service.DrugService;
import org.service.PatientService;
import org.service.PrescriptionService;
import org.util.Input;
import org.util.Print;
import org.util.Utility;

public class PatientMenu {
    private static final PatientService patientService = new PatientService();
    private static final PrescriptionService prescriptionService = new PrescriptionService();
    private static final DrugService drugService = new DrugService();

    static void signUp() {
        print("Enter your name (0 for exit) :");
        String name = Input.scanner();
        if(name.equals("0"))
            return;
        while (true) {
            Patient patient = new Patient();
            patient.setName(name);
            Print.enterUsername();
            String input = Input.scanner();
            patient.setUsername(input);
            if (!patientService.checkUsername(patient)) {
                patient = enterPassword(patient, false);
                if (patient == null)//If patient is null, it means user entered 0 to exit.
                    continue;
                patientService.add(patient);
                print("Created!");
                patient.setId(patientService.getLastGeneratedId());
                menu(patient);
                return;
            } else {
                print("Username is exist!");
            }

        }
    }

    static void login() {
        while (true) {
            print("Enter username (0 to exit) :");
            Patient patient = new Patient();
            String username = Input.scanner();
            if (username.equals("0"))
                break;
            patient.setUsername(username);
            if (!patientService.checkUsername(patient)) {
                print("Username not exist!");
                continue;
            }
            patient = enterPassword(patient, true);
            if (patient == null)//If patient is null, it means user entered 0 to exit.
                continue;
            menu(patient);
            return;
        }
    }

    private static void menu(Patient patient) {
        print("Welcome " + patient.getName() + "!");
        while (true) {
            PatientService patientService = new PatientService();
            Print.patientMenu();
            String input = Input.scanner();
            switch (input) {
                case "1" -> addPrescriptionMenu(patient);
                case "2" -> print(patientService.loadPrescriptions(patient.getId(), PrescriptionStatus.CONFIRMED));
                case "3" -> editPrescriptionMenu(patient);
                case "4" -> deletePrescriptionMenu(patient);
                case "5" -> print(patientService.loadPrescriptions(patient.getId(), PrescriptionStatus.REJECTED));
                case "0" -> {
                    return;
                }
                default -> print("Invalid Entry!");
            }
        }
    }

    private static void deletePrescriptionMenu(Patient patient) {
        PrescriptionList prescriptionList = prescriptionService.loadAll(patient.getId(), PrescriptionStatus.PENDING);
        if (prescriptionList.isEmpty()) {
            print("There is no prescription!");
            return;
        }
        print(prescriptionList);
        while (true) {
            print("Enter your prescription id to delete (0 for exit) :");
            int indexOfPrescriptionList = Input.intScanner();
            switch (indexOfPrescriptionList) {
                case -1 -> print("Invalid Entry!");
                case 0 -> {
                    return;
                }
                default -> {
                    long prescriptionID = Utility.getPrescriptionId(prescriptionService.loadAll(patient.getId(), PrescriptionStatus.PENDING), indexOfPrescriptionList);
                    Prescription prescription = new Prescription(prescriptionID, patient.getId());
                    prescription.setStatus(PrescriptionStatus.PENDING);
                    if (prescriptionService.isExist(prescription, true)) {
                        prescriptionService.remove(prescription);
                        print("Removed!");
                        return;
                    } else print("This prescription is not exist!");
                }
            }
        }
    }

    private static void editPrescriptionMenu(Patient patient) {
        PrescriptionList prescriptionList = prescriptionService.loadAll(patient.getId(), PrescriptionStatus.PENDING);
        if (prescriptionList.isEmpty()) {
            print("There is no prescription to show!");
            return;
        }
        print(prescriptionList);
        while (true) {
            print("Enter your prescription id (0 for exit) :");
            int indexOfPrescriptionList = Input.intScanner();//If user entered invalid input, this method returns -1.
            switch (indexOfPrescriptionList) {
                case -1 -> print("Invalid entry!");
                case 0 -> {
                    return;
                }
                default -> {
                    long prescriptionId = Utility.getPrescriptionId(prescriptionService.loadAll(patient.getId(), PrescriptionStatus.PENDING), indexOfPrescriptionList);
                    Prescription prescription = new Prescription(prescriptionId, patient.getId());
                    prescription.setStatus(PrescriptionStatus.PENDING);
                    if (prescriptionService.isExist(prescription, true)) {
                        editLoadedPrescriptionMenu(prescriptionService.load(prescription, true));
                        return;
                    } else print("This prescription is not exist!");
                }
            }
        }
    }

    private static void editLoadedPrescriptionMenu(Prescription loadedPrescription) {
        print(loadedPrescription);
        while (true) {
            print("Enter drug id to edit (0 to save and exit) :");
            int indexOfDrugList = Input.intScanner();
            switch (indexOfDrugList) {
                case -1 -> print("Invalid entry!");
                case 0 -> {
                    return;
                }
                default -> {
                    long drugId = Utility.getDrugId(loadedPrescription, indexOfDrugList);
                    Drug drug = new Drug(drugId, loadedPrescription.getId());
                    if (drugService.isExist(drug))
                        editDrugMenu(drugService.load(drug));
                    else print("the Drug with this id not found!");
                }
            }

        }
    }

    private static void editDrugMenu(Drug drug) {
        print("Add your drug name (X to delete this drug, 0 to go back) :");
        String input = Input.scanner();
        if (input.equals("0"))
            return;
        if (input.equalsIgnoreCase("x")) {
            drugService.remove(drug);
            print("Deleted!");
            return;
        }

        drug.setName(input);
        drugService.editName(drug, input);
        print("Edited!");
    }

    private static void addPrescriptionMenu(Patient patient) {
        Prescription prescription = new Prescription(patient.getId(), PrescriptionStatus.PENDING);
        DrugList drugList = new DrugList(10, true);
        while (!drugList.isFull()) {
            Drug drug = addDrugMenu();
            if (drug == null)//When drug is null, it means the user entered 0 to exit
                break;
            drugList.add(drug);
        }
        if (drugList.isEmpty())
            print("Not added any drug!\nAborted!");
        else {
            prescription.setDrugs(drugList);
            prescriptionService.add(prescription);
            print("Added!");
        }
    }

    private static Drug addDrugMenu() {
        while (true) {
            Drug drug = new Drug();
            print("Enter your drug name (0 to save and go back) :");
            String input = Input.scanner();
            if (input.equals("0"))
                return null;
            if (input.equals(""))
                continue;
            drug.setName(input);
            return drug;
        }
    }

    private static Patient enterPassword(Patient patient, boolean checkPassword) {
        while (true) {
            print("Enter your password (0 for exit) :");
            String input = Input.scanner();
            if (input.equals("0"))
                return null;
            patient.setPassword(input);
            if (!checkPassword)
                return patient;
            if (patientService.isExist(patient))
                return patient;
            else print("Invalid password!");
        }
    }

    private static void print(Object o) {
        System.out.println(o);
    }

}

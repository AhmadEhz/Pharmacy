package org.util.menu;

import org.entity.Drug;
import org.entity.DrugList;
import org.entity.Patient;
import org.entity.Prescription;
import org.service.PatientService;
import org.service.PrescriptionService;
import org.util.Input;
import org.util.Print;

public class PatientMenu {
    static PatientService patientService = new PatientService();
    static PrescriptionService prescriptionService = new PrescriptionService();

    static void signUp() {
        Print.enterName();
        String name = Input.scanner();
        while (true) {
            Patient patient = new Patient();
            patient.setName(name);
            Print.enterUsername();
            String input = Input.scanner();
            patient.setUsername(input);
            if (!patientService.checkUsername(patient)) {
                patient = enterPassword(patient, false);
                if(patient==null)
                    continue;
                patientService.add(patient);
                Print.created();
                menu(patient);
                return;
            }
            else Print.usernameExist();

        }
    }

    static void login() {
        while (true) {
            Patient patient = new Patient();
            String username = Menu.enterUsername(false);
            if(username==null)
                break;
            patient.setUsername(username);
            patient = enterPassword(patient, true);
            if (patient == null)
                continue;
            menu(patient);
            return;
        }
    }

    private static void menu(Patient patient) {
        boolean exit = false;
        Print.welcomeUser(patient.getName());
        while (!exit) {
            PatientService patientService = new PatientService();
            Print.patientMenu();
            String input = Input.scanner();
            switch (input) {
                case "1" -> addPrescriptionMenu(patient);
                case "2" -> Print.patientPrescription(patientService.loadPrescriptions(patient.getId()));
                case "3" ->{}
                case "4" ->{}
                case "0" -> {return;}
            }
        }
    }

    private static void addPrescriptionMenu(Patient patient) {
        Prescription prescription = new Prescription(patient.getId());
        DrugList drugList = new DrugList(10, true);
        for (int i = 0; !drugList.isFull(); i++) {
            Drug drug = addDrugMenu();
            if (drug == null)
                break;
            drugList.add(drug);
        }
        prescription.setDrugs(drugList);
        prescriptionService.add(prescription);
        Print.added();
    }

    private static Drug addDrugMenu() {
        while (true) {
            Drug drug = new Drug();
            Print.addDrug();
            String input = Input.scanner();
            if (input.equals("0"))
                return null;
            if(input.equals(""))
                continue;
            drug.setName(input);
            return drug;
        }
    }

    private static Patient enterPassword(Patient patient, boolean checkPassword) {
        while (true) {
            Print.enterPassword();
            String input = Input.scanner();
            if (input.equals("0"))
                return null;
            patient.setPassword(input);
            if (!checkPassword)
                return patient;
            if (patientService.isExist(patient))
                return patient;
            else Print.invalidPassword();
        }
    }
}

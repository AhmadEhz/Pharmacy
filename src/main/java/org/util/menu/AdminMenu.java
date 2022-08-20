package org.util.menu;

import org.entity.*;
import org.service.AdminService;
import org.service.PrescriptionService;
import org.util.Input;
import org.util.Print;
import org.util.Utility;


public class AdminMenu {
    private static final AdminService adminService = new AdminService();
    private static final PrescriptionService prescriptionService = new PrescriptionService();
    //static DrugService drugService = new DrugService();

    static void login() {
        while (true) {
            Admin admin = new Admin();
            print("Enter username: (0 for exit) :");
            String username = Input.scanner();
            if (username.equals("0")) break;
            admin.setUsername(username);
            if (!adminService.checkUsername(admin)) {
                print("Username not exist!");
            } else {
                admin = enterPassword(admin);
                if (admin == null) continue;
                menu(admin);
                return;
            }
        }
    }

    private static void menu(Admin admin) {
        print("Welcome " + admin.getName() + '!');
        while (true) {
            Print.adminMenu();
            String input = Input.scanner();
            switch (input) {
                case "1" -> showPrescriptions(prescriptionService.loadAll(), true);
                case "2" -> showPrescriptions(prescriptionService.loadAll(PrescriptionStatus.PENDING), true);
                case "3" -> showPrescriptions(prescriptionService.loadAll(PrescriptionStatus.REJECTED), false);
                case "4" -> showPrescriptions(prescriptionService.loadAll(PrescriptionStatus.CONFIRMED), false);
                case "0" -> {
                    return;
                }
                default -> print("Invalid entry!");
            }
        }
    }

    private static void showPrescriptions(PrescriptionList prescriptionList, boolean editable) {
        print(prescriptionList);
        if (!editable) {
            print("Enter to continue");
            Input.scanner();
        }
        while (editable) {
            print("Enter your prescription id (0 for exit) :");
            int indexOfPrescriptionList = Input.intScanner();//If user entered invalid input, this method returns -1.
            switch (indexOfPrescriptionList) {
                case -1 -> print("Invalid entry!");
                case 0 -> editable = false;
                default -> {
                    long prescriptionId = Utility.getPrescriptionId(prescriptionList, indexOfPrescriptionList);
                    Prescription prescription = new Prescription(prescriptionId);
                    if (prescriptionService.isExist(prescription, false)) {
                        prescription = prescriptionService.load(prescription, false);
                        if (prescription.getStatus() != PrescriptionStatus.PENDING) {
                            print("This prescription is " + prescription.getStatus() + " and cannot change!");
                            continue;
                        }
                        editPrescription(prescription);
                        return;
                    } else print("This prescription is not exist!");
                }
            }
        }
    }

    private static void editPrescription(Prescription loadedPrescription) {
        if (loadedPrescription.isEmpty()) {
            print("This prescription is Empty\nEnter to continue");
            Input.scanner();
            return;
        }
        while (true) {
            print(loadedPrescription);
            if (loadedPrescription.getStatus() == PrescriptionStatus.PENDING)
                print("HINT: to show price and does exist, set status to confirmed");
            if (loadedPrescription.getStatus() != PrescriptionStatus.PENDING)
                print("NOTE: after submit, you can't change the status");
            Print.editLoadedPrescription();
            switch (Input.scanner()) {
                case "1" -> editAllDrugs(loadedPrescription.getDrugs());
                case "2" -> {//Edit one drug.
                    int drugId = selectDrug();
                    if (drugId == -1)
                        continue;
                    Drug drug = loadedPrescription.getDrug(drugId);
                    if (drug == null) {
                        print("The drug with this id not exist!");
                        continue;
                    }
                    editDrug(drug);
                    loadedPrescription.getDrugs().set(drug, drugId);
                    updateTotalPrice(loadedPrescription);
                }
                case "3" -> {
                    PrescriptionStatus prescriptionStatus = setPrescriptionStatus();
                    if (prescriptionStatus != null)
                        loadedPrescription.setStatus(prescriptionStatus);
                }
                case "0" -> {
                    prescriptionService.update(loadedPrescription);//Update prescription and all drugs
                    print("Set!");
                    return;
                }
                case "x" -> {
                    print("Abort!");
                    return;
                }
                default -> {
                    print("Invalid entry!");
                    continue;
                }
            }
            updateTotalPrice(loadedPrescription);
        }
    }

    private static int selectDrug() {
        while (true) {
            print("Enter drug id to edit (0 to exit) :");
            int drugId = Input.intScanner();
            if (drugId == -1) {
                print("Invalid entry!");
                continue;
            }
            return drugId - 1;
        }
    }

    private static void editAllDrugs(DrugList drugList) {
        for (int i = 0; i < drugList.length(); i++) {
            drugList.set(editDrug(drugList.load(i)), i);
        }
    }

    private static Drug editDrug(Drug drug) {
        while (true) {
            print("Drug: " + drug);
            Print.editDrug(drug.getDoesExist());
            String input = Input.scanner().toLowerCase();
            switch (input) {
                case "1" -> drug.setPrice(setDrugPrice());
                case "2" -> drug.setDoesExist(!drug.getDoesExist());
                case "0" -> {
                    return drug;
                }
                default -> print("Invalid entry!");
            }
        }
    }

    private static int setDrugPrice() {
        while (true) {
            print("Enter drug price");
            int price = Input.intScanner();
            if (price == -1) print("Invalid entry!");
            else return price;
        }
    }

    private static PrescriptionStatus setPrescriptionStatus() {
        while (true) {
            Print.setPrescriptionStatus();
            switch (Input.scanner()) {
                case "1":
                    print("Set!");
                    return PrescriptionStatus.CONFIRMED;

                case "2":
                    print("Set!");
                    return PrescriptionStatus.REJECTED;

                case "3":
                    print("set!");
                    return PrescriptionStatus.PENDING;

                case "0":
                    return null;
                default:
                    print("Invalid entry!");
                    break;
            }
        }

    }

    private static Admin enterPassword(Admin admin) {
        while (true) {
            Print.enterPassword();
            String input = Input.scanner();
            if (input.equals("0")) return null;
            admin.setPassword(input);
            if (adminService.isExist(admin)) return admin;
            else print("Invalid password!");
        }
    }

    private static void print(Object o) {
        System.out.println(o);
    }

    private static void updateTotalPrice(Prescription prescription) {
        int sum = 0;
        for (int i = 0; i < prescription.getNumberOfDrugs(); i++) {
            sum += prescription.getDrug(i).getPrice();
        }
        prescription.setTotalPrice(sum);
    }
}

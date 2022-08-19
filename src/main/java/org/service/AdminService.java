package org.service;

import org.entity.*;
import org.repository.AdminRepository;

import java.sql.SQLException;

public class AdminService {
    private AdminRepository adminRepository = new AdminRepository();
    private PrescriptionService prescriptionService = new PrescriptionService();
    DrugService drugService = new DrugService();
    public PrescriptionList seeAllPrescription() {
            return prescriptionService.loadAll();
    }
    public void setPrescriptionStatus(Prescription prescription, PrescriptionStatus status) {
        prescriptionService.setPrescriptionStatus(prescription, status);
    }
    public void setDrugExist(Drug drug, boolean existDrug) {
        drugService.setDoesExist(drug,existDrug);
    }
    public void setDrugPrice(Drug drug, int price) {
            drugService.setPrice(drug,price);
    }

    public Admin load(Admin admin) {
        try {
            return adminRepository.read(admin);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isExist(Admin admin) {
        try {
            return adminRepository.read(admin)!=null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

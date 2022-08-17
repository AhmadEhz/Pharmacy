package org.service;

import org.entity.Admin;
import org.entity.Drug;
import org.entity.Prescription;
import org.entity.PrescriptionList;
import org.repository.AdminRepository;

import java.sql.SQLException;

public class AdminService {
    AdminRepository adminRepository = new AdminRepository();
    public PrescriptionList seeAllPrescription() {
        return null;
    }
    public void confirmPrescription(Prescription prescription) {

    }
    public void setExist(Prescription prescription) {

    }
    public void setItemPrice(Drug drug) {

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

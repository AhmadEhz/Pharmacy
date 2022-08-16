package org.service;

import org.entity.Prescription;
import org.entity.PrescriptionList;

import java.sql.SQLException;

public class PatientService {
    PrescriptionService prescriptionService = new PrescriptionService();
    public void addPrescription(Prescription prescription) throws SQLException {
        prescriptionService.add(prescription);
    }
    public PrescriptionList seePrescriptions(){
        return null;
    }
    public void editPrescription(Prescription prescription){

    }
    public void deletePrescription(Prescription prescription) {

    }
}

package org.service;

import org.entity.Prescription;
import org.entity.PrescriptionList;
import org.entity.PrescriptionStatus;
import org.repository.PrescriptionRepository;

import java.sql.SQLException;

public class PrescriptionService {
    PrescriptionRepository prescriptionRepository = new PrescriptionRepository();
    DrugService drugService = new DrugService();

    public void add(Prescription prescription) throws SQLException {
        prescriptionRepository.create(prescription);
        int prescriptionId = prescriptionRepository.getLastPrescriptionIdAdded();

        for (int i = 0; i < prescription.numberOfItems(); i++) {//Add items of prescription
            drugService.add(prescription.getItem(i), prescriptionId);
        }
    }
   public PrescriptionList loadPrescriptionConfirmed(long patientId) throws SQLException {
        return prescriptionRepository.read(patientId);
   }

    public void remove(int id) throws SQLException {
        prescriptionRepository.delete(id);
    }

    public void changeStatus(Prescription prescription, PrescriptionStatus status) throws SQLException {
        prescriptionRepository.update(prescription, status);
    }
}

package org.service;

import org.entity.Prescription;
import org.entity.PrescriptionStatus;
import org.repository.PrescriptionRepository;

import java.sql.SQLException;

public class PrescriptionService {
    PrescriptionRepository prescriptionRepository = new PrescriptionRepository();
    ItemService itemService = new ItemService();

    public void add(Prescription prescription) throws SQLException {
        prescriptionRepository.add(prescription);
        int prescriptionId = prescriptionRepository.getLastPrescriptionIdAdded();

        for (int i = 0; i < prescription.numberOfItems(); i++) {//Add items of prescription
            itemService.add(prescription.getItem(i), prescriptionId);
        }

    }

    public void remove(int id) throws SQLException {
        prescriptionRepository.remove(id);
    }

    public void changeStatus(Prescription prescription, PrescriptionStatus status) throws SQLException {
        prescriptionRepository.changeStatus(prescription, status);
    }
}

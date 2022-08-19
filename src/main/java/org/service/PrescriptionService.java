package org.service;

import org.entity.Prescription;
import org.entity.PrescriptionList;
import org.entity.PrescriptionStatus;
import org.repository.PrescriptionRepository;

import java.sql.SQLException;

public class PrescriptionService {
    private PrescriptionRepository prescriptionRepository = new PrescriptionRepository();
    private DrugService drugService = new DrugService();

    public void add(Prescription prescription) {
        try {
            prescriptionRepository.create(prescription);
            long prescriptionId = prescriptionRepository.getLastPrescriptionIdAdded();

            for (int i = 0; i < prescription.numberOfItems(); i++) {//Add items of prescription
                drugService.add(prescription.getItem(i), prescriptionId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(long id) throws SQLException {
        prescriptionRepository.delete(id);
    }

    public PrescriptionList loadAll() {
        try {
            return prescriptionRepository.readAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PrescriptionList loadAll(long patientId) throws SQLException {//Load only confirmed prescriptions.
        return prescriptionRepository.readAll(patientId);
    }

    public Prescription load(Prescription prescription) {
        try {
            return prescriptionRepository.read(prescription);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPrescriptionStatus(Prescription prescription, PrescriptionStatus status) {
        prescription.setStatus(status);
        try {
            prescriptionRepository.update(prescription);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isExist(Prescription prescription) {
        try {
            return prescriptionRepository.read(prescription) != null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

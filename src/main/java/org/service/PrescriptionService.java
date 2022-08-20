package org.service;

import org.entity.Prescription;
import org.entity.PrescriptionList;
import org.entity.PrescriptionStatus;
import org.repository.PrescriptionRepository;

import java.sql.SQLException;

public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository = new PrescriptionRepository();
    private final DrugService drugService = new DrugService();

    public void add(Prescription prescription) {
        try {
            prescriptionRepository.create(prescription);
            long prescriptionId = prescriptionRepository.getLastPrescriptionIdAdded();

            for (int i = 0; i < prescription.getNumberOfDrugs(); i++) {//Add items of prescription
                drugService.add(prescription.getDrug(i), prescriptionId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(Prescription prescription) {
        try {
            drugService.remove(prescription.getDrugs());
            prescriptionRepository.delete(prescription);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PrescriptionList loadAll() {
        try {
            return prescriptionRepository.readAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PrescriptionList loadAll(long patientId, PrescriptionStatus status) {//Load only confirmed prescriptions.
        try {
            return prescriptionRepository.readAll(patientId, status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PrescriptionList loadAll(PrescriptionStatus status) {
        try {
            return prescriptionRepository.readAll(status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Prescription load(Prescription prescription, boolean checkPatientId) {
        try {
            return prescriptionRepository.read(prescription, checkPatientId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Prescription prescription) {
        try {
            prescriptionRepository.update(prescription);
            for (int i = 0; i < prescription.getNumberOfDrugs(); i++) {
                drugService.update(prescription.getDrug(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isExist(Prescription prescription, boolean checkPatientId) {
        try {
            return prescriptionRepository.read(prescription, checkPatientId) != null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.service;

import org.entity.Patient;
import org.entity.PrescriptionList;
import org.entity.PrescriptionStatus;
import org.repository.PatientRepository;

import java.sql.SQLException;

public class PatientService {
    PrescriptionService prescriptionService = new PrescriptionService();
    PatientRepository patientRepository = new PatientRepository();

    public void add(Patient patient) {
        try {
            patientRepository.create(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PrescriptionList loadPrescriptions(long patientId, PrescriptionStatus status) {
        return prescriptionService.loadAll(patientId, status);

    }

    public boolean checkUsername(Patient patient) {
        try {
            return patientRepository.read(patient, false) != null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isExist(Patient patient) {//for login
        try {
            return patientRepository.read(patient, true) != null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long getLastGeneratedId() {
        return patientRepository.getLastGeneratedId();
    }
}

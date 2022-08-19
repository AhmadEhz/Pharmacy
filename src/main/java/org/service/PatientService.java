package org.service;

import org.entity.Patient;
import org.entity.Prescription;
import org.entity.PrescriptionList;
import org.repository.PatientRepository;

import java.sql.SQLException;

public class PatientService {
    PrescriptionService prescriptionService = new PrescriptionService();
    PatientRepository patientRepository = new PatientRepository();

    public void addPrescription(Prescription prescription) throws SQLException {
        prescriptionService.add(prescription);
    }
    public void add(Patient patient)  {
        try {
            patientRepository.create(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PrescriptionList loadPrescriptions(long patientId) {
        try {
            return prescriptionService.loadAll(patientId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editPrescription(Prescription prescription) {

    }

    public void deletePrescription(Prescription prescription) {

    }
    public boolean checkUsername(Patient patient) {
        try {
            return patientRepository.read(patient,false) != null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isExist(Patient patient) {//for login
        try {
            return patientRepository.read(patient,true) != null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

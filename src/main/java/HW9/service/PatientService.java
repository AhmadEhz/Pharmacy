package HW9.service;

import HW9.entity.Prescription;
import HW9.entity.PrescriptionList;

import java.sql.SQLException;

public class PatientService {
    ItemService itemService = new ItemService();
    public void addPrescription(Prescription prescription) throws SQLException {
        for (int i = 0; prescription.getItem(i) != null; i++) {
            itemService.add(prescription.getItem(i));
        }
    }
    public PrescriptionList seePrescriptions(){
        return null;
    }
    public void editPrescription(Prescription prescription){

    }
    public void deletePrescription(Prescription prescription) {

    }
}

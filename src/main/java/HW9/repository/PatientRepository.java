package HW9.repository;

import HW9.entity.Item;
import HW9.entity.Prescription;
import HW9.entity.PrescriptionList;

public class PatientRepository {
    ItemRepository itemRepository = new ItemRepository();
    public void addPrescription(Prescription prescription) {
        for(int i = 0; !prescription.isEmpty(i);i++) {
            Item item = prescription.getItem(i);

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

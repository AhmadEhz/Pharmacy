package HW9.service;

import HW9.entity.Prescription;
import HW9.repository.PrescriptionRepository;

import java.sql.SQLException;

public class PrescriptionService {
    PrescriptionRepository prescriptionRepository = new PrescriptionRepository();
    ItemService itemService = new ItemService();

    public void add(Prescription prescription) throws SQLException {
        prescriptionRepository.add(prescription);
        int itemId[] = new int[prescription.numberOfItems()];
        for (int i = 0; !prescription.isEmptyItem(i); i++) {
            itemId[i] = itemService.add(prescription.getItem(i));
        }
        for (int i = 0; i < itemId.length; i++) {

        }
    }
}

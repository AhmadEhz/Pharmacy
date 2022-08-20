package org.util;

import org.entity.DrugList;
import org.entity.Patient;
import org.entity.Prescription;
import org.entity.PrescriptionList;

public class Utility {
    public static long getDrugId(Prescription prescription,int index) {
        DrugList drugList = prescription.getDrugs();
        if(index>drugList.length())
            return -1;
        return drugList.load(index-1).getId();

    }
    public static long getPrescriptionId(PrescriptionList prescriptionList, int index) {
        if(index>prescriptionList.length())
            return -1;
        return prescriptionList.load(index-1).getId();
    }
}

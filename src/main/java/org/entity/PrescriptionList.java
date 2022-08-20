package org.entity;

import java.util.Arrays;

public class PrescriptionList {
    private Prescription [] prescriptions = new Prescription[100];
    private int index = 0;
    public void add(Prescription prescription) {
        if (index == prescriptions.length - 1) {
            Arrays.copyOf(prescriptions, prescriptions.length + 100);
        }
        prescriptions[index++] = prescription;
    }

    public void remove(Prescription prescription) {
        int indexOfPrescription = getPrescriptionIndex(prescription);
        if (indexOfPrescription != -1);//If indexOfClub equals -1 , it means no club found.
        {
            for (int i = indexOfPrescription; i < index; i++)
                prescriptions[i] = prescriptions[i + 1];
            prescriptions[index--] = null;
        }
    }
    private int getPrescriptionIndex(Prescription prescription) {
        if (isEmpty())
            return -1;
        for (int i = 0; i < index; i++) {
            if (prescriptions[i].equals(prescription))
                return i;
        }
        return -1;
    }

    public Prescription load(int id) {
        if (isEmpty() || id > index)
            return null;
        return prescriptions[id];
    }

    public Prescription load(Prescription prescription) {
        if (isEmpty())
            return null;
        for (int i = 0; i < index; i++) {
            if (prescriptions[i].equals(prescription))
                return prescriptions[i];
        }
        return null;
    }
    public Prescription[] loadAll() {
        Prescription[] prescriptionArray = new Prescription[index];
        if(isEmpty())
            return null;
        for(int i = 0; i<index; i++) {
            prescriptionArray[i]= prescriptions[i];
        }
        return prescriptionArray;
    }
    public boolean isEmpty() {
        return index == 0;
    }
    public int length() {
        return index;
    }
    @Override
    public String toString() {
        if(isEmpty())
            return "There is no prescription!";
        String string = "";
        for(int i=0; i<index; i++)
        {
            string += "Prescription " + (i+1) +":\n" + prescriptions[i];
        }
        return string;
    }
}

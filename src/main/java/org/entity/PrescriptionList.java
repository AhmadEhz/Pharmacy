package org.entity;

import java.util.Arrays;

public class PrescriptionList {
    private Prescription [] prescriptions = new Prescription[100];
    private int index = 0;
    public void add(Prescription prescription) {
        if (index == prescriptions.length - 1) {
            prescriptions = Arrays.copyOf(prescriptions, prescriptions.length + 100);
        }
        prescriptions[index++] = prescription;
    }

    public Prescription load(int id) {
        if (isEmpty() || id > index)
            return null;
        return prescriptions[id];
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

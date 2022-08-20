package org.entity;

import java.util.Arrays;

public class DrugList {
    private Drug[] drugs;
    private final boolean finalSize;
    private int index = 0;

    public DrugList(int length, boolean finalSize) {
        this.finalSize = finalSize;
        drugs = new Drug[length];
    }

    public void add(Drug drug) {
        if (isFull()) {
            if (finalSize)
                return;
            drugs = Arrays.copyOf(drugs, drugs.length * 2);
        }
        if (drug != null)
            drugs[index++] = drug;
    }

    public void set(Drug drug, int drugIndex) {
        drugs[drugIndex] = drug;
    }
    public Drug load(int id) {
        if (isEmpty() || id > index)
            return null;
        return drugs[id];
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public int length() {
        return index;
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < index; i++) {
            string += (i + 1) + "- " + drugs[i] + "\n";
        }
        return string;
    }

    public boolean isFull() {
        return index == drugs.length;
    }

    public String toStringSummary() {//show drug names only
        String string = "";
        for (int i = 0; i < index; i++) {
            string += (i + 1) + "- " + drugs[i].toStringSummary() + "\n";
        }
        return string;
    }
}

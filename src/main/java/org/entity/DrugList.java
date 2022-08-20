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
            Arrays.copyOf(drugs, drugs.length + 100);
        }
        drugs[index++] = drug;
    }

    public void remove(Drug drug) {
        int indexOfItem = getItemIndex(drug);
        if (indexOfItem == -1)//If indexOfClub equals -1 , it means no club found.
            return ;
        else {
            for (int i = indexOfItem; i < index; i++)
                drugs[i] = drugs[i + 1];
            drugs[index--] = null;
        }
    }
    private int getItemIndex(Drug drug) {
        if (isEmpty())
            return -1;
        for (int i = 0; i < index; i++) {
            if (drugs[i].equals(drug))
                return i;
        }
        return -1;
    }

    public Drug load(int id) {
        if (isEmpty() || id > index)
            return null;
        return drugs[id];
    }

    public Drug load(Drug drug) {
        if (isEmpty())
            return null;
        for (int i = 0; i < index; i++) {
            if (drugs[i].equals(drug))
                return drugs[i];
        }
        return null;
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
        for(int i = 0; i<index; i++) {
            string += (i+1) + "- " + drugs[i]+"\n";
        }
        return string;
    }
    public boolean isFull() {
        return index==drugs.length;
    }

    public String toStringSummary() {//show drug names only
        String string = "";
        for(int i = 0; i<index; i++) {
            string += (i+1) + "- " + drugs[i].toStringSummary()+"\n";
        }
        return string;
    }
}

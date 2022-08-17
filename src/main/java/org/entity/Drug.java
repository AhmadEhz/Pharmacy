package org.entity;

import java.util.Objects;

public class Drug {
    private long id;
    private String name;
    private int price;
    private boolean doesExist;
    private long prescriptionId;

    public Drug() {
    }

    public Drug(String name) {
        this.name = name;
    }

    public Drug(int id, String name, int price, boolean doesExist, int prescriptionId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.doesExist = doesExist;
        this.prescriptionId = prescriptionId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getDoesExist() {
        return doesExist;
    }

    public void setDoesExist(boolean doesExist) {
        this.doesExist = doesExist;
    }

    public long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
    @Override
    public String toString() {
        return "Name: " + name + " | " +
                "Price: " + price +
                " -> " + doesExistToString();

    }
    private String doesExistToString() {
        if(doesExist)
            return "Available";
            else return "Not available";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return Objects.equals(name, drug.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

package org.entity;

import java.util.Objects;

public class Item {
    private long id;
    private String name;
    private int price;
    private boolean doesExist;
    private int prescriptionId;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(int id, String name, int price, boolean doesExist, int prescriptionId) {
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

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

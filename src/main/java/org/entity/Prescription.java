package org.entity;

import java.util.Objects;

public class Prescription {
    private int id;
    private ItemList items;
    private PrescriptionStatus status;
    public Prescription() {
        status = PrescriptionStatus.PENDING;
        items = new ItemList(10,true);
    }
    public Prescription(int id) {
        status = PrescriptionStatus.PENDING;
        this.id = id;
        items = new ItemList(10,true);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemList getItems() {
        return items;
    }
    public Item getItem(int id) {
        return items.load(id);
    }

    public void setItems(ItemList items) {
        this.items = items;
    }

    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }
    public boolean isEmptyItem(int id){
        return items.load(id) == null;
    }
    public int numberOfItems() {
        return items.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

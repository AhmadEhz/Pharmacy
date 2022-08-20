package org.entity;

import java.util.Objects;

public class Prescription {
    private long id;
    private DrugList drugs;
    private PrescriptionStatus status;
    private long patientId;
    private int totalPrice;

    public Prescription() {
        status = PrescriptionStatus.PENDING;
        drugs = new DrugList(10, true);//max drugList by per prescription is 10.
    }

    public Prescription(long patientId, PrescriptionStatus status) {
        this.patientId = patientId;
        drugs = new DrugList(10, true);//max drugList by per prescription is 10.
        this.status = status;
    }

    public Prescription(long patientId) {
        this.patientId = patientId;
        drugs = new DrugList(10, true);
    }

    public Prescription(long id, long patientId) {
        this.id = id;
        this.patientId = patientId;
        drugs = new DrugList(10, true);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DrugList getDrugs() {
        return drugs;
    }

    public Drug getItem(int id) {
        return drugs.load(id);
    }

    public void setDrugs(DrugList drugs) {
        this.drugs = drugs;
    }

    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }

    public boolean isEmptyItem(int id) {
        return drugs.load(id) == null;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int numberOfItems() {
        return drugs.length();
    }

    @Override
    public String toString() {//if status is confirmed, shows all drugs with details. if not confirmed, shows name of drugs only.
        String drugs = null;
        if (this.drugs != null)//If this.drugs is null it means this prescription has not any items.
            drugs = status == PrescriptionStatus.CONFIRMED ? this.drugs + "Total price: " + totalPrice + "\n" : this.drugs.toStringSummary();
        String drugList = drugs != null ? "Items:\n" + drugs : "No item!\n";
        return drugList + "Status: " + status + "\n" +
                "-----------------------------\n";
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

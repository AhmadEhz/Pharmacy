package HW9.entity;

public class PrescriptionItem {
    public final Prescription prescription;
    public final Item item;

    public PrescriptionItem(Prescription prescription, Item item) {
        this.prescription = prescription;
        this.item = item;
    }

    public Prescription getPrescription() {
        return prescription;
    }
    public Item getItem() {
        return item;
    }
}

package HW9.entity;

public class Prescription {
    private int id;
    private Item item[] = new Item[10];
    private PrescriptionStatus status;
    public Prescription() {
        status = PrescriptionStatus.PENDING;
    }
    public Prescription(int id) {
        status = PrescriptionStatus.PENDING;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item[] getItem() {
        return item;
    }
    public Item getItem(int index) {
        return item[index];
    }

    public void setItem(Item[] item) {
        this.item = item;
    }

    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }
    public boolean isEmpty() {
        return item[0] != null;
    }
    public boolean isEmpty(int index) {
        return item[index] != null;
    }
}

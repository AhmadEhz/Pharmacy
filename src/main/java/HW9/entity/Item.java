package HW9.entity;

public class Item {
    private int id;
    private final String name;
    private int price;
    private boolean doesExist;

    public Item(String name) {
        this.name = name;
    }
    public Item(int id, String name, int price, boolean doesExist) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.doesExist = doesExist;
    }

    public int getId(String name) {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isDoesExist() {
        return doesExist;
    }

    public void setDoesExist(boolean doesExist) {
        this.doesExist = doesExist;
    }
}

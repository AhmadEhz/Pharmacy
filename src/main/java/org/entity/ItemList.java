package org.entity;

import java.util.Arrays;

public class ItemList {
    private Item[] items;
    private final boolean finalSize;
    private int index;

    public ItemList(int length, boolean finalSize) {
        this.finalSize = finalSize;
        items = new Item[length];
    }

    public void add(Item item) {
        if (index == items.length - 1) {
            if (finalSize)
                return;
            Arrays.copyOf(items, items.length + 100);
        }
        items[index++] = item;
    }

    public void remove(Item item) {
        int indexOfItem = getItemIndex(item);
        if (indexOfItem == -1)//If indexOfClub equals -1 , it means no club found.
            return ;
        else {
            for (int i = indexOfItem; i < index; i++)
                items[i] = items[i + 1];
            items[index--] = null;
        }
    }
    private int getItemIndex(Item item) {
        if (isEmpty())
            return -1;
        for (int i = 0; i < index; i++) {
            if (items[i].equals(item))
                return i;
        }
        return -1;
    }

    public Item load(int id) {
        if (isEmpty() || id > index)
            return null;
        return items[id];
    }

    public Item load(Item item) {
        if (isEmpty())
            return null;
        for (int i = 0; i < index; i++) {
            if (items[i].equals(item))
                return items[i];
        }
        return null;
    }

    public boolean isEmpty() {
        return index == 0;
    }
    public int length() {
        return index;
    }
}

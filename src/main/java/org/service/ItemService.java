package org.service;

import org.entity.Item;
import org.repository.ItemRepositoryImpl;

import java.sql.SQLException;

public class ItemService {
    ItemRepositoryImpl itemRepositoryImpl = new ItemRepositoryImpl();

    public void add(Item item, int prescriptionId) throws SQLException {
        itemRepositoryImpl.add(item, prescriptionId);
    }
    public void remove(int id) throws SQLException {
        itemRepositoryImpl.remove(id);
    }
    public void edit(Item item) throws SQLException {
        itemRepositoryImpl.edit(item);
    }
}

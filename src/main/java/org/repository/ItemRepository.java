package org.repository;

import org.entity.Item;

import java.sql.SQLException;

public interface ItemRepository {
void add(Item item, int prespectionId) throws SQLException;
    void remove(int id) throws SQLException;
    void edit(Item item) throws SQLException;

}

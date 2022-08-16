package org.repository;

import org.config.DbConfig;
import org.entity.Item;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ItemRepositoryImpl implements ItemRepository{
    @Override
    public void add(Item item, int prescriptionId) throws SQLException {
        String query = """
                insert into item (name, price, does_exit,prescription_id)
                values(?,?,?,?);
                               """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1,item.getName());
        ps.setLong(2,item.getId());
        ps.setBoolean(3,item.getDoesExist());
        ps.setInt(4,prescriptionId);
        ps.close();
    }
    @Override
    public void remove(int id) throws SQLException {
        String query = """
                delete from item
                where id = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setInt(1,id);
        ps.execute();
        ps.close();
    }
    @Override
    public void edit(Item item) throws SQLException {
        String query = """
                update item
                set name = ?, price = ?, does_exit = ?
                where id = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1,item.getName());
        ps.setInt(2, item.getPrice());
        ps.setBoolean(3,item.getDoesExist());
        ps.setLong(4,item.getId());
        ps.execute();
        ps.close();
    }
}

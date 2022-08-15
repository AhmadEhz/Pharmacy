package HW9.repository;

import HW9.config.DbConfig;
import HW9.entity.Item;
import HW9.entity.Prescription;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ItemRepository {
    public int add(Item item) throws SQLException {
        String query = """
                insert into item (name, price, does_exit)
                values(?,?,?);
                               """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,item.getName());
        ps.setInt(2,item.getId());
        ps.setBoolean(3,item.isDoesExist());
        ResultSet rs = ps.executeQuery();
        int itemId = -1;
        if (rs.next())
            itemId = rs.getInt("id");
        ps.close();
        return itemId;
    }
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
    public void edit(Item item) throws SQLException {
        String query = """
                update item
                set name = ?, price = ?, does_exit = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1,item.getName());
        ps.setInt(2, item.getPrice());
        ps.setBoolean(3,item.isDoesExist());
        ps.execute();
        ps.close();
    }
}

package org.repository.drug;

import org.config.DbConfig;
import org.entity.Drug;
import org.entity.DrugList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DrugRepositoryImpl implements DrugRepository {
    @Override
    public void add(Drug drug, int prescriptionId) throws SQLException {
        String query = """
                insert into drug (name, price, does_exist,prescription_id)
                values(?,?,?,?);
                               """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1, drug.getName());
        ps.setLong(2, drug.getId());
        ps.setBoolean(3, drug.getDoesExist());
        ps.setLong(4, prescriptionId);
        ps.close();
    }

    @Override
    public void remove(int id) throws SQLException {
        String query = """
                delete from drug
                where id = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

    @Override
    public void update(Drug drug) throws SQLException {
        String query = """
                update drug
                set name = ?, price = ?, does_exist = ?
                where id = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1, drug.getName());
        ps.setLong(2, drug.getPrice());
        ps.setBoolean(3, drug.getDoesExist());
        ps.setLong(4, drug.getId());
        ps.execute();
        ps.close();
    }

    public DrugList read(long prescriptionId) throws SQLException {
        String query = """
                select * from drug as d
                join prescription p on d.prescription_id = p.id
                where p.id = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setLong(1, prescriptionId);
        ResultSet rs = ps.executeQuery();
        DrugList drugList = new DrugList(10, true);
        while (rs.next()) {
            Drug drug = new Drug();
            drug.setId(rs.getLong("id"));
            drug.setName(rs.getString("name"));
            drug.setPrice(rs.getInt("price"));
            drug.setDoesExist(rs.getBoolean("does_exist"));
            drug.setPrescriptionId(rs.getLong("prescription_id"));
            drugList.add(drug);
        }
        if (drugList.isEmpty())
            return null;
        else return drugList;
    }
}

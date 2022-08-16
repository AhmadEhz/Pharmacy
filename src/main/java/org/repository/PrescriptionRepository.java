package org.repository;

import org.config.DbConfig;
import org.entity.Prescription;
import org.entity.PrescriptionStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrescriptionRepository {
    private int lastPrescriptionIdAdded;
    public void add(Prescription prescription) throws SQLException {
        String query = """
                insert into prescription(status)
                values(?);
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, prescription.getStatus().name());
        ResultSet rs = ps.executeQuery();
        lastPrescriptionIdAdded = rs.getInt("id");
        ps.close();
    }

    public void changeStatus(Prescription prescription, PrescriptionStatus status) throws SQLException {
        String query = """
                update prescription
                set status = ?
                where id = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1, status.name());
        ps.setInt(2, prescription.getId());
        ps.execute();
        ps.close();
    }

    public void remove(int id) throws SQLException {
        String query = """
                delete from prescription
                where id = ?
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }
    public int getLastPrescriptionIdAdded() {
        return lastPrescriptionIdAdded;
    }
}

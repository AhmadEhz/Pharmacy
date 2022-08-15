package HW9.repository;

import HW9.config.DbConfig;
import HW9.entity.Prescription;
import HW9.entity.PrescriptionStatus;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrescriptionRepository {
    public void add(Prescription prescription) throws SQLException {
        String query = """
                insert into prescription(status)
                values(?);
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1, prescription.getStatus().name());
        ps.execute();
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

    public void remove(Prescription prescription) throws SQLException {
        String query = """
                delete from prescription
                where id = ?
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setInt(1, prescription.getId());
        ps.execute();
        ps.close();
    }
}

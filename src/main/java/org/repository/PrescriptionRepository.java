package org.repository;

import org.config.DbConfig;
import org.entity.Prescription;
import org.entity.PrescriptionList;
import org.entity.PrescriptionStatus;
import org.repository.drug.DrugRepositoryImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrescriptionRepository {
    private long lastPrescriptionIdAdded;
    private DrugRepositoryImpl drugRepository = new DrugRepositoryImpl();

    public void create(Prescription prescription) throws SQLException {
        String query = """
                insert into prescription(status, patient_id)
                values(? :: prescription_status, ?);
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, prescription.getStatus().name());
        ps.setLong(2, prescription.getPatientId());
        ps.execute();
        ResultSet generatedKeys = ps.getGeneratedKeys();
        generatedKeys.next();
        lastPrescriptionIdAdded = generatedKeys.getInt(1);

        generatedKeys.close();
        ps.close();
    }

    public void update(Prescription prescription) throws SQLException {
        String query = """
                update prescription
                set status = ?, total_price = ?
                where id = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1, prescription.getStatus().name());
        ps.setInt(2, prescription.getTotalPrice());
        ps.setLong(3, prescription.getId());
        ps.execute();
        ps.close();
    }

    public void delete(long id) throws SQLException {
        String query = """
                delete from prescription
                where id = ?
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

    public long getLastPrescriptionIdAdded() {
        return lastPrescriptionIdAdded;
    }

    public PrescriptionList readAll() throws SQLException {
        String query = """
                select * from prescription
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ps.close();
        PrescriptionList prescriptionList = new PrescriptionList();
        while (rs.next()) {
            Prescription prescription = new Prescription();
            prescription.setId(rs.getLong("id"));
            prescription.setStatus(PrescriptionStatus.valueOf(rs.getString("status")));
            prescriptionList.add(prescription);
        }
        return prescriptionList;
    }

    public PrescriptionList readAll(long patient_id) throws SQLException {
        String query = """
                select * from prescription
                where patient_id = ?
                and status = 'CONFIRMED';
                """;

        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setLong(1, patient_id);
        ResultSet rs = ps.executeQuery();
        PrescriptionList prescriptionList = new PrescriptionList();
        while (rs.next()) {
            Prescription prescription = new Prescription();
            prescription.setId(rs.getLong("id"));
            prescription.setStatus(PrescriptionStatus.valueOf(rs.getString("status")));
            prescription.setDrugs(drugRepository.read(prescription.getId()));
            prescriptionList.add(prescription);
        }
        ps.close();
        return prescriptionList;
    }

    public Prescription read(Prescription prescription) throws SQLException {
        String query = """
                select * from prescription
                where id = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setLong(1, prescription.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            prescription.setDrugs(drugRepository.read(prescription.getId()));
            prescription.setStatus(PrescriptionStatus.valueOf(rs.getString("status")));
            return prescription;
        } else return null;
    }
    /*public PrescriptionList read() throws SQLException {
        String query = """
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        PrescriptionList prescriptionList = new PrescriptionList();
        while (rs.next()) {
            Prescription prescription = new Prescription();
            prescription.setId(rs.getInt("id"));
            prescription.setStatus(PrescriptionStatus.valueOf(rs.getString("status")));
            prescription.setDrugs(drugRepository.read(prescription.getId()));
            prescriptionList.add(prescription);
        }
        return prescriptionList;
    }*/
}

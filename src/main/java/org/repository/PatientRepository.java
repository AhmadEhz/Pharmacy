package org.repository;

import org.config.DbConfig;
import org.entity.Patient;
import org.repository.drug.DrugRepositoryImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRepository {
    DrugRepositoryImpl drugRepository = new DrugRepositoryImpl();

    public Patient read(Patient patient) throws SQLException {
        String query = """
                select * from users
                where user_name = ? and password = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1, patient.getUsername());
        ps.setString(2, patient.getPassword());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Patient newPatient = new Patient ();
           patient.setId(rs.getLong("id"));
           patient.setName(rs.getString("name"));
           patient.setUsername(rs.getString("user_name"));
           patient.setPassword(rs.getString("password"));
           return newPatient;
        }
        else return null;
    }

}

package org.repository;

import org.config.DbConfig;
import org.entity.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientRepository {
    private long lastGeneratedID;

    public Patient read(Patient patient, boolean checkPassword) throws SQLException {
        String query = """
                select * from users
                where username = ? and access = 'PATIENT'
                """;
        if (checkPassword)
            query += " and password = ?;";
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1, patient.getUsername());
        if (checkPassword)
            ps.setString(2, patient.getPassword());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Patient newPatient = new Patient();
            patient.setId(rs.getLong("id"));
            patient.setName(rs.getString("name"));
            patient.setUsername(rs.getString("username"));
            patient.setPassword(rs.getString("password"));
            ps.close();
            rs.close();
            return newPatient;
        } else {
            ps.close();
            rs.close();
            return null;
        }
    }

    public void create(Patient patient) throws SQLException {
        String query = """
                insert into users (name, username, password, access)
                values (?,?,?,'PATIENT')
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,patient.getName());
        ps.setString(2,patient.getUsername());
        ps.setString(3,patient.getPassword());
        ps.execute();
        ResultSet generatedKeys = ps.getGeneratedKeys();
        generatedKeys.next();
        lastGeneratedID = generatedKeys.getLong(1);
        ps.close();
        generatedKeys.close();
    }

    public long getLastGeneratedId() {
        return lastGeneratedID;
    }
}

package org.repository;

import org.config.DbConfig;
import org.entity.Admin;
import org.entity.PrescriptionStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
    public Admin read(Admin admin) throws SQLException {
        String query = """
                select id, name from users 
                where username = ? and password = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ps.close();
        if(rs.next()) {
            admin.setName(rs.getString("name"));
            admin.setId(rs.getLong("id"));
            rs.close();
            return admin;
        }
        else {
            rs.close();
            return null;
        }
    }
    public void update(Admin admin) throws SQLException {
        String query = """
                update users
                set name = ?,
                username = ?,
                password = ?
                where id = ? and access = 'ADMIN';""";
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1,admin.getName());
        ps.setString(2,admin.getUsername());
        ps.setString(3,admin.getPassword());
        ps.setLong(4,admin.getId());
        ps.execute();
        ps.close();
    }
}

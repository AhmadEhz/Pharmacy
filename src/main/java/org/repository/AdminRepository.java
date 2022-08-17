package org.repository;

import org.config.DbConfig;
import org.entity.Admin;

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
        if(rs.next()) {
            admin.setName(rs.getString("name"));
            admin.setId(rs.getLong("id"));
            return admin;
        }
        else return null;
    }
}

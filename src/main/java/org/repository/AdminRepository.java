package org.repository;

import org.config.DbConfig;
import org.entity.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
    public Admin read(Admin admin,boolean checkPassword) throws SQLException {
        String query = """
                select id, name from users
                where username = ? and access = 'ADMIN'
                """;
        if(checkPassword)
            query+= " and password = ?";
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1,admin.getUsername());
        if(checkPassword)
            ps.setString(2,admin.getPassword());
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            admin.setName(rs.getString("name"));
            admin.setId(rs.getLong("id"));
            ps.close();
            rs.close();
            return admin;
        }
        else {
            ps.close();
            rs.close();
            return null;
        }
    }
}

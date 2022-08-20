package org.service;

import org.entity.*;
import org.repository.AdminRepository;

import java.sql.SQLException;

public class AdminService {
    private final AdminRepository adminRepository = new AdminRepository();

    public boolean isExist(Admin admin) {
        try {
            return adminRepository.read(admin,true)!=null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkUsername(Admin admin) {
        try {
            return adminRepository.read(admin, false) != null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

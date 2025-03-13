package com.natursalas.natursalassystem.service;

import com.natursalas.natursalassystem.model.dao.AdminInfoDAO;
import com.natursalas.natursalassystem.model.dto.AdminInfoDTO;

import java.sql.Connection;

public class AdminInfoService {
    private final AdminInfoDAO adminInfoDAO;

    public AdminInfoService(Connection connection) {
        this.adminInfoDAO = new AdminInfoDAO(connection);
    }

    public AdminInfoDTO getAdminInfo() {
        return adminInfoDAO.getAdminInfo();
    }
}

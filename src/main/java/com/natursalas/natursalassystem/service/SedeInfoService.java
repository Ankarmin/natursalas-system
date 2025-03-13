package com.natursalas.natursalassystem.service;

import com.natursalas.natursalassystem.model.dao.SedeInfoDAO;
import com.natursalas.natursalassystem.model.dto.SedeInfoDTO;

import java.sql.Connection;

public class SedeInfoService {
    private final SedeInfoDAO sedeInfoDAO;

    public SedeInfoService(Connection connection) {
        this.sedeInfoDAO = new SedeInfoDAO(connection);
    }

    public SedeInfoDTO getSedeInfo(String idLocation) {
        return sedeInfoDAO.getSedeInfo(idLocation);
    }
}
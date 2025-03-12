package com.natursalas.natursalassystem.service;

import com.natursalas.natursalassystem.model.dao.SaleDAO;
import com.natursalas.natursalassystem.model.dto.SaleDTO;

import java.sql.Connection;
import java.util.List;

public class SaleService {
    private final SaleDAO saleDAO;

    public SaleService(Connection connection) {
        this.saleDAO = new SaleDAO(connection);
    }

    public boolean updateSale(SaleDTO updatedSale) {
        return saleDAO.updateSale(updatedSale);
    }

    public List<SaleDTO> getAllSales() {
        return saleDAO.getAllSales();
    }

    public List<SaleDTO> getSalesByDNI(String dni) {
        return saleDAO.getSalesByDNI(dni);
    }

    public List<SaleDTO> getSalesByLocation(String idLocation) {
        return saleDAO.getSalesByLocation(idLocation);
    }
}
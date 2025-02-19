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

    public boolean addSale(SaleDTO newSale) {
        return saleDAO.addSale(newSale);
    }

    public boolean updateSale(SaleDTO updatedSale) {
        return saleDAO.updateSale(updatedSale);
    }

    public boolean deleteSale(String idSale) {
        return saleDAO.deleteSale(idSale);
    }

    public SaleDTO getSale(String idSale) {
        return saleDAO.getSale(idSale);
    }

    public List<SaleDTO> getAllSales() {
        return saleDAO.getAllSales();
    }

    public List<SaleDTO> getSalesByDNI(String dni) {
        return saleDAO.getSalesByDNI(dni);
    }
}
package com.natursalas.natursalassystem.service;

import com.natursalas.natursalassystem.model.dao.SaleDAO;
import com.natursalas.natursalassystem.model.dto.SaleDTO;
import com.natursalas.natursalassystem.model.dto.SaleDetailDTO;

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

    public boolean addSaleWithDetails(SaleDTO sale, List<SaleDetailDTO> details) {
        return saleDAO.addSaleWithDetails(sale, details);
    }

    public boolean updateSale(SaleDTO updatedSale) {
        return saleDAO.updateSale(updatedSale);
    }

    public boolean deleteSale(String idSale) {
        return saleDAO.deleteSale(idSale);
    }

    public List<SaleDTO> getAllSales() {
        return saleDAO.getAllSales();
    }

    public boolean updateSaleInfo(String idSale, String dni, String diagnosis, String category) {
        return saleDAO.updateSaleInfo(idSale, dni, diagnosis, category);
    }

    public List<SaleDTO> getSalesByDNI(String dni) {
        return saleDAO.getSalesByDNI(dni);
    }

    public boolean existsSale(String idSale) {
        return saleDAO.existsSale(idSale);
    }
}
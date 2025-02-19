package com.natursalas.natursalassystem.service;

import com.natursalas.natursalassystem.model.dao.SaleDetailDAO;
import com.natursalas.natursalassystem.model.dto.SaleDetailDTO;

import java.sql.Connection;
import java.util.List;

public class SaleDetailService {
    private final SaleDetailDAO saleDetailDAO;

    public SaleDetailService(Connection connection) {
        this.saleDetailDAO = new SaleDetailDAO(connection);
    }

    public boolean addSalesDetail(SaleDetailDTO newSalesDetail) {
        return saleDetailDAO.addSalesDetail(newSalesDetail);
    }

    public boolean updateSalesDetail(SaleDetailDTO updatedSalesDetail) {
        return saleDetailDAO.updateSalesDetail(updatedSalesDetail);
    }

    public boolean deleteSalesDetail(Integer idSale, String idProduct, String idLocation) {
        return saleDetailDAO.deleteSalesDetail(idSale, idProduct, idLocation);
    }

    public SaleDetailDTO getSalesDetail(Integer idSale, String idProduct, String idLocation) {
        return saleDetailDAO.getSalesDetail(idSale, idProduct, idLocation);
    }

    public List<SaleDetailDTO> getAllSalesDetails() {
        return saleDetailDAO.getAllSalesDetails();
    }

    public List<SaleDetailDTO> getSalesDetailsBySaleId(String idSale) {
        return saleDetailDAO.getSalesDetailsBySaleId(idSale);
    }
}
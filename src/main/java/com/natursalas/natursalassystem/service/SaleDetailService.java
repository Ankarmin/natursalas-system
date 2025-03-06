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

    public boolean addMultipleSalesDetails(List<SaleDetailDTO> salesDetails) {
        return saleDetailDAO.addMultipleSalesDetails(salesDetails);
    }

    public boolean updateSalesDetail(SaleDetailDTO updatedSalesDetail) {
        return saleDetailDAO.updateSalesDetail(updatedSalesDetail);
    }

    public boolean deleteSalesDetail(String idSale, String idProduct, String idLocation) {
        return saleDetailDAO.deleteSalesDetail(idSale, idProduct, idLocation);
    }

    public List<SaleDetailDTO> getSalesDetailsBySaleId(String idSale) {
        return saleDetailDAO.getSalesDetailsBySaleId(idSale);
    }

    public boolean existsSaleDetail(String idSale, String idProduct, String idLocation) {
        return saleDetailDAO.existsSaleDetail(idSale, idProduct, idLocation);
    }
}
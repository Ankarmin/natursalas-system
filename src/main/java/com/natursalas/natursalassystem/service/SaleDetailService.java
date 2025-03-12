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

    public boolean addSalesDetails(String idSale, List<SaleDetailDTO> salesDetails) {
        return saleDetailDAO.addSalesDetails(idSale, salesDetails);
    }

    public List<SaleDetailDTO> getSalesDetailsBySaleId(String idSale) {
        return saleDetailDAO.getSalesDetailsBySaleId(idSale);
    }
}
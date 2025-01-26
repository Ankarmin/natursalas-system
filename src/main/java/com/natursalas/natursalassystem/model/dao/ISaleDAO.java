package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SaleDTO;

import java.util.List;

public interface ISaleDAO {

    boolean addSale(SaleDTO newSale);

    boolean updateSale(SaleDTO updatedSale);

    boolean deleteSale(Integer saleId);

    SaleDTO getSale(Integer saleId);

    List<SaleDTO> getAllSales();
}
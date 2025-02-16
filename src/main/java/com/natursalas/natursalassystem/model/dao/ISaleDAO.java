package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SaleDTO;

import java.util.List;

public interface ISaleDAO {

    boolean addSale(SaleDTO newSale);

    boolean updateSale(SaleDTO updatedSale);

    boolean deleteSale(String idSale);

    SaleDTO getSale(String idSale);

    List<SaleDTO> getAllSales();
}
package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SaleDTO;

import java.util.List;

public interface ISaleDAO {
    boolean updateSale(SaleDTO updatedSale);

    List<SaleDTO> getAllSales();

    List<SaleDTO> getSalesByDNI(String dni);

    List<SaleDTO> getSalesByLocation(String idLocation);

    boolean existsSale(String idSale);
}
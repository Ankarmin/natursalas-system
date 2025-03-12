package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SaleDetailDTO;

import java.util.List;

public interface ISalesDetailDAO {
    boolean addSalesDetails(String idSale, List<SaleDetailDTO> salesDetails);

    List<SaleDetailDTO> getSalesDetailsBySaleId(String idSale);
}
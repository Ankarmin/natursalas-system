package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SaleDetailDTO;

import java.util.List;

public interface ISalesDetailDAO {

    boolean addSalesDetail(SaleDetailDTO newSalesDetail);

    boolean updateSalesDetail(SaleDetailDTO updatedSalesDetail);

    boolean deleteSalesDetail(Integer idSale, String idProduct, String idLocation);

    SaleDetailDTO getSalesDetail(Integer idSale, String idProduct, String idLocation);

    List<SaleDetailDTO> getAllSalesDetails();
}
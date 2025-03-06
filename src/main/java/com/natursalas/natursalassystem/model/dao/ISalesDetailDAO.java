package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SaleDetailDTO;

import java.util.List;

public interface ISalesDetailDAO {

    boolean addSalesDetail(SaleDetailDTO newSalesDetail);

    boolean addMultipleSalesDetails(List<SaleDetailDTO> salesDetails);

    boolean updateSalesDetail(SaleDetailDTO updatedSalesDetail);

    boolean deleteSalesDetail(String idSale, String idProduct, String idLocation);

    List<SaleDetailDTO> getSalesDetailsBySaleId(String idSale);

    boolean existsSaleDetail(String idSale, String idProduct, String idLocation);
}
package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SalesDetailDTO;

import java.util.List;

public interface ISalesDetailDAO {

    boolean addSalesDetail(SalesDetailDTO newSalesDetail);

    boolean updateSalesDetail(SalesDetailDTO updatedSalesDetail);

    boolean deleteSalesDetail(Integer idSale, String idProduct, String idLocation);

    SalesDetailDTO getSalesDetail(Integer idSale, String idProduct, String idLocation);

    List<SalesDetailDTO> getAllSalesDetails();
}
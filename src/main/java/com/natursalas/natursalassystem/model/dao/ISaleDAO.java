package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SaleDTO;
import com.natursalas.natursalassystem.model.dto.SaleDetailDTO;

import java.util.List;

public interface ISaleDAO {

    boolean addSale(SaleDTO newSale);

    boolean addSaleWithDetails(SaleDTO sale, List<SaleDetailDTO> details);

    boolean updateSale(SaleDTO updatedSale);

    boolean deleteSale(String idSale);

    List<SaleDTO> getAllSales();

    boolean updateSaleInfo(String idSale, String dni, String diagnosis, String category);

    List<SaleDTO> getSalesByDNI(String dni);

    List<SaleDTO> getSalesByLocation(String idLocation);

    boolean existsSale(String idSale);
}
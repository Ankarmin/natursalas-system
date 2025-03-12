package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.ProductsForLocationDTO;

import java.util.List;

public interface IProductsForLocationDAO {
    ProductsForLocationDTO findProductInLocation(String idProduct, String idLocation);

    List<ProductsForLocationDTO> getProductsForLocation(String idLocation);
}

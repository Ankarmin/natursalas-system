package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.ProductsIncreaseDTO;

import java.util.List;

public interface IProductsIncreaseDAO {
    boolean addProductsIncrease(ProductsIncreaseDTO newProductsIncrease);

    List<ProductsIncreaseDTO> getAllProductsIncreases();

    List<ProductsIncreaseDTO> getProductsIncreaseByLocation(String idLocation);
}
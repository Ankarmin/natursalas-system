package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.ProductsIncreaseDTO;

import java.util.List;

public interface IProductsIncreaseDAO {

    boolean addProductsIncrease(ProductsIncreaseDTO newProductsIncrease);

    boolean updateProductsIncrease(ProductsIncreaseDTO updatedProductsIncrease);

    boolean deleteProductsIncrease(Integer idProductIncrease);

    ProductsIncreaseDTO getProductsIncrease(Integer idProductIncrease);

    List<ProductsIncreaseDTO> getAllProductsIncreases();
}
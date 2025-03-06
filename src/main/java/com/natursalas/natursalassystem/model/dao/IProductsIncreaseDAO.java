package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.ProductsIncreaseDTO;

import java.util.List;

public interface IProductsIncreaseDAO {

    boolean addProductsIncrease(ProductsIncreaseDTO newProductsIncrease);

    boolean updateProductsIncrease(ProductsIncreaseDTO updatedProductsIncrease);

    boolean deleteProductsIncrease(int idProductIncrease);

    ProductsIncreaseDTO getProductsIncrease(int idProductIncrease);

    List<ProductsIncreaseDTO> getAllProductsIncreases();

    boolean existsProductIncrease(int idProductIncrease);

    List<ProductsIncreaseDTO> getProductIncreasesByProductId(String idProduct);
}
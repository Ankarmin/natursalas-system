package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.ProductDTO;

import java.util.List;

public interface IProductDAO {
    boolean addProduct(ProductDTO newProduct);

    boolean updateProduct(ProductDTO updatedProduct);

    ProductDTO getProduct(String idProduct);

    List<ProductDTO> getAllProducts();

    String getProductIdByProductName(String productName);

    boolean existsProduct(String idProduct);
}
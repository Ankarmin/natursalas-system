package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.ProductDTO;

import java.util.List;

public interface IProductDAO {

    boolean addProduct(ProductDTO newProduct);

    boolean updateProduct(ProductDTO updatedProduct);

    boolean deleteProduct(String productId, String locationId);

    ProductDTO getProduct(String productId, String locationId);

    List<ProductDTO> getAllProducts();
}
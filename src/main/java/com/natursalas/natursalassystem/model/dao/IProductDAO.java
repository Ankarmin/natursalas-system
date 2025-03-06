package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.ProductDTO;

import java.util.List;

public interface IProductDAO {
    boolean addProduct(ProductDTO newProduct);

    boolean updateProduct(ProductDTO updatedProduct);

    boolean deleteProduct(String idProduct);

    ProductDTO getProduct(String idProduct);

    List<ProductDTO> getAllProducts();

    boolean existsProduct(String idProduct);
}
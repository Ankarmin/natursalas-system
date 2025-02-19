package com.natursalas.natursalassystem.service;

import com.natursalas.natursalassystem.model.dao.ProductDAO;
import com.natursalas.natursalassystem.model.dto.ProductDTO;

import java.sql.Connection;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(Connection connection) {
        this.productDAO = new ProductDAO(connection);
    }

    public boolean addProduct(ProductDTO newProduct) {
        return productDAO.addProduct(newProduct);
    }

    public boolean updateProduct(ProductDTO updatedProduct) {
        return productDAO.updateProduct(updatedProduct);
    }

    public boolean deleteProduct(String productId, String locationId) {
        return productDAO.deleteProduct(productId, locationId);
    }

    public ProductDTO getProduct(String productId, String locationId) {
        return productDAO.getProduct(productId, locationId);
    }

    public List<ProductDTO> getAllProducts() {
        return productDAO.getAllProducts();
    }
}
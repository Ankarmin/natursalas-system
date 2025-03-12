package com.natursalas.natursalassystem.service;

import com.natursalas.natursalassystem.model.dao.ProductsIncreaseDAO;
import com.natursalas.natursalassystem.model.dto.ProductsIncreaseDTO;

import java.sql.Connection;
import java.util.List;

public class ProductIncreaseService {
    private final ProductsIncreaseDAO productsIncreaseDAO;

    public ProductIncreaseService(Connection connection) {
        this.productsIncreaseDAO = new ProductsIncreaseDAO(connection);
    }

    public boolean addProductsIncrease(ProductsIncreaseDTO newProductsIncrease) {
        return productsIncreaseDAO.addProductsIncrease(newProductsIncrease);
    }

    public List<ProductsIncreaseDTO> getAllProductsIncreases() {
        return productsIncreaseDAO.getAllProductsIncreases();
    }

    public List<ProductsIncreaseDTO> getProductsIncreaseByLocation(String idLocation) {
        return productsIncreaseDAO.getProductsIncreaseByLocation(idLocation);
    }
}
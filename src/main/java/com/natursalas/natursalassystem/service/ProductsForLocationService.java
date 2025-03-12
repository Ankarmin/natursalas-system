package com.natursalas.natursalassystem.service;

import com.natursalas.natursalassystem.model.dao.ProductsForLocationDAO;
import com.natursalas.natursalassystem.model.dto.ProductsForLocationDTO;

import java.sql.Connection;
import java.util.List;

public class ProductsForLocationService {
    private final ProductsForLocationDAO productsForLocationDAO;

    public ProductsForLocationService(Connection connection) {
        this.productsForLocationDAO = new ProductsForLocationDAO(connection);
    }

    public ProductsForLocationDTO getProductInLocation(String productId, String locationId) {
        return productsForLocationDAO.findProductInLocation(productId, locationId);
    }

    public List<ProductsForLocationDTO> getProductsForLocation(String idLocation) {
        return productsForLocationDAO.getProductsForLocation(idLocation);
    }
}
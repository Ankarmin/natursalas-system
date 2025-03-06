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

    public boolean addProductToLocation(ProductsForLocationDTO product) {
        return productsForLocationDAO.insertProductInLocation(product);
    }

    public boolean updateProductStockAndPrice(ProductsForLocationDTO product) {
        return productsForLocationDAO.updateProductStockAndPrice(product);
    }

    public boolean removeProductFromLocation(String productId, String locationId) {
        return productsForLocationDAO.removeProductFromLocation(productId, locationId);
    }

    public ProductsForLocationDTO getProductInLocation(String productId, String locationId) {
        return productsForLocationDAO.findProductInLocation(productId, locationId);
    }

    public List<ProductsForLocationDTO> getAllProductsByLocation() {
        return productsForLocationDAO.listAllProductsByLocation();
    }
}
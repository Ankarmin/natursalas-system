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

    public boolean updateProductsIncrease(ProductsIncreaseDTO updatedProductsIncrease) {
        return productsIncreaseDAO.updateProductsIncrease(updatedProductsIncrease);
    }

    public boolean deleteProductsIncrease(int idProductIncrease) {
        return productsIncreaseDAO.deleteProductsIncrease(idProductIncrease);
    }

    public ProductsIncreaseDTO getProductsIncrease(int idProductIncrease) {
        return productsIncreaseDAO.getProductsIncrease(idProductIncrease);
    }

    public List<ProductsIncreaseDTO> getAllProductsIncreases() {
        return productsIncreaseDAO.getAllProductsIncreases();
    }

    public List<ProductsIncreaseDTO> getProductIncreasesByProductId(String idProduct) {
        return productsIncreaseDAO.getProductIncreasesByProductId(idProduct);
    }

    public List<ProductsIncreaseDTO> getProductsIncreaseByLocation(String idLocation) {
        return productsIncreaseDAO.getProductsIncreaseByLocation(idLocation);
    }

    public boolean existsProductIncrease(int idProductIncrease) {
        return productsIncreaseDAO.existsProductIncrease(idProductIncrease);
    }
}
package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.ProductsIncreaseDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductsIncreaseDAO implements IProductsIncreaseDAO {
    private static final Logger LOGGER = Logger.getLogger(ProductsIncreaseDAO.class.getName());
    private final Connection connection;

    public ProductsIncreaseDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addProductsIncrease(ProductsIncreaseDTO newProductsIncrease) {
        String query = "INSERT INTO productsIncrease (idProduct, dateOfEntry, quantity, idLocation) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newProductsIncrease.getIdProduct());
            stmt.setTimestamp(2, newProductsIncrease.getDateOfEntry());
            stmt.setInt(3, newProductsIncrease.getQuantity());
            stmt.setString(4, newProductsIncrease.getIdLocation());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding product increase", e);
            return false;
        }
    }

    @Override
    public List<ProductsIncreaseDTO> getAllProductsIncreases() {
        List<ProductsIncreaseDTO> productsIncreases = new ArrayList<>();
        String query = "SELECT * FROM productsIncrease";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                productsIncreases.add(new ProductsIncreaseDTO(rs.getInt("idProductIncrease"), rs.getString("idProduct"), rs.getTimestamp("dateOfEntry"), rs.getInt("quantity"), rs.getString("idLocation")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all product increases", e);
        }
        return productsIncreases;
    }

    @Override
    public List<ProductsIncreaseDTO> getProductsIncreaseByLocation(String idLocation) {
        List<ProductsIncreaseDTO> productsIncreases = new ArrayList<>();
        String query = "SELECT * FROM productsIncrease WHERE idLocation = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idLocation);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productsIncreases.add(new ProductsIncreaseDTO(rs.getInt("idProductIncrease"), rs.getString("idProduct"), rs.getTimestamp("dateOfEntry"), rs.getInt("quantity"), rs.getString("idLocation")));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving product increases for location {0}", idLocation);
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        }
        return productsIncreases;
    }
}
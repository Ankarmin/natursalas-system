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
    public boolean updateProductsIncrease(ProductsIncreaseDTO updatedProductsIncrease) {
        if (!existsProductIncrease(updatedProductsIncrease.getIdProductIncrease())) {
            LOGGER.log(Level.WARNING, "Attempted to update non-existing product increase: {0}", updatedProductsIncrease.getIdProductIncrease());
            return false;
        }

        String query = "UPDATE productsIncrease SET idProduct = ?, dateOfEntry = ?, quantity = ?, idLocation = ? WHERE idProductIncrease = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedProductsIncrease.getIdProduct());
            stmt.setTimestamp(2, updatedProductsIncrease.getDateOfEntry());
            stmt.setInt(3, updatedProductsIncrease.getQuantity());
            stmt.setString(4, updatedProductsIncrease.getIdLocation());
            stmt.setInt(5, updatedProductsIncrease.getIdProductIncrease());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating product increase", e);
            return false;
        }
    }

    @Override
    public boolean deleteProductsIncrease(int idProductIncrease) {
        if (!existsProductIncrease(idProductIncrease)) {
            LOGGER.log(Level.WARNING, "Attempted to delete non-existing product increase: {0}", idProductIncrease);
            return false;
        }

        String query = "DELETE FROM productsIncrease WHERE idProductIncrease = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idProductIncrease);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting product increase", e);
            return false;
        }
    }

    @Override
    public ProductsIncreaseDTO getProductsIncrease(int idProductIncrease) {
        String query = "SELECT * FROM productsIncrease WHERE idProductIncrease = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idProductIncrease);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProductsIncreaseDTO(rs.getInt("idProductIncrease"), rs.getString("idProduct"), rs.getTimestamp("dateOfEntry"), rs.getInt("quantity"), rs.getString("idLocation"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving product increase", e);
        }
        return null;
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
    public List<ProductsIncreaseDTO> getProductIncreasesByProductId(String idProduct) {
        List<ProductsIncreaseDTO> productsIncreases = new ArrayList<>();
        String query = "SELECT * FROM productsIncrease WHERE idProduct = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idProduct);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productsIncreases.add(new ProductsIncreaseDTO(rs.getInt("idProductIncrease"), rs.getString("idProduct"), rs.getTimestamp("dateOfEntry"), rs.getInt("quantity"), rs.getString("idLocation")));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving product increases by product ID", e);
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

    @Override
    public boolean existsProductIncrease(int idProductIncrease) {
        String query = "SELECT 1 FROM productsIncrease WHERE idProductIncrease = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idProductIncrease);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking if product increase exists", e);
            return false;
        }
    }
}
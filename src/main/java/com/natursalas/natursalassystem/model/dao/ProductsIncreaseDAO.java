package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.ProductsIncreaseDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsIncreaseDAO implements IProductsIncreaseDAO {
    private Connection connection;

    public ProductsIncreaseDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addProductsIncrease(ProductsIncreaseDTO newProductsIncrease) {
        String query = "INSERT INTO productsIncrease (idProduct, dateOfEntry, quantity, idLocation) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newProductsIncrease.getIdProduct());
            stmt.setDate(2, newProductsIncrease.getDateOfEntry());
            stmt.setInt(3, newProductsIncrease.getQuantity());
            stmt.setString(4, newProductsIncrease.getIdLocation());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProductsIncrease(ProductsIncreaseDTO updatedProductsIncrease) {
        String query = "UPDATE productsIncrease SET idProduct = ?, dateOfEntry = ?, quantity = ?, idLocation = ? WHERE idProductIncrease = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedProductsIncrease.getIdProduct());
            stmt.setDate(2, updatedProductsIncrease.getDateOfEntry());
            stmt.setInt(3, updatedProductsIncrease.getQuantity());
            stmt.setString(4, updatedProductsIncrease.getIdLocation());
            stmt.setInt(5, updatedProductsIncrease.getIdProductIncrease());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProductsIncrease(Integer idProductIncrease) {
        String query = "DELETE FROM productsIncrease WHERE idProductIncrease = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idProductIncrease);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ProductsIncreaseDTO getProductsIncrease(Integer idProductIncrease) {
        String query = "SELECT * FROM productsIncrease WHERE idProductIncrease = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idProductIncrease);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ProductsIncreaseDTO(
                        rs.getInt("idProductIncrease"),
                        rs.getString("idProduct"),
                        rs.getDate("dateOfEntry"),
                        rs.getInt("quantity"),
                        rs.getString("idLocation")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductsIncreaseDTO> getAllProductsIncreases() {
        List<ProductsIncreaseDTO> productsIncreases = new ArrayList<>();
        String query = "SELECT * FROM productsIncrease";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                productsIncreases.add(new ProductsIncreaseDTO(
                        rs.getInt("idProductIncrease"),
                        rs.getString("idProduct"),
                        rs.getDate("dateOfEntry"),
                        rs.getInt("quantity"),
                        rs.getString("idLocation")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsIncreases;
    }
}
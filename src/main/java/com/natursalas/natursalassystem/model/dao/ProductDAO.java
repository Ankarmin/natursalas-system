package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.ProductDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO implements IProductDAO {
    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private final Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addProduct(ProductDTO newProduct) {
        if (existsProduct(newProduct.getIdProduct())) {
            LOGGER.log(Level.WARNING, "Product already exists: {0}", newProduct.getIdProduct());
            return false;
        }

        String query = "INSERT INTO product (idProduct, category, productName, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newProduct.getIdProduct());
            stmt.setString(2, newProduct.getCategory());
            stmt.setString(3, newProduct.getProductName());
            stmt.setInt(4, newProduct.getPrice());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding product", e);
            return false;
        }
    }

    @Override
    public boolean updateProduct(ProductDTO updatedProduct) {
        if (!existsProduct(updatedProduct.getIdProduct())) {
            LOGGER.log(Level.WARNING, "Attempted to update non-existing product: {0}", updatedProduct.getIdProduct());
            return false;
        }

        String query = "UPDATE product SET category = ?, productName = ?, price = ? WHERE idProduct = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedProduct.getCategory());
            stmt.setString(2, updatedProduct.getProductName());
            stmt.setInt(3, updatedProduct.getPrice());
            stmt.setString(4, updatedProduct.getIdProduct());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating product", e);
            return false;
        }
    }

    @Override
    public boolean deleteProduct(String productId) {
        if (!existsProduct(productId)) {
            LOGGER.log(Level.WARNING, "Attempted to delete non-existing product: {0}", productId);
            return false;
        }

        String query = "DELETE FROM product WHERE idProduct = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, productId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting product", e);
            return false;
        }
    }

    @Override
    public ProductDTO getProduct(String productId) {
        String query = "SELECT * FROM product WHERE idProduct = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProductDTO(rs.getString("idProduct"), rs.getString("category"), rs.getString("productName"), rs.getInt("price"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving product", e);
        }
        return null;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        String query = "SELECT * FROM product";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                products.add(new ProductDTO(rs.getString("idProduct"), rs.getString("category"), rs.getString("productName"), rs.getInt("price")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all products", e);
        }
        return products;
    }

    @Override
    public String getProductIdByProductName(String productName) {
        String query = "SELECT idProduct FROM product WHERE productName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, productName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("idProduct");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving product ID by product name", e);
        }
        return null;
    }

    @Override
    public boolean existsProduct(String idProduct) {
        String query = "SELECT COUNT(1) FROM product WHERE idProduct = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idProduct);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking if product exists", e);
            return false;
        }
    }
}
package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.ProductDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private final Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addProduct(ProductDTO newProduct) {
        String query = "INSERT INTO product (idProduct, category, productName, stock, price, idLocation) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newProduct.getIdProduct());
            stmt.setString(2, newProduct.getCategory());
            stmt.setString(3, newProduct.getProductName());
            stmt.setInt(4, newProduct.getStock());
            stmt.setInt(5, newProduct.getPrice());
            stmt.setString(6, newProduct.getIdLocation());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProduct(ProductDTO updatedProduct) {
        String query = "UPDATE product SET category = ?, productName = ?, stock = ?, price = ? WHERE idProduct = ? AND idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedProduct.getCategory());
            stmt.setString(2, updatedProduct.getProductName());
            stmt.setInt(3, updatedProduct.getStock());
            stmt.setInt(4, updatedProduct.getPrice());
            stmt.setString(5, updatedProduct.getIdProduct());
            stmt.setString(6, updatedProduct.getIdLocation());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(String productId, String locationId) {
        String query = "DELETE FROM product WHERE idProduct = ? AND idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, productId);
            stmt.setString(2, locationId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ProductDTO getProduct(String productId, String locationId) {
        String query = "SELECT * FROM product WHERE idProduct = ? AND idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, productId);
            stmt.setString(2, locationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ProductDTO(rs.getString("idProduct"), rs.getString("category"), rs.getString("productName"), rs.getInt("stock"), rs.getInt("price"), rs.getString("idLocation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        String query = "SELECT * FROM product";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                products.add(new ProductDTO(rs.getString("idProduct"), rs.getString("category"), rs.getString("productName"), rs.getInt("stock"), rs.getInt("price"), rs.getString("idLocation")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
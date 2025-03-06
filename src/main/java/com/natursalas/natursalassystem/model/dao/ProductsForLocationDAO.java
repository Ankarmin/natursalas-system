package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.ProductsForLocationDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductsForLocationDAO implements IProductsForLocationDAO {
    private static final Logger LOGGER = Logger.getLogger(ProductsForLocationDAO.class.getName());
    private final Connection connection;

    public ProductsForLocationDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insertProductInLocation(ProductsForLocationDTO product) {
        if (existsProductInLocation(product.getIdProduct(), product.getIdLocation())) {
            LOGGER.log(Level.WARNING, "Product already exists in location: {0} - {1}", new Object[]{product.getIdProduct(), product.getIdLocation()});
            return false;
        }

        String query = "INSERT INTO productsForLocation (idProduct, category, productName, price, idLocation, stock) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getIdProduct());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getProductName());
            stmt.setInt(4, product.getPrice());
            stmt.setString(5, product.getIdLocation());
            stmt.setInt(6, product.getStock());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting product in location", e);
            return false;
        }
    }

    @Override
    public boolean updateProductStockAndPrice(ProductsForLocationDTO product) {
        if (!existsProductInLocation(product.getIdProduct(), product.getIdLocation())) {
            LOGGER.log(Level.WARNING, "Attempted to update non-existing product in location: {0} - {1}", new Object[]{product.getIdProduct(), product.getIdLocation()});
            return false;
        }

        String query = "UPDATE productsForLocation SET price = ?, stock = ? WHERE idProduct = ? AND idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, product.getPrice());
            stmt.setInt(2, product.getStock());
            stmt.setString(3, product.getIdProduct());
            stmt.setString(4, product.getIdLocation());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating product stock and price", e);
            return false;
        }
    }

    @Override
    public boolean removeProductFromLocation(String productId, String locationId) {
        if (!existsProductInLocation(productId, locationId)) {
            LOGGER.log(Level.WARNING, "Attempted to remove non-existing product from location: {0} - {1}", new Object[]{productId, locationId});
            return false;
        }

        String query = "DELETE FROM productsForLocation WHERE idProduct = ? AND idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, productId);
            stmt.setString(2, locationId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error removing product from location", e);
            return false;
        }
    }

    @Override
    public ProductsForLocationDTO findProductInLocation(String productId, String locationId) {
        String query = "SELECT * FROM productsForLocation WHERE idProduct = ? AND idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, productId);
            stmt.setString(2, locationId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProductsForLocationDTO(rs.getString("idProduct"), rs.getString("category"), rs.getString("productName"), rs.getInt("price"), rs.getString("idLocation"), rs.getInt("stock"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error finding product in location", e);
        }
        return null;
    }

    @Override
    public List<ProductsForLocationDTO> listAllProductsByLocation() {
        List<ProductsForLocationDTO> products = new ArrayList<>();
        String query = "SELECT * FROM productsForLocation";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                products.add(new ProductsForLocationDTO(rs.getString("idProduct"), rs.getString("category"), rs.getString("productName"), rs.getInt("price"), rs.getString("idLocation"), rs.getInt("stock")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all products by location", e);
        }
        return products;
    }

    @Override
    public boolean existsProductInLocation(String idProduct, String idLocation) {
        String query = "SELECT COUNT(1) FROM productsForLocation WHERE idProduct = ? AND idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idProduct);
            stmt.setString(2, idLocation);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking if product exists in location", e);
            return false;
        }
    }
}
package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.ProductsForLocationDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public ProductsForLocationDTO findProductInLocation(String productId, String locationId) {
        String query = "SELECT * FROM productsForLocation WHERE idProduct = ? AND idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, productId);
            stmt.setString(2, locationId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProductsForLocationDTO(rs.getString("idProduct"), rs.getString("idLocation"), rs.getInt("stock"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error finding product in location", e);
        }
        return null;
    }

    @Override
    public List<ProductsForLocationDTO> getProductsForLocation(String idLocation) {
        List<ProductsForLocationDTO> products = new ArrayList<>();
        String query = "SELECT * FROM productsForLocation WHERE idLocation = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idLocation);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(new ProductsForLocationDTO(rs.getString("idProduct"), rs.getString("idLocation"), rs.getInt("stock")));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving products for location {0}", idLocation);
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        }
        return products;
    }
}
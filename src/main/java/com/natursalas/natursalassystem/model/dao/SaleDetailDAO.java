package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SaleDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleDetailDAO implements ISalesDetailDAO {
    private static final Logger LOGGER = Logger.getLogger(SaleDetailDAO.class.getName());
    private final Connection connection;

    public SaleDetailDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addSalesDetail(SaleDetailDTO newSalesDetail) {
        String query = "INSERT INTO salesDetail (idSale, idProduct, idLocation, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newSalesDetail.getIdSale());
            stmt.setString(2, newSalesDetail.getIdProduct());
            stmt.setString(3, newSalesDetail.getIdLocation());
            stmt.setInt(4, newSalesDetail.getQuantity());
            stmt.setInt(5, newSalesDetail.getPrice());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding sales detail", e);
            return false;
        }
    }

    @Override
    public boolean addMultipleSalesDetails(List<SaleDetailDTO> salesDetails) {
        String query = "INSERT INTO salesDetail (idSale, idProduct, idLocation, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (SaleDetailDTO detail : salesDetails) {
                stmt.setString(1, detail.getIdSale());
                stmt.setString(2, detail.getIdProduct());
                stmt.setString(3, detail.getIdLocation());
                stmt.setInt(4, detail.getQuantity());
                stmt.setInt(5, detail.getPrice());
                stmt.addBatch();
            }
            stmt.executeBatch();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding multiple sales details", e);
            return false;
        }
    }

    @Override
    public boolean updateSalesDetail(SaleDetailDTO updatedSalesDetail) {
        if (!existsSaleDetail(updatedSalesDetail.getIdSale(), updatedSalesDetail.getIdProduct(), updatedSalesDetail.getIdLocation())) {
            LOGGER.log(Level.WARNING, "Attempted to update non-existing sale detail: {0}", updatedSalesDetail.getIdSale());
            return false;
        }

        String query = "UPDATE salesDetail SET quantity = ?, price = ? WHERE idSale = ? AND idProduct = ? AND idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, updatedSalesDetail.getQuantity());
            stmt.setInt(2, updatedSalesDetail.getPrice());
            stmt.setString(3, updatedSalesDetail.getIdSale());
            stmt.setString(4, updatedSalesDetail.getIdProduct());
            stmt.setString(5, updatedSalesDetail.getIdLocation());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating sales detail", e);
            return false;
        }
    }

    @Override
    public boolean deleteSalesDetail(String idSale, String idProduct, String idLocation) {
        if (!existsSaleDetail(idSale, idProduct, idLocation)) {
            LOGGER.log(Level.WARNING, "Attempted to delete non-existing sale detail: {0}", idSale);
            return false;
        }

        String query = "DELETE FROM salesDetail WHERE idSale = ? AND idProduct = ? AND idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idSale);
            stmt.setString(2, idProduct);
            stmt.setString(3, idLocation);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting sales detail", e);
            return false;
        }
    }

    @Override
    public List<SaleDetailDTO> getSalesDetailsBySaleId(String idSale) {
        List<SaleDetailDTO> salesDetails = new ArrayList<>();
        String query = "SELECT * FROM salesDetail WHERE idSale = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idSale);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    salesDetails.add(new SaleDetailDTO(rs.getString("idSale"), rs.getString("idProduct"), rs.getString("idLocation"), rs.getInt("quantity"), rs.getInt("price"), rs.getInt("subtotal")));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving sales details by sale ID: " + idSale, e);
        }

        return salesDetails;
    }

    @Override
    public boolean existsSaleDetail(String idSale, String idProduct, String idLocation) {
        String query = "SELECT 1 FROM salesDetail WHERE idSale = ? AND idProduct = ? AND idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idSale);
            stmt.setString(2, idProduct);
            stmt.setString(3, idLocation);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking if sale detail exists", e);
            return false;
        }
    }
}
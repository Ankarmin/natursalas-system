package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SaleDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleDAO implements ISaleDAO {
    private static final Logger LOGGER = Logger.getLogger(SaleDAO.class.getName());
    private final Connection connection;

    public SaleDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean updateSale(SaleDTO updatedSale) {
        if (!existsSale(updatedSale.getIdSale())) {
            LOGGER.log(Level.WARNING, "Attempted to update non-existing sale: {0}", updatedSale.getIdSale());
            return false;
        }

        String query = "UPDATE sale SET DNI = ?, diagnosis = ?, category = ?, idLocation = ?, subtotal = ? WHERE idSale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedSale.getDNI());
            stmt.setString(2, updatedSale.getDiagnosis());
            stmt.setString(3, updatedSale.getCategory());
            stmt.setString(4, updatedSale.getIdLocation());
            stmt.setBigDecimal(5, updatedSale.getSubtotal());
            stmt.setString(6, updatedSale.getIdSale());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating sale", e);
            return false;
        }
    }

    @Override
    public List<SaleDTO> getAllSales() {
        List<SaleDTO> salesList = new ArrayList<>();
        String query = "SELECT * FROM sale ORDER BY saleDate DESC";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                salesList.add(new SaleDTO(rs.getString("idSale"), rs.getString("DNI"), rs.getString("diagnosis"), rs.getString("category"), rs.getTimestamp("saleDate"), rs.getString("idLocation"), rs.getBigDecimal("subtotal")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all sales", e);
        }
        return salesList;
    }

    @Override
    public List<SaleDTO> getSalesByDNI(String dni) {
        List<SaleDTO> salesList = new ArrayList<>();
        String query = "SELECT * FROM sale WHERE DNI = ? ORDER BY saleDate DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, dni);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    salesList.add(new SaleDTO(rs.getString("idSale"), rs.getString("DNI"), rs.getString("diagnosis"), rs.getString("category"), rs.getTimestamp("saleDate"), rs.getString("idLocation"), rs.getBigDecimal("subtotal")));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving sales by DNI: " + dni, e);
        }
        return salesList;
    }

    @Override
    public List<SaleDTO> getSalesByLocation(String idLocation) {
        List<SaleDTO> salesList = new ArrayList<>();
        String query = "SELECT * FROM sale WHERE idLocation = ? ORDER BY saleDate DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idLocation);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    salesList.add(new SaleDTO(rs.getString("idSale"), rs.getString("DNI"), rs.getString("diagnosis"), rs.getString("category"), rs.getTimestamp("saleDate"), rs.getString("idLocation"), rs.getBigDecimal("subtotal")));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving sales by location: " + idLocation, e);
        }
        return salesList;
    }

    public boolean existsSale(String idSale) {
        String query = "SELECT 1 FROM sale WHERE idSale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idSale);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking if sale exists", e);
            return false;
        }
    }
}
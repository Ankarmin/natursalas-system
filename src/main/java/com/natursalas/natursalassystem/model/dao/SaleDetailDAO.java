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
    public boolean addSalesDetails(String idSale, List<SaleDetailDTO> salesDetails) {
        if (salesDetails.isEmpty()) {
            LOGGER.log(Level.WARNING, "Intento de agregar detalles de venta sin productos.");
            return false;
        }

        String query = "INSERT INTO salesDetail (idSale, idProduct, idLocation, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (SaleDetailDTO detail : salesDetails) {
                stmt.setString(1, idSale);
                stmt.setString(2, detail.getIdProduct());
                stmt.setString(3, detail.getIdLocation());
                stmt.setInt(4, detail.getQuantity());
                stmt.setBigDecimal(5, detail.getPrice());
                stmt.addBatch();
            }
            stmt.executeBatch();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al agregar múltiples detalles de venta con el mismo ID de venta.", e);
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
                    salesDetails.add(new SaleDetailDTO(rs.getString("idSale"), rs.getString("idProduct"), rs.getString("idLocation"), rs.getInt("quantity"), rs.getBigDecimal("price"), rs.getBigDecimal("subtotal")));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving sales details by sale ID: " + idSale, e);
        }

        return salesDetails;
    }
}
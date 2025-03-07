package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SaleDTO;
import com.natursalas.natursalassystem.model.dto.SaleDetailDTO;

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
    public boolean addSale(SaleDTO newSale) {
        String query = "INSERT INTO sale (idSale, DNI, diagnosis, category, idLocation, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newSale.getIdSale());
            stmt.setString(2, newSale.getDNI());
            stmt.setString(3, newSale.getDiagnosis());
            stmt.setString(4, newSale.getCategory());
            stmt.setString(5, newSale.getIdLocation());
            stmt.setInt(6, newSale.getSubtotal());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding sale", e);
            return false;
        }
    }

    public boolean addSaleWithDetails(SaleDTO sale, List<SaleDetailDTO> details) {
        String saleQuery = "INSERT INTO sale (idSale, DNI, diagnosis, category, idLocation, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
        String detailQuery = "INSERT INTO salesDetail (idSale, idProduct, idLocation, quantity, price) VALUES (?, ?, ?, ?, ?)";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement saleStmt = connection.prepareStatement(saleQuery); PreparedStatement detailStmt = connection.prepareStatement(detailQuery)) {

                // Insertar venta
                saleStmt.setString(1, sale.getIdSale());
                saleStmt.setString(2, sale.getDNI());
                saleStmt.setString(3, sale.getDiagnosis());
                saleStmt.setString(4, sale.getCategory());
                saleStmt.setString(5, sale.getIdLocation());
                saleStmt.setInt(6, sale.getSubtotal());
                saleStmt.executeUpdate();

                // Insertar detalles de venta
                for (SaleDetailDTO detail : details) {
                    detailStmt.setString(1, detail.getIdSale());
                    detailStmt.setString(2, detail.getIdProduct());
                    detailStmt.setString(3, detail.getIdLocation());
                    detailStmt.setInt(4, detail.getQuantity());
                    detailStmt.setInt(5, detail.getPrice());
                    detailStmt.addBatch();
                }
                detailStmt.executeBatch();

                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Rollback failed", ex);
            }
            LOGGER.log(Level.SEVERE, "Error adding sale with details", e);
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Failed to reset auto-commit", e);
            }
        }
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
            stmt.setInt(5, updatedSale.getSubtotal());
            stmt.setString(6, updatedSale.getIdSale());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating sale", e);
            return false;
        }
    }

    @Override
    public boolean deleteSale(String idSale) {
        if (!existsSale(idSale)) {
            LOGGER.log(Level.WARNING, "Attempted to delete non-existing sale: {0}", idSale);
            return false;
        }

        String query = "DELETE FROM sale WHERE idSale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idSale);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting sale", e);
            return false;
        }
    }

    @Override
    public List<SaleDTO> getAllSales() {
        List<SaleDTO> salesList = new ArrayList<>();
        String query = "SELECT * FROM sale ORDER BY saleDate DESC";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                salesList.add(new SaleDTO(rs.getString("idSale"), rs.getString("DNI"), rs.getString("diagnosis"), rs.getString("category"), rs.getTimestamp("saleDate"), rs.getString("idLocation"), rs.getInt("subtotal")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all sales", e);
        }
        return salesList;
    }

    @Override
    public boolean updateSaleInfo(String idSale, String dni, String diagnosis, String category) {
        String query = "UPDATE sale SET DNI = ?, diagnosis = ?, category = ? WHERE idSale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, dni);
            stmt.setString(2, diagnosis);
            stmt.setString(3, category);
            stmt.setString(4, idSale);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating sale info", e);
            return false;
        }
    }

    @Override
    public List<SaleDTO> getSalesByDNI(String dni) {
        List<SaleDTO> salesList = new ArrayList<>();
        String query = "SELECT * FROM sale WHERE DNI = ? ORDER BY saleDate DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, dni);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    salesList.add(new SaleDTO(rs.getString("idSale"), rs.getString("DNI"), rs.getString("diagnosis"), rs.getString("category"), rs.getTimestamp("saleDate"), rs.getString("idLocation"), rs.getInt("subtotal")));
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
                    salesList.add(new SaleDTO(
                            rs.getString("idSale"),
                            rs.getString("DNI"),
                            rs.getString("diagnosis"),
                            rs.getString("category"),
                            rs.getTimestamp("saleDate"),
                            rs.getString("idLocation"),
                            rs.getInt("subtotal")
                    ));
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
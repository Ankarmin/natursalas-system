package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SalesDetailDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDetailDAO implements ISalesDetailDAO {
    private final Connection connection;

    public SalesDetailDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addSalesDetail(SalesDetailDTO newSalesDetail) {
        String query = "INSERT INTO salesDetail (idSale, idProduct, idLocation, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newSalesDetail.getIdSale());
            stmt.setString(2, newSalesDetail.getIdProduct());
            stmt.setString(3, newSalesDetail.getIdLocation());
            stmt.setInt(4, newSalesDetail.getQuantity());
            stmt.setInt(5, newSalesDetail.getPrice());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSalesDetail(SalesDetailDTO updatedSalesDetail) {
        String query = "UPDATE salesDetail SET quantity = ?, price = ? WHERE idSale = ? AND idProduct = ? AND idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, updatedSalesDetail.getQuantity());
            stmt.setInt(2, updatedSalesDetail.getPrice());
            stmt.setString(3, updatedSalesDetail.getIdSale());
            stmt.setString(4, updatedSalesDetail.getIdProduct());
            stmt.setString(5, updatedSalesDetail.getIdLocation());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSalesDetail(Integer idSale, String idProduct, String idLocation) {
        String query = "DELETE FROM salesDetail WHERE idSale = ? AND idProduct = ? AND idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idSale);
            stmt.setString(2, idProduct);
            stmt.setString(3, idLocation);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SalesDetailDTO getSalesDetail(Integer idSale, String idProduct, String idLocation) {
        String query = "SELECT * FROM salesDetail WHERE idSale = ? AND idProduct = ? AND idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idSale);
            stmt.setString(2, idProduct);
            stmt.setString(3, idLocation);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SalesDetailDTO(rs.getString("idSale"), rs.getString("idProduct"), rs.getString("idLocation"), rs.getInt("quantity"), rs.getInt("price"), rs.getInt("subtotal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SalesDetailDTO> getAllSalesDetails() {
        List<SalesDetailDTO> salesDetails = new ArrayList<>();
        String query = "SELECT * FROM salesDetail";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                salesDetails.add(new SalesDetailDTO(rs.getString("idSale"), rs.getString("idProduct"), rs.getString("idLocation"), rs.getInt("quantity"), rs.getInt("price"), rs.getInt("subtotal")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesDetails;
    }

    public List<SalesDetailDTO> getSalesDetailsBySaleId(String idSale) {
        List<SalesDetailDTO> salesDetails = new ArrayList<>();
        String query = "SELECT * FROM salesDetail WHERE idSale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idSale);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                salesDetails.add(new SalesDetailDTO(rs.getString("idSale"), rs.getString("idProduct"), rs.getString("idLocation"), rs.getInt("quantity"), rs.getInt("price"), rs.getInt("subtotal")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesDetails;
    }
}
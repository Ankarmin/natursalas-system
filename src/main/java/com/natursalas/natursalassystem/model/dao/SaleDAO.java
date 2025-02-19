package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SaleDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO implements ISaleDAO {
    private final Connection connection;

    public SaleDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addSale(SaleDTO newSale) {
        String query = "INSERT INTO sale (idSale, DNI, diagnosis, category, saleDate, idLocation) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newSale.getIdSale());
            stmt.setString(2, newSale.getDNI());
            stmt.setString(3, newSale.getDiagnosis());
            stmt.setString(4, newSale.getCategory());
            stmt.setTimestamp(5, newSale.getSaleDate());
            stmt.setString(6, newSale.getIdLocation());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSale(SaleDTO updatedSale) {
        String query = "UPDATE sale SET DNI = ?, diagnosis = ?, category = ?, saleDate = ?, idLocation = ? WHERE idSale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedSale.getDNI());
            stmt.setString(2, updatedSale.getDiagnosis());
            stmt.setString(3, updatedSale.getCategory());
            stmt.setTimestamp(4, updatedSale.getSaleDate());
            stmt.setString(5, updatedSale.getIdLocation());
            stmt.setString(6, updatedSale.getIdSale());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSale(String idSale) {
        String query = "DELETE FROM sale WHERE idSale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idSale);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SaleDTO getSale(String idSale) {
        String query = "SELECT * FROM sale WHERE idSale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idSale);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SaleDTO(rs.getString("idSale"), rs.getString("DNI"), rs.getString("diagnosis"), rs.getString("category"), rs.getTimestamp("saleDate"), rs.getString("idLocation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SaleDTO> getAllSales() {
        List<SaleDTO> sales = new ArrayList<>();
        String query = "SELECT * FROM sale";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                sales.add(new SaleDTO(rs.getString("idSale"), rs.getString("DNI"), rs.getString("diagnosis"), rs.getString("category"), rs.getTimestamp("saleDate"), rs.getString("idLocation")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    public List<SaleDTO> getSalesByDNI(String dni) {
        List<SaleDTO> sales = new ArrayList<>();
        String query = "SELECT * FROM sale WHERE DNI = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sales.add(new SaleDTO(rs.getString("idSale"), rs.getString("DNI"), rs.getString("diagnosis"), rs.getString("category"), rs.getTimestamp("saleDate"), rs.getString("idLocation")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }
}
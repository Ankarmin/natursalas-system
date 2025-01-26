package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.SaleDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO implements ISaleDAO {
    private Connection connection;

    public SaleDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addSale(SaleDTO newSale) {
        String query = "INSERT INTO sale (idPatient, diagnosis, category, saleDate, idLocation, idUser) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newSale.getIdPatient());
            stmt.setString(2, newSale.getDiagnosis());
            stmt.setString(3, newSale.getCategory());
            stmt.setTimestamp(4, newSale.getSaleDate());
            stmt.setString(5, newSale.getIdLocation());
            stmt.setInt(6, newSale.getIdUser());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSale(SaleDTO updatedSale) {
        String query = "UPDATE sale SET idPatient = ?, diagnosis = ?, category = ?, saleDate = ?, idLocation = ?, idUser = ? WHERE idSale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedSale.getIdPatient());
            stmt.setString(2, updatedSale.getDiagnosis());
            stmt.setString(3, updatedSale.getCategory());
            stmt.setTimestamp(4, updatedSale.getSaleDate());
            stmt.setString(5, updatedSale.getIdLocation());
            stmt.setInt(6, updatedSale.getIdUser());
            stmt.setInt(7, updatedSale.getIdSale());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSale(Integer saleId) {
        String query = "DELETE FROM sale WHERE idSale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, saleId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SaleDTO getSale(Integer saleId) {
        String query = "SELECT * FROM sale WHERE idSale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, saleId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SaleDTO(rs.getInt("idSale"), rs.getString("idPatient"), rs.getString("diagnosis"), rs.getString("category"), rs.getTimestamp("saleDate"), rs.getString("idLocation"), rs.getInt("idUser"));
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
                sales.add(new SaleDTO(rs.getInt("idSale"), rs.getString("idPatient"), rs.getString("diagnosis"), rs.getString("category"), rs.getTimestamp("saleDate"), rs.getString("idLocation"), rs.getInt("idUser")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }
}
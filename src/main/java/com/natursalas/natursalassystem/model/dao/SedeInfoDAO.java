package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.PatientDTO;
import com.natursalas.natursalassystem.model.dto.SedeInfoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SedeInfoDAO implements ISedeInfoDAO {
    private static final Logger LOGGER = Logger.getLogger(SedeInfoDAO.class.getName());
    private final Connection connection;

    public SedeInfoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public SedeInfoDTO getSedeInfo(String idLocation) {
        return new SedeInfoDTO(getBestSellingProduct(idLocation), getProductsSoldToday(idLocation), getPatientsAttendedToday(idLocation), getNewPatientsToday(idLocation), getPatientsAttendedTodayList(idLocation));
    }

    private String getBestSellingProduct(String idLocation) {
        String query = """
                SELECT p.productName, SUM(sd.quantity) AS total_vendido
                FROM salesDetail sd
                JOIN sale s ON sd.idSale = s.idSale
                JOIN product p ON sd.idProduct = p.idProduct
                WHERE s.idLocation = ? AND DATE(s.saleDate) = CURDATE()
                GROUP BY p.productName
                ORDER BY total_vendido DESC
                LIMIT 1;
                """;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idLocation);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getString("productName") : "Sin ventas hoy";
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener el producto más vendido. Query: " + query, e);
            return "Error al obtener datos";
        }
    }

    private int getProductsSoldToday(String idLocation) {
        return executeSingleIntQuery("""
                 SELECT COALESCE(SUM(sd.quantity), 0)
                 FROM salesDetail sd
                 JOIN sale s ON sd.idSale = s.idSale
                 WHERE s.idLocation = ? AND DATE(s.saleDate) = CURDATE();
                """, idLocation);
    }

    private int getPatientsAttendedToday(String idLocation) {
        return executeSingleIntQuery("""
                SELECT COUNT(DISTINCT s.DNI)
                FROM sale s
                WHERE s.idLocation = ? AND DATE(s.saleDate) = CURDATE();
                """, idLocation);
    }

    private int getNewPatientsToday(String idLocation) {
        return executeSingleIntQuery("""
                SELECT COUNT(*)
                FROM patient
                WHERE idLocation = ? AND DATE(dateOfEntry) = CURDATE();
                """, idLocation);
    }

    private List<PatientDTO> getPatientsAttendedTodayList(String idLocation) {
        List<PatientDTO> patients = new ArrayList<>();
        String query = """
                SELECT DISTINCT p.DNI, p.firstName, p.lastName, p.age, p.phoneNumber, p.dateOfEntry, p.dateOfBirth, p.idLocation
                FROM sale s
                JOIN patient p ON s.DNI = p.DNI
                WHERE s.idLocation = ? AND DATE(s.saleDate) = CURDATE();
                """;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idLocation);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    patients.add(new PatientDTO(rs.getString("DNI"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("age"), rs.getString("phoneNumber"), rs.getTimestamp("dateOfEntry"), rs.getDate("dateOfBirth"), rs.getString("idLocation")));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener la lista de pacientes atendidos hoy. Query: " + query, e);
        }
        return patients;
    }

    private int executeSingleIntQuery(String query, String idLocation) {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idLocation);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error ejecutando consulta. Query: " + query, e);
            return 0;
        }
    }
}
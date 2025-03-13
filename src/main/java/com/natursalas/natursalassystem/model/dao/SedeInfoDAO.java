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
        String bestSellingProduct = getBestSellingProduct(idLocation);
        int productsSoldToday = getProductsSoldToday(idLocation);
        int patientsAttendedToday = executeSingleIntQuery("""
                 SELECT COUNT(DISTINCT s.DNI)\s
                 FROM sale s\s
                 WHERE s.idLocation = ? AND DATE(s.saleDate) = CURDATE();
                \s""", idLocation);
        int newPatientsToday = executeSingleIntQuery("""
                 SELECT COUNT(*)\s
                 FROM patient\s
                 WHERE idLocation = ? AND DATE(dateOfEntry) = CURDATE();
                \s""", idLocation);
        List<PatientDTO> patientsAttendedTodayList = getPatientsAttendedTodayList(idLocation);

        return new SedeInfoDTO(bestSellingProduct, productsSoldToday, patientsAttendedToday, newPatientsToday, patientsAttendedTodayList);
    }

    private String getBestSellingProduct(String idLocation) {
        String query = """
                SELECT p.productName, COUNT(sd.idProduct) as cantidad
                FROM salesDetail sd
                JOIN sale s ON sd.idSale = s.idSale
                JOIN product p ON sd.idProduct = p.idProduct
                WHERE s.idLocation = ? AND DATE(s.saleDate) = CURDATE()
                GROUP BY sd.idProduct, p.productName
                ORDER BY cantidad DESC
                LIMIT 1;
                """;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idLocation);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("productName");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener el producto más vendido", e);
        }
        return "Sin ventas hoy";
    }

    private int getProductsSoldToday(String idLocation) {
        String query = """
                 SELECT COALESCE(SUM(sd.quantity), 0)\s
                 FROM salesDetail sd
                 JOIN sale s ON sd.idSale = s.idSale
                 WHERE s.idLocation = ? AND DATE(s.saleDate) = CURDATE();
                \s""";
        return executeSingleIntQuery(query, idLocation);
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
            LOGGER.log(Level.SEVERE, "Error al obtener la lista de pacientes atendidos hoy", e);
        }
        return patients;
    }

    private int executeSingleIntQuery(String query, String idLocation) {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idLocation);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error ejecutando consulta", e);
        }
        return 0;
    }
}
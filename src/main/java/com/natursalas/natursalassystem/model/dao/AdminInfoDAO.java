package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.AdminInfoDTO;
import com.natursalas.natursalassystem.model.dto.ViewAdminInfoDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminInfoDAO implements IAdminInfoDAO {
    private static final Logger LOGGER = Logger.getLogger(AdminInfoDAO.class.getName());
    private final Connection connection;

    public AdminInfoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public AdminInfoDTO getAdminInfo() {
        String bestSellingLocation = getBestSellingLocation();
        int productsSoldToday = executeSingleIntQuery("""
                 SELECT COALESCE(SUM(sd.quantity), 0)\s
                 FROM salesDetail sd\s
                 JOIN sale s ON sd.idSale = s.idSale\s
                 WHERE DATE(s.saleDate) = CURDATE();
                \s""");
        int patientsAttendedToday = executeSingleIntQuery("""
                 SELECT COUNT(DISTINCT s.DNI)\s
                 FROM sale s\s
                 WHERE DATE(s.saleDate) = CURDATE();
                \s""");
        int newPatientsToday = executeSingleIntQuery("""
                 SELECT COUNT(*)\s
                 FROM patient\s
                 WHERE DATE(dateOfEntry) = CURDATE();
                \s""");

        List<ViewAdminInfoDTO> adminInfoList = getViewAdminInfoList();

        return new AdminInfoDTO(bestSellingLocation, productsSoldToday, patientsAttendedToday, newPatientsToday, adminInfoList);
    }

    private String getBestSellingLocation() {
        String query = """
                SELECT s.idLocation, SUM(sd.quantity) AS total_vendidos
                FROM salesDetail sd
                JOIN sale s ON sd.idSale = s.idSale
                WHERE DATE(s.saleDate) = CURDATE()
                GROUP BY s.idLocation
                ORDER BY total_vendidos DESC
                LIMIT 1;
                """;
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getString("idLocation");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener la sede con más ventas", e);
        }
        return "Sin ventas hoy";
    }

    private List<ViewAdminInfoDTO> getViewAdminInfoList() {
        List<ViewAdminInfoDTO> adminInfoList = new ArrayList<>();
        String query = """
                SELECT l.idLocation,
                       COALESCE(SUM(sd.quantity), 0) AS productosVendidos,
                       (SELECT p.productName
                        FROM salesDetail sd2
                        JOIN sale s2 ON sd2.idSale = s2.idSale
                        JOIN product p ON sd2.idProduct = p.idProduct
                        WHERE s2.idLocation = l.idLocation AND DATE(s2.saleDate) = CURDATE()
                        GROUP BY sd2.idProduct, p.productName
                        ORDER BY COUNT(sd2.idProduct) DESC
                        LIMIT 1) AS productoMasVendido,
                       (SELECT COUNT(DISTINCT s3.DNI)
                        FROM sale s3
                        WHERE s3.idLocation = l.idLocation AND DATE(s3.saleDate) = CURDATE()) AS pacientesAtendidos,
                       (SELECT COUNT(*)
                        FROM patient p2
                        WHERE p2.idLocation = l.idLocation AND DATE(p2.dateOfEntry) = CURDATE()) AS nuevosPacientes
                FROM location l
                LEFT JOIN sale s ON l.idLocation = s.idLocation
                LEFT JOIN salesDetail sd ON s.idSale = sd.idSale
                GROUP BY l.idLocation;
                """;
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                adminInfoList.add(new ViewAdminInfoDTO(rs.getString("idLocation"), rs.getInt("productosVendidos"), rs.getString("productoMasVendido") != null ? rs.getString("productoMasVendido") : "Sin ventas", rs.getInt("pacientesAtendidos"), rs.getInt("nuevosPacientes")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener la información de las sedes", e);
        }
        return adminInfoList;
    }

    private int executeSingleIntQuery(String query) {
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error ejecutando consulta", e);
        }
        return 0;
    }
}
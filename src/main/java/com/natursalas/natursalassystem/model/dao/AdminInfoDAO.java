package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.AdminInfoDTO;
import com.natursalas.natursalassystem.model.dto.ViewAdminInfoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return new AdminInfoDTO(getBestSellingLocation(), getProductsSoldToday(), getPatientsAttendedToday(), getNewPatientsToday(), getViewAdminInfoList());
    }

    /**
     * Obtiene la sede con más productos vendidos en el día actual.
     */
    private String getBestSellingLocation() {
        String query = """
                SELECT s.idLocation, COALESCE(SUM(sd.quantity), 0) AS total_vendidos
                FROM salesDetail sd
                JOIN sale s ON sd.idSale = s.idSale
                WHERE DATE(s.saleDate) = CURDATE()
                GROUP BY s.idLocation
                ORDER BY total_vendidos DESC
                LIMIT 1;
                """;
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getString("idLocation") : "Sin ventas hoy";
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener la sede con más ventas. Query: " + query, e);
            return "Error al obtener datos";
        }
    }

    /**
     * Obtiene la cantidad total de productos vendidos hoy en todas las sedes.
     */
    private int getProductsSoldToday() {
        return executeSingleIntQuery("""
                 SELECT COALESCE(SUM(sd.quantity), 0)
                 FROM salesDetail sd
                 JOIN sale s ON sd.idSale = s.idSale
                 WHERE DATE(s.saleDate) = CURDATE();
                """);
    }

    /**
     * Obtiene el número de pacientes atendidos hoy en todas las sedes.
     */
    private int getPatientsAttendedToday() {
        return executeSingleIntQuery("""
                 SELECT COUNT(DISTINCT s.DNI)
                 FROM sale s
                 WHERE DATE(s.saleDate) = CURDATE();
                """);
    }

    /**
     * Obtiene el número de nuevos pacientes registrados hoy en todas las sedes.
     */
    private int getNewPatientsToday() {
        return executeSingleIntQuery("""
                 SELECT COUNT(*)
                 FROM patient
                 WHERE DATE(dateOfEntry) = CURDATE();
                """);
    }

    /**
     * Obtiene la información de todas las sedes con datos de ventas y pacientes.
     */
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
                        GROUP BY p.productName
                        ORDER BY SUM(sd2.quantity) DESC
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
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                adminInfoList.add(new ViewAdminInfoDTO(rs.getString("idLocation"), rs.getInt("productosVendidos"), rs.getString("productoMasVendido") != null ? rs.getString("productoMasVendido") : "Sin producto vendido", rs.getInt("pacientesAtendidos"), rs.getInt("nuevosPacientes")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener la información de las sedes. Query: " + query, e);
        }
        return adminInfoList;
    }

    /**
     * Ejecuta una consulta que devuelve un solo valor entero.
     */
    private int executeSingleIntQuery(String query) {
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error ejecutando consulta. Query: " + query, e);
            return 0;
        }
    }
}
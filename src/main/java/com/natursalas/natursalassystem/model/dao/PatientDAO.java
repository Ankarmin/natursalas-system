package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.PatientDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientDAO implements IPatientDAO {
    private static final Logger LOGGER = Logger.getLogger(PatientDAO.class.getName());
    private final Connection connection;

    public PatientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addPatient(PatientDTO newPatient) {
        String query = "INSERT INTO patient (DNI, firstName, lastName, phoneNumber, dateOfEntry, dateOfBirth, district, idLocation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newPatient.getDNI());
            stmt.setString(2, newPatient.getFirstName());
            stmt.setString(3, newPatient.getLastName());
            stmt.setString(4, newPatient.getPhoneNumber());
            stmt.setTimestamp(5, newPatient.getDateOfEntry());
            stmt.setDate(6, newPatient.getDateOfBirth());
            stmt.setString(7, newPatient.getDistrict());
            stmt.setString(8, newPatient.getIdLocation());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding patient", e);
            return false;
        }
    }

    @Override
    public boolean updatePatient(PatientDTO updatedPatient) {
        String query = "UPDATE patient SET firstName = ?, lastName = ?, phoneNumber = ?, dateOfEntry = ?, dateOfBirth = ?, district = ?, idLocation = ? WHERE DNI = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedPatient.getFirstName());
            stmt.setString(2, updatedPatient.getLastName());
            stmt.setString(3, updatedPatient.getPhoneNumber());
            stmt.setTimestamp(4, updatedPatient.getDateOfEntry());
            stmt.setDate(5, updatedPatient.getDateOfBirth());
            stmt.setString(6, updatedPatient.getDistrict());
            stmt.setString(7, updatedPatient.getIdLocation());
            stmt.setString(8, updatedPatient.getDNI());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating patient", e);
            return false;
        }
    }

    @Override
    public boolean deletePatient(String DNI) {
        if (!existsPatient(DNI)) {
            LOGGER.log(Level.WARNING, "Attempted to delete non-existing patient: {0}", DNI);
            return false;
        }
        String query = "DELETE FROM patient WHERE DNI = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, DNI);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting patient", e);
            return false;
        }
    }

    @Override
    public PatientDTO getPatient(String DNI) {
        String query = "SELECT * FROM patient WHERE DNI = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, DNI);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PatientDTO(rs.getString("DNI"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("age"), rs.getString("phoneNumber"), rs.getTimestamp("dateOfEntry"), rs.getDate("dateOfBirth"), rs.getString("district"), rs.getString("idLocation"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving patient", e);
        }
        return null;
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        List<PatientDTO> patients = new ArrayList<>();
        String query = "SELECT * FROM patient";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                patients.add(new PatientDTO(rs.getString("DNI"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("age"), rs.getString("phoneNumber"), rs.getTimestamp("dateOfEntry"), rs.getDate("dateOfBirth"), rs.getString("district"), rs.getString("idLocation")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all patients", e);
        }
        return patients;
    }

    @Override
    public List<PatientDTO> getPatientsByLocation(String idLocation) {
        List<PatientDTO> patients = new ArrayList<>();
        String query = "SELECT * FROM patient WHERE idLocation = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idLocation);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    patients.add(new PatientDTO(
                            rs.getString("DNI"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getInt("age"),
                            rs.getString("phoneNumber"),
                            rs.getTimestamp("dateOfEntry"),
                            rs.getDate("dateOfBirth"),
                            rs.getString("district"),
                            rs.getString("idLocation")
                    ));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving patients by location", e);
        }
        return patients;
    }

    @Override
    public boolean existsPatient(String DNI) {
        String query = "SELECT COUNT(1) FROM patient WHERE DNI = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, DNI);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking if patient exists", e);
            return false;
        }
    }
}
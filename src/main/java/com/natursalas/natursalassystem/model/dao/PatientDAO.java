package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.PatientDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO implements IPatientDAO {
    private Connection connection;

    public PatientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addPatient(PatientDTO newPatient) {
        String query = "INSERT INTO patient (idPatient, firstName, lastName, age, phoneNumber, dateOfConsultation, dateOfBirth, district, idLocation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newPatient.getIdPatient());
            stmt.setString(2, newPatient.getFirstName());
            stmt.setString(3, newPatient.getLastName());
            stmt.setInt(4, newPatient.getAge());
            stmt.setString(5, newPatient.getPhoneNumber());
            stmt.setDate(6, newPatient.getDateOfConsultation());
            stmt.setDate(7, newPatient.getDateOfBirth());
            stmt.setString(8, newPatient.getDistrict());
            stmt.setString(9, newPatient.getIdLocation());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePatient(PatientDTO updatedPatient) {
        String query = "UPDATE patient SET firstName = ?, lastName = ?, age = ?, phoneNumber = ?, dateOfConsultation = ?, dateOfBirth = ?, district = ?, idLocation = ? WHERE idPatient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedPatient.getFirstName());
            stmt.setString(2, updatedPatient.getLastName());
            stmt.setInt(3, updatedPatient.getAge());
            stmt.setString(4, updatedPatient.getPhoneNumber());
            stmt.setDate(5, updatedPatient.getDateOfConsultation());
            stmt.setDate(6, updatedPatient.getDateOfBirth());
            stmt.setString(7, updatedPatient.getDistrict());
            stmt.setString(8, updatedPatient.getIdLocation());
            stmt.setString(9, updatedPatient.getIdPatient());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePatient(Integer patientId) {
        String query = "DELETE FROM patient WHERE idPatient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PatientDTO getPatient(Integer patientId) {
        String query = "SELECT * FROM patient WHERE idPatient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PatientDTO(rs.getString("idPatient"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("age"), rs.getString("phoneNumber"), rs.getDate("dateOfConsultation"), rs.getDate("dateOfBirth"), rs.getString("district"), rs.getString("idLocation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        List<PatientDTO> patients = new ArrayList<>();
        String query = "SELECT * FROM patient";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                patients.add(new PatientDTO(rs.getString("idPatient"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("age"), rs.getString("phoneNumber"), rs.getDate("dateOfConsultation"), rs.getDate("dateOfBirth"), rs.getString("district"), rs.getString("idLocation")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }
}
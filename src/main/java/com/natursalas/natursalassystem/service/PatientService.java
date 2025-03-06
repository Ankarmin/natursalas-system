package com.natursalas.natursalassystem.service;

import com.natursalas.natursalassystem.model.dao.PatientDAO;
import com.natursalas.natursalassystem.model.dto.PatientDTO;

import java.sql.Connection;
import java.util.List;

public class PatientService {
    private final PatientDAO patientDAO;

    public PatientService(Connection connection) {
        this.patientDAO = new PatientDAO(connection);
    }

    public boolean addPatient(PatientDTO newPatient) {
        return patientDAO.addPatient(newPatient);
    }

    public boolean updatePatient(PatientDTO updatedPatient) {
        return patientDAO.updatePatient(updatedPatient);
    }

    public boolean deletePatient(String DNI) {
        return patientDAO.deletePatient(DNI);
    }

    public PatientDTO getPatient(String DNI) {
        return patientDAO.getPatient(DNI);
    }

    public List<PatientDTO> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    public boolean existsPatient(String DNI) {
        return patientDAO.existsPatient(DNI);
    }
}
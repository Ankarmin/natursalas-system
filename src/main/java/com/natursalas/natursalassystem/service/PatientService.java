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

    public PatientDTO getPatient(String DNI) {
        return patientDAO.getPatient(DNI);
    }

    public List<PatientDTO> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    public List<PatientDTO> getPatientsByLocation(String idLocation) {
        return patientDAO.getPatientsByLocation(idLocation);
    }

    public PatientDTO getPatientByDNI(String idPatient) {
        return patientDAO.getPatientByDNI(idPatient);
    }
}
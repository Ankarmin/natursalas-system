package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.PatientDTO;

import java.util.List;

public interface IPatientDAO {

    boolean addPatient(PatientDTO newPatient);

    boolean updatePatient(PatientDTO updatedPatient);

    boolean deletePatient(Integer patientId);

    PatientDTO getPatient(Integer patientId);

    List<PatientDTO> getAllPatients();
}
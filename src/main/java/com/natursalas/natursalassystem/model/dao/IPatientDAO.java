package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.PatientDTO;

import java.util.List;

public interface IPatientDAO {

    boolean addPatient(PatientDTO newPatient);

    boolean updatePatient(PatientDTO updatedPatient);

    boolean deletePatient(String DNI);

    PatientDTO getPatient(String DNI);

    List<PatientDTO> getAllPatients();

    List<PatientDTO> getPatientsByLocation(String idLocation);
}
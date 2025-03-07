package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dto.PatientDTO;
import com.natursalas.natursalassystem.service.DatabaseConnection;
import com.natursalas.natursalassystem.service.PatientService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.ResourceBundle;

public class SedeEditarPacienteController implements Initializable {

    @FXML
    private TextField editarPaciente_apellidos;

    @FXML
    private Button editarPaciente_bttnCancelar;

    @FXML
    private Button editarPaciente_bttnConfirmar;

    @FXML
    private TextField editarPaciente_dni;

    @FXML
    private TextField editarPaciente_edad;

    @FXML
    private DatePicker editarPaciente_fechaNacimiento;

    @FXML
    private TextField editarPaciente_nombre;

    @FXML
    private TextField editarPaciente_telefono;
    private PatientDTO patientDTO;
    private PatientService patientService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBaseDatos();
    }

    public void setPatient(PatientDTO patientDTO) {
        this.patientDTO = patientDTO;
        editarPaciente_dni.setText(patientDTO.getDNI());
        editarPaciente_nombre.setText(patientDTO.getFirstName());
        editarPaciente_apellidos.setText(patientDTO.getLastName());
        Date dateOfBirth = patientDTO.getDateOfBirth();
        editarPaciente_fechaNacimiento.setValue(dateOfBirth.toLocalDate());
        editarPaciente_edad.setText(String.valueOf(patientDTO.getAge()));
        editarPaciente_telefono.setText(patientDTO.getPhoneNumber());
    }

    private void configurarBaseDatos() {
        Connection connection = DatabaseConnection.getConnection();
        patientService = new PatientService(connection);
    }

    @FXML
    private void botonCancelar() {
        Stage currentStage = (Stage) editarPaciente_bttnCancelar.getScene().getWindow();
        currentStage.close();
    }
}

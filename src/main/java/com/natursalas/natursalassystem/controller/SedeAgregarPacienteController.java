package com.natursalas.natursalassystem.controller;

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
import java.util.ResourceBundle;

public class SedeAgregarPacienteController implements Initializable {

    @FXML
    private TextField agregarPaciente_apellidos;

    @FXML
    private Button agregarPaciente_bttnCancelar;

    @FXML
    private Button agregarPaciente_bttnConfirmar;

    @FXML
    private TextField agregarPaciente_dni;

    @FXML
    private TextField agregarPaciente_edad;

    @FXML
    private DatePicker agregarPaciente_fechaNacimiento;

    @FXML
    private TextField agregarPaciente_nombre;

    @FXML
    private TextField agregarPaciente_telefono;
    private String idLocation;
    private PatientService patientService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBaseDatos();
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    private void configurarBaseDatos() {
        Connection connection = DatabaseConnection.getConnection();
        patientService = new PatientService(connection);
    }

    @FXML
    private void botonCancelar() {
        Stage currentStage = (Stage) agregarPaciente_bttnCancelar.getScene().getWindow();
        currentStage.close();
    }
}

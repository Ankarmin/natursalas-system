package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dto.PatientDTO;
import com.natursalas.natursalassystem.service.DatabaseConnection;
import com.natursalas.natursalassystem.service.PatientService;
import com.natursalas.natursalassystem.util.AlertMessages;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SedeAgregarPacienteController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(SedeAgregarPacienteController.class.getName());
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
        boolean confirmar = AlertMessages.mostrarConfirmacion("¿Está seguro que desea cancelar? Los datos ingresados se perderán.");
        if (confirmar) {
            Stage currentStage = (Stage) agregarPaciente_bttnCancelar.getScene().getWindow();
            currentStage.close();
        }
    }

    @FXML
    private void agregarPaciente() {
        try {
            if (agregarPaciente_dni.getText().isEmpty() || agregarPaciente_nombre.getText().isEmpty() || agregarPaciente_apellidos.getText().isEmpty() || agregarPaciente_edad.getText().isEmpty() || agregarPaciente_telefono.getText().isEmpty() || agregarPaciente_fechaNacimiento.getValue() == null) {

                AlertMessages.mostrarAlerta("Todos los campos son obligatorios.", Alert.AlertType.WARNING);
                return;
            }

            String DNI = agregarPaciente_dni.getText().trim();
            String phoneNumber = agregarPaciente_telefono.getText().trim();
            if (!DNI.matches("\\d{8}")) {
                AlertMessages.mostrarAlerta("El DNI debe tener 8 dígitos.", Alert.AlertType.WARNING);
                return;
            }
            if (!phoneNumber.matches("\\d{9}")) {
                AlertMessages.mostrarAlerta("El número de teléfono debe tener 9 dígitos.", Alert.AlertType.WARNING);
                return;
            }

            int age;
            try {
                age = Integer.parseInt(agregarPaciente_edad.getText().trim());
                if (age <= 0) {
                    AlertMessages.mostrarAlerta("La edad debe ser un número positivo.", Alert.AlertType.WARNING);
                    return;
                }
            } catch (NumberFormatException e) {
                AlertMessages.mostrarAlerta("La edad debe ser un número válido y positivo.", Alert.AlertType.WARNING);
                return;
            }

            Date dateOfBirth = Date.valueOf(agregarPaciente_fechaNacimiento.getValue());
            Date currentDate = new Date(System.currentTimeMillis());

            if (!dateOfBirth.before(currentDate)) {
                AlertMessages.mostrarAlerta("La fecha de nacimiento debe ser anterior a la fecha actual.", Alert.AlertType.WARNING);
                return;
            }

            Timestamp dateOfEntry = new Timestamp(System.currentTimeMillis());
            PatientDTO newPatient = new PatientDTO(DNI, agregarPaciente_nombre.getText().trim(), agregarPaciente_apellidos.getText().trim(), age, phoneNumber, dateOfEntry, dateOfBirth, "", this.idLocation);

            if (patientService.addPatient(newPatient)) {
                AlertMessages.mostrarAlerta("Paciente agregado exitosamente.", Alert.AlertType.INFORMATION);

                Stage currentStage = (Stage) agregarPaciente_bttnConfirmar.getScene().getWindow();
                currentStage.close();
            } else {
                AlertMessages.mostrarAlerta("No se pudo agregar el paciente. Verifique los datos.", Alert.AlertType.ERROR);
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al agregar paciente", e);
            AlertMessages.mostrarAlerta("Ocurrió un error inesperado.", Alert.AlertType.ERROR);
        }
    }
}
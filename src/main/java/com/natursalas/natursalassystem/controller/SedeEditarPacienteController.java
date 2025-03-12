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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SedeEditarPacienteController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(SedeEditarPacienteController.class.getName());

    @FXML
    private TextField editarPaciente_apellidos;
    @FXML
    private Button editarPaciente_bttnCancelar;
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
        if (AlertMessages.mostrarConfirmacion("¿Está seguro que desea cancelar? Los cambios no guardados se perderán.")) {
            cerrarVentana();
        }
    }

    @FXML
    private void editarPaciente() {
        try {
            if (editarPaciente_dni.getText().isEmpty() || editarPaciente_nombre.getText().isEmpty() || editarPaciente_apellidos.getText().isEmpty() || editarPaciente_edad.getText().isEmpty() || editarPaciente_telefono.getText().isEmpty() || editarPaciente_fechaNacimiento.getValue() == null) {

                AlertMessages.mostrarAlerta("Todos los campos son obligatorios.", Alert.AlertType.WARNING);
                return;
            }

            String phoneNumber = editarPaciente_telefono.getText().trim();
            if (!phoneNumber.matches("\\d{9}")) {
                AlertMessages.mostrarAlerta("El número de teléfono debe tener 9 dígitos.", Alert.AlertType.WARNING);
                return;
            }

            int age;
            try {
                age = Integer.parseInt(editarPaciente_edad.getText().trim());
                if (age <= 0) {
                    AlertMessages.mostrarAlerta("La edad debe ser un número positivo.", Alert.AlertType.WARNING);
                    return;
                }
            } catch (NumberFormatException e) {
                AlertMessages.mostrarAlerta("La edad debe ser un número válido y positivo.", Alert.AlertType.WARNING);
                return;
            }

            Date dateOfBirth = Date.valueOf(editarPaciente_fechaNacimiento.getValue());
            Date currentDate = new Date(System.currentTimeMillis());
            if (!dateOfBirth.before(currentDate)) {
                AlertMessages.mostrarAlerta("La fecha de nacimiento debe ser anterior a la fecha actual.", Alert.AlertType.WARNING);
                return;
            }

            // Setea los nuevos valores en el DTO en lugar de crear uno nuevo
            patientDTO.setFirstName(editarPaciente_nombre.getText().trim());
            patientDTO.setLastName(editarPaciente_apellidos.getText().trim());
            patientDTO.setAge(age);
            patientDTO.setPhoneNumber(phoneNumber);
            patientDTO.setDateOfBirth(dateOfBirth);
            patientDTO.setDateOfEntry();

            if (patientService.updatePatient(patientDTO)) {
                AlertMessages.mostrarAlerta("Paciente actualizado exitosamente.", Alert.AlertType.INFORMATION);
                cerrarVentana();
            } else {
                AlertMessages.mostrarAlerta("No se pudo actualizar el paciente. Verifique los datos.", Alert.AlertType.ERROR);
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar paciente", e);
            AlertMessages.mostrarAlerta("Ocurrió un error inesperado.", Alert.AlertType.ERROR);
        }
    }

    private void cerrarVentana() {
        Stage currentStage = (Stage) editarPaciente_bttnCancelar.getScene().getWindow();
        currentStage.close();
    }
}
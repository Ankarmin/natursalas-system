package com.natursalas.natursalassystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

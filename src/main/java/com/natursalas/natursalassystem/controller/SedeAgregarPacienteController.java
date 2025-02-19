package com.natursalas.natursalassystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

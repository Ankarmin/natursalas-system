package com.natursalas.natursalassystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SedeAgregarProductoController implements Initializable {

    @FXML
    private Button agregarProducto_bttnCancelar;

    @FXML
    private Button agregarProducto_bttnEditar;

    @FXML
    private ComboBox<?> agregarProducto_comboBoxCategoria;

    @FXML
    private TextField agregarProducto_textFieldCodigo;

    @FXML
    private TextField agregarProducto_textFieldNombre;

    @FXML
    private TextField agregarProducto_textFieldPrecio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

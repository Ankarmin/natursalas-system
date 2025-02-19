package com.natursalas.natursalassystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SedeEditarProductoController implements Initializable {


    @FXML
    private Button editarProducto_bttnCancelar;

    @FXML
    private Button editarProducto_bttnEditar;

    @FXML
    private ComboBox<?> editarProducto_comboBoxCategoria;

    @FXML
    private TextField editarProducto_textFieldCodigo;

    @FXML
    private TextField editarProducto_textFieldNombre;

    @FXML
    private TextField editarProducto_textFieldPrecio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

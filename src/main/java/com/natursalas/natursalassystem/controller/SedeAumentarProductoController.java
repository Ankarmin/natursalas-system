package com.natursalas.natursalassystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SedeAumentarProductoController implements Initializable {

    @FXML
    private Button aumentarProducto_bttnAumentar;

    @FXML
    private Button aumentarProducto_bttnBorrar;

    @FXML
    private Button aumentarProducto_bttnCancelar;

    @FXML
    private Button aumentarProducto_bttnInsertar;

    @FXML
    private TableColumn<?, ?> aumentarProducto_columna_cantidadAumentada;

    @FXML
    private TableColumn<?, ?> aumentarProducto_columna_producto;

    @FXML
    private ComboBox<?> aumentarProducto_comboBoxProducto;

    @FXML
    private TableView<?> aumentarProducto_tableViewProductosAumentados;

    @FXML
    private TextField aumentarProducto_textFieldCantidadAumentada;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

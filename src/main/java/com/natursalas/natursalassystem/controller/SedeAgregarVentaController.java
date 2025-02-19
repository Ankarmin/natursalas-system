package com.natursalas.natursalassystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SedeAgregarVentaController implements Initializable {

    @FXML
    private Button agregarVenta_bttnAgregarVenta;

    @FXML
    private Button agregarVenta_bttnBuscar;

    @FXML
    private Button agregarVenta_bttnCancelar;

    @FXML
    private Button agregarVenta_bttnConfirmarPaciente;

    @FXML
    private Button agregarVenta_bttnConfirmarProducto;

    @FXML
    private TextField agregarVenta_cantidadProducto;

    @FXML
    private TableColumn<?, ?> agregarVenta_columna_cantidad;

    @FXML
    private TableColumn<?, ?> agregarVenta_columna_idProducto;

    @FXML
    private TableColumn<?, ?> agregarVenta_columna_precioTotal;

    @FXML
    private TableColumn<?, ?> agregarVenta_columna_precioUnitario;

    @FXML
    private TableColumn<?, ?> agregarVenta_columna_producto;

    @FXML
    private ComboBox<?> agregarVenta_comboBoxProducto;

    @FXML
    private Label agregarVenta_lblApellidos;

    @FXML
    private Label agregarVenta_lblNombres;

    @FXML
    private Label agregarVenta_lblStockProducto;

    @FXML
    private TableView<?> agregarVenta_tableViewProductosSeleccionados;

    @FXML
    private TextField agregarVenta_txtFieldDniPaciente;

    @FXML
    private TextField agregarVenta_txtFieldPrecioProducto;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

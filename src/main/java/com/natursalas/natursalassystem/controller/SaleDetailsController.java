package com.natursalas.natursalassystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class SaleDetailsController implements Initializable {

    @FXML
    private TableColumn<?, ?> saleDetails_columna_cantidadVendida;

    @FXML
    private TableColumn<?, ?> saleDetails_columna_dniPaciente;

    @FXML
    private TableColumn<?, ?> saleDetails_columna_paciente;

    @FXML
    private TableColumn<?, ?> saleDetails_columna_precioTotal;

    @FXML
    private TableColumn<?, ?> saleDetails_columna_precioUnitario;

    @FXML
    private TableColumn<?, ?> saleDetails_columna_producto;

    @FXML
    private TableColumn<?, ?> saleDetails_columna_sede;

    @FXML
    private TableView<?> saleDetails_tableViewDetalles;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

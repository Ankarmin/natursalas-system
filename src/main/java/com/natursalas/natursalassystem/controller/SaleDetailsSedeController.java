package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dao.ProductDAO;
import com.natursalas.natursalassystem.model.dto.ProductDTO;
import com.natursalas.natursalassystem.model.dto.SaleDetailDTO;
import com.natursalas.natursalassystem.model.dto.SaleDetailSpecialDTO;
import com.natursalas.natursalassystem.service.DatabaseConnection;
import com.natursalas.natursalassystem.service.SaleDetailService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class SaleDetailsSedeController implements Initializable {

    @FXML
    private TableColumn<?, ?> saleDetails_columna_cantidadVendida;

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
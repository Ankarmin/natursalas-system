package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dto.ProductsForLocationDTO;
import com.natursalas.natursalassystem.service.DatabaseConnection;
import com.natursalas.natursalassystem.service.ProductsForLocationService;
import com.natursalas.natursalassystem.service.SaleService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class SedeAgregarVentaController implements Initializable {
    private final ObservableList<String> productsNames = FXCollections.observableArrayList();
    @FXML
    private Button agregarVenta_bttnAgregarVenta;
    @FXML
    private Button agregarVenta_bttnBuscar;
    @FXML
    private Button agregarVenta_bttnCancelar;
    @FXML
    private Button agregarVenta_bttnInsertarSeleccion;
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
    private ComboBox<String> agregarVenta_comboBoxProducto;
    @FXML
    private Label agregarVenta_lblApellidos;
    @FXML
    private Label agregarVenta_lblNombres;
    @FXML
    private Label agregarVenta_lblStockProducto;
    @FXML
    private TableView<?> agregarVenta_tableViewProductosSeleccionados;
    @FXML
    private TextArea agregarVenta_textArea_diagnostico;
    @FXML
    private TextField agregarVenta_txtFieldDniPaciente;
    @FXML
    private TextField agregarVenta_txtFieldPrecioProducto;
    private String idLocation;
    private SaleService saleService;
    private ProductsForLocationService productsForLocationService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBaseDatos();
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
        cargarProductosComboBox(idLocation);
    }

    private void configurarBaseDatos() {
        Connection connection = DatabaseConnection.getConnection();
        saleService = new SaleService(connection);
        productsForLocationService = new ProductsForLocationService(connection);
    }

    @FXML
    private void botonCancelar() {
        Stage currentStage = (Stage) agregarVenta_bttnCancelar.getScene().getWindow();
        currentStage.close();
    }

    private void cargarProductosComboBox(String idLocation) {
        productsNames.clear();
        List<ProductsForLocationDTO> productsForLocation = productsForLocationService.getProductsForLocation(idLocation);
        List<ProductsForLocationDTO> filteredProducts = productsForLocation.stream().filter(product -> product.getStock() > 0).toList();

        for (ProductsForLocationDTO producto : filteredProducts) {
            productsNames.add(producto.getProductName());
        }

        agregarVenta_comboBoxProducto.setItems(productsNames);
    }
}

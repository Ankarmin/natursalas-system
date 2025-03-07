package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dto.ProductDTO;
import com.natursalas.natursalassystem.service.DatabaseConnection;
import com.natursalas.natursalassystem.service.ProductIncreaseService;
import com.natursalas.natursalassystem.service.ProductService;
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

public class SedeAumentarProductoController implements Initializable {

    private final ObservableList<String> productosNombres = FXCollections.observableArrayList();
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
    private ComboBox<String> aumentarProducto_comboBoxProducto;
    @FXML
    private TableView<?> aumentarProducto_tableViewProductosAumentados;
    @FXML
    private TextField aumentarProducto_textFieldCantidadAumentada;
    private String idLocation;
    private ProductService productService;
    private ProductIncreaseService productIncreaseService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBaseDatos();

        cargarProductosComboBox();
    }

    private void configurarBaseDatos() {
        Connection connection = DatabaseConnection.getConnection();
        productIncreaseService = new ProductIncreaseService(connection);
        productService = new ProductService(connection);
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    private void cargarProductosComboBox() {
        productosNombres.clear();
        List<ProductDTO> productos = productService.getAllProducts();

        for (ProductDTO producto : productos) {
            productosNombres.add(producto.getProductName());
        }

        aumentarProducto_comboBoxProducto.setItems(productosNombres);
    }

    @FXML
    private void botonCancelar() {
        Stage currentStage = (Stage) aumentarProducto_bttnCancelar.getScene().getWindow();
        currentStage.close();
    }
}

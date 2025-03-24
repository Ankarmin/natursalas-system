package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dto.ProductDTO;
import com.natursalas.natursalassystem.service.DatabaseConnection;
import com.natursalas.natursalassystem.service.ProductService;
import com.natursalas.natursalassystem.util.AlertMessages;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminAgregarProductoController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(AdminAgregarProductoController.class.getName());

    @FXML
    private Button agregarProducto_bttnCancelar;
    @FXML
    private ComboBox<String> agregarProducto_comboBoxCategoria;
    @FXML
    private TextField agregarProducto_textFieldCodigo;
    @FXML
    private TextField agregarProducto_textFieldNombre;
    @FXML
    private TextField agregarProducto_textFieldPrecio;

    private ProductService productService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBaseDatos();

        cargarCategorias();
    }

    private void configurarBaseDatos() {
        productService = new ProductService(DatabaseConnection.getConnection());
    }

    private void cargarCategorias() {
        agregarProducto_comboBoxCategoria.getItems().addAll("Natursalas", "Farmacia", "Otros");
    }

    @FXML
    private void agregarProducto() {
        try {
            String nombre = agregarProducto_textFieldNombre.getText().trim();
            String codigo = agregarProducto_textFieldCodigo.getText().trim();
            String precioTexto = agregarProducto_textFieldPrecio.getText().trim().replace(",", ".");
            String categoria = agregarProducto_comboBoxCategoria.getValue();

            if (nombre.isEmpty() || codigo.isEmpty() || precioTexto.isEmpty() || categoria == null) {
                AlertMessages.mostrarAlerta("Todos los campos son obligatorios.", Alert.AlertType.WARNING);
                return;
            }

            if (!codigo.matches("^[a-zA-Z0-9]+$")) {
                AlertMessages.mostrarAlerta("El código del producto solo puede contener letras y números.", Alert.AlertType.WARNING);
                return;
            }

            if (!precioTexto.matches("^\\d+(\\.\\d{1,2})?$")) {
                AlertMessages.mostrarAlerta("El precio debe ser un número positivo con máximo 2 decimales.", Alert.AlertType.WARNING);
                return;
            }

            BigDecimal precio;
            try {
                precio = new BigDecimal(precioTexto).setScale(2, RoundingMode.HALF_UP);
                if (precio.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                AlertMessages.mostrarAlerta("El precio debe ser un número positivo con máximo 2 decimales.", Alert.AlertType.WARNING);
                return;
            }

            if (productService.getProduct(codigo) != null) {
                AlertMessages.mostrarAlerta("El código de producto ya existe. Introduzca uno diferente.", Alert.AlertType.ERROR);
                return;
            }

            ProductDTO nuevoProducto = new ProductDTO(codigo, categoria, nombre, precio);
            boolean productoAgregado = productService.addProduct(nuevoProducto);

            if (productoAgregado) {
                AlertMessages.mostrarAlerta("Producto agregado exitosamente.", Alert.AlertType.INFORMATION);
                cerrarVentana();
            } else {
                AlertMessages.mostrarAlerta("No se pudo agregar el producto. Intente nuevamente.", Alert.AlertType.ERROR);
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al agregar producto: {0}", e.getMessage());
            AlertMessages.mostrarAlerta("Ocurrió un error inesperado al agregar el producto.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void botonCancelar() {
        if (AlertMessages.mostrarConfirmacion("¿Desea cancelar el registro del producto?")) {
            cerrarVentana();
        }
    }

    private void cerrarVentana() {
        Stage currentStage = (Stage) agregarProducto_bttnCancelar.getScene().getWindow();
        currentStage.close();
    }
}
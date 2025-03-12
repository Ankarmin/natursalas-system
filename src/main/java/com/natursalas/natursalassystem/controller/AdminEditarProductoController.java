package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dto.ProductDTO;
import com.natursalas.natursalassystem.service.DatabaseConnection;
import com.natursalas.natursalassystem.service.ProductService;
import com.natursalas.natursalassystem.util.AlertMessages;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminEditarProductoController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(AdminEditarProductoController.class.getName());

    @FXML
    private Button editarProducto_bttnCancelar;
    @FXML
    private ComboBox<String> editarProducto_comboBoxCategoria;
    @FXML
    private TextField editarProducto_textFieldCodigo;
    @FXML
    private TextField editarProducto_textFieldNombre;
    @FXML
    private TextField editarProducto_textFieldPrecio;

    private ProductDTO productDTO;
    private ProductService productService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBaseDatos();

        cargarCategorias();
    }

    private void configurarBaseDatos() {
        Connection connection = DatabaseConnection.getConnection();
        productService = new ProductService(connection);
    }

    private void cargarCategorias() {
        editarProducto_comboBoxCategoria.getItems().addAll("Natursalas", "Farmacia", "Otros");
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
        editarProducto_textFieldCodigo.setText(productDTO.getIdProduct());
        editarProducto_textFieldNombre.setText(productDTO.getProductName());
        editarProducto_textFieldPrecio.setText(String.valueOf(productDTO.getPrice()));
        editarProducto_comboBoxCategoria.setValue(productDTO.getCategory());
    }

    @FXML
    private void editarProducto() {
        try {
            String nombre = editarProducto_textFieldNombre.getText().trim();
            String precioTexto = editarProducto_textFieldPrecio.getText().trim();
            String categoria = editarProducto_comboBoxCategoria.getValue();

            if (nombre.isEmpty() || precioTexto.isEmpty() || categoria == null) {
                AlertMessages.mostrarAlerta("Todos los campos son obligatorios.", javafx.scene.control.Alert.AlertType.WARNING);
                return;
            }

            int precio;
            try {
                precio = Integer.parseInt(precioTexto);
                if (precio <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                AlertMessages.mostrarAlerta("El precio debe ser un número positivo.", javafx.scene.control.Alert.AlertType.WARNING);
                return;
            }

            productDTO.setProductName(nombre);
            productDTO.setPrice(precio);
            productDTO.setCategory(categoria);

            boolean productoActualizado = productService.updateProduct(productDTO);

            if (productoActualizado) {
                AlertMessages.mostrarAlerta("Producto actualizado exitosamente.", javafx.scene.control.Alert.AlertType.INFORMATION);
                cerrarVentana();
            } else {
                AlertMessages.mostrarAlerta("No se pudo actualizar el producto. Inténtelo nuevamente.", javafx.scene.control.Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar producto: {0}", e.getMessage());
            AlertMessages.mostrarAlerta("Ocurrió un error inesperado al actualizar el producto.", javafx.scene.control.Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void botonCancelar() {
        if (AlertMessages.mostrarConfirmacion("¿Desea cancelar la edición del producto?")) {
            cerrarVentana();
        }
    }

    private void cerrarVentana() {
        Stage currentStage = (Stage) editarProducto_bttnCancelar.getScene().getWindow();
        currentStage.close();
    }
}
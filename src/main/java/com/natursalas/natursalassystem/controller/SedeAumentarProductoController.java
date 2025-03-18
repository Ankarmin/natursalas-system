package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dto.ProductDTO;
import com.natursalas.natursalassystem.model.dto.ProductsIncreaseDTO;
import com.natursalas.natursalassystem.model.dto.ViewProductoIncreaseDTO;
import com.natursalas.natursalassystem.service.DatabaseConnection;
import com.natursalas.natursalassystem.service.ProductIncreaseService;
import com.natursalas.natursalassystem.service.ProductService;
import com.natursalas.natursalassystem.util.AlertMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class SedeAumentarProductoController implements Initializable {
    private final ObservableList<String> productosNombres = FXCollections.observableArrayList();
    private final ObservableList<ViewProductoIncreaseDTO> aumentosProductos = FXCollections.observableArrayList();

    @FXML
    private Button aumentarProducto_bttnCancelar;
    @FXML
    private TableColumn<ViewProductoIncreaseDTO, Integer> aumentarProducto_columna_cantidadAumentada;
    @FXML
    private TableColumn<ViewProductoIncreaseDTO, String> aumentarProducto_columna_producto;
    @FXML
    private ComboBox<String> aumentarProducto_comboBoxProducto;
    @FXML
    private TableView<ViewProductoIncreaseDTO> aumentarProducto_tableViewProductosAumentados;
    @FXML
    private TextField aumentarProducto_textFieldCantidadAumentada;

    private String idLocation;
    private ProductService productService;
    private ProductIncreaseService productIncreaseService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBaseDatos();
        configurarColumnasAumentos();
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

        productos.sort(Comparator.comparing(ProductDTO::getProductName));

        for (ProductDTO producto : productos) {
            productosNombres.add(producto.getProductName());
        }

        aumentarProducto_comboBoxProducto.setItems(productosNombres);
    }

    private void configurarColumnasAumentos() {
        aumentarProducto_columna_producto.setCellValueFactory(new PropertyValueFactory<>("productName"));
        aumentarProducto_columna_cantidadAumentada.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    @FXML
    private void botonCancelar() {
        if (AlertMessages.mostrarConfirmacion("¿Desea cancelar el aumento de productos?")) {
            cerrarVentana();
        }
    }

    @FXML
    private void insertarAumento() {
        if (aumentarProducto_comboBoxProducto.getValue() == null || aumentarProducto_textFieldCantidadAumentada.getText().isEmpty()) {
            AlertMessages.mostrarAlerta("Seleccione un producto y especifique la cantidad.", Alert.AlertType.WARNING);
            return;
        }

        try {
            int quantity = Integer.parseInt(aumentarProducto_textFieldCantidadAumentada.getText());
            if (quantity <= 0) {
                AlertMessages.mostrarAlerta("La cantidad debe ser mayor a cero.", Alert.AlertType.WARNING);
                return;
            }

            String idProducto = productService.getProductIdByProductName(aumentarProducto_comboBoxProducto.getValue());
            String productName = aumentarProducto_comboBoxProducto.getValue();

            ViewProductoIncreaseDTO viewProductoIncreaseDTO = new ViewProductoIncreaseDTO(idProducto, productName, quantity);
            aumentosProductos.add(viewProductoIncreaseDTO);
            aumentarProducto_tableViewProductosAumentados.setItems(aumentosProductos);
            aumentarProducto_tableViewProductosAumentados.refresh();

            aumentarProducto_comboBoxProducto.setValue(null);
            aumentarProducto_textFieldCantidadAumentada.setText("");

        } catch (NumberFormatException e) {
            AlertMessages.mostrarAlerta("La cantidad debe ser un número válido.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void borrarAumento() {
        ViewProductoIncreaseDTO viewProductoIncreaseDTO = aumentarProducto_tableViewProductosAumentados.getSelectionModel().getSelectedItem();
        if (viewProductoIncreaseDTO != null) {
            aumentosProductos.remove(viewProductoIncreaseDTO);
            aumentarProducto_tableViewProductosAumentados.refresh();
        } else {
            AlertMessages.mostrarAlerta("Seleccione un producto para eliminar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void agregarAumentos() {
        if (aumentosProductos.isEmpty()) {
            AlertMessages.mostrarAlerta("No hay productos para aumentar.", Alert.AlertType.WARNING);
            return;
        }

        boolean allInserted = true;

        for (ViewProductoIncreaseDTO viewProductoIncreaseDTO : aumentosProductos) {
            ProductsIncreaseDTO productsIncreaseDTO = new ProductsIncreaseDTO();
            productsIncreaseDTO.setIdProduct(viewProductoIncreaseDTO.getIdProduct());
            productsIncreaseDTO.setDateOfEntry();
            productsIncreaseDTO.setQuantity(viewProductoIncreaseDTO.getQuantity());
            productsIncreaseDTO.setIdLocation(idLocation);

            boolean inserted = productIncreaseService.addProductsIncrease(productsIncreaseDTO);

            if (!inserted) {
                allInserted = false;
                AlertMessages.mostrarAlerta("Error al aumentar el producto: " + viewProductoIncreaseDTO.getProductName(), Alert.AlertType.ERROR);
            }
        }

        if (allInserted) {
            AlertMessages.mostrarAlerta("Productos aumentados exitosamente.", Alert.AlertType.INFORMATION);
            aumentosProductos.clear();
            aumentarProducto_tableViewProductosAumentados.refresh();
            cerrarVentana();
        } else {
            AlertMessages.mostrarAlerta("Algunos productos no se pudieron aumentar. Verifique los errores.", Alert.AlertType.WARNING);
        }
    }

    private void cerrarVentana() {
        Stage currentStage = (Stage) aumentarProducto_bttnCancelar.getScene().getWindow();
        currentStage.close();
    }
}
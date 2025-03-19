package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dto.*;
import com.natursalas.natursalassystem.service.*;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class SedeAgregarVentaController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(SedeAgregarVentaController.class.getName());

    private final ObservableList<String> productsNames = FXCollections.observableArrayList();
    private final ObservableList<ViewAddSaleDTO> productsSelected = FXCollections.observableArrayList();

    @FXML
    private Button agregarVenta_bttnCancelar;
    @FXML
    private TextField agregarVenta_cantidadProducto;
    @FXML
    private TableColumn<ViewAddSaleDTO, Integer> agregarVenta_columna_cantidad;
    @FXML
    private TableColumn<ViewAddSaleDTO, String> agregarVenta_columna_idProducto;
    @FXML
    private TableColumn<ViewAddSaleDTO, Integer> agregarVenta_columna_precioTotal;
    @FXML
    private TableColumn<ViewAddSaleDTO, Integer> agregarVenta_columna_precioUnitario;
    @FXML
    private TableColumn<ViewAddSaleDTO, String> agregarVenta_columna_producto;
    @FXML
    private ComboBox<String> agregarVenta_comboBoxProducto;
    @FXML
    private Label agregarVenta_lblApellidos;
    @FXML
    private Label agregarVenta_lblNombres;
    @FXML
    private Label agregarVenta_lblStockProducto;
    @FXML
    private TableView<ViewAddSaleDTO> agregarVenta_tableViewProductosSeleccionados;
    @FXML
    private TextArea agregarVenta_textArea_diagnostico;
    @FXML
    private TextField agregarVenta_txtFieldDniPaciente;
    @FXML
    private TextField agregarVenta_txtFieldPrecioProducto;
    @FXML
    private ComboBox<String> combobox_tipoDeVenta;

    private String idLocation;
    private PatientDTO patientDTO;
    private SaleService saleService;
    private SaleDetailService saleDetailService;
    private ProductsForLocationService productsForLocationService;
    private ProductService productService;
    private PatientService patientService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBaseDatos();
        configurarColumnasSaleDetails();
        cargarCategorias();
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
        cargarProductosComboBox(idLocation);
    }

    private void configurarBaseDatos() {
        Connection connection = DatabaseConnection.getConnection();
        saleService = new SaleService(connection);
        productsForLocationService = new ProductsForLocationService(connection);
        patientService = new PatientService(connection);
        productService = new ProductService(connection);
        saleDetailService = new SaleDetailService(connection);
    }

    @FXML
    private void botonCancelar() {
        if (AlertMessages.mostrarConfirmacion("¿Desea cancelar la venta?")) {
            cerrarVentana();
        }
    }

    private void configurarColumnasSaleDetails() {
        agregarVenta_columna_idProducto.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        agregarVenta_columna_producto.setCellValueFactory(new PropertyValueFactory<>("productName"));
        agregarVenta_columna_precioUnitario.setCellValueFactory(new PropertyValueFactory<>("price"));
        agregarVenta_columna_cantidad.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        agregarVenta_columna_precioTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }

    private void cargarProductosComboBox(String idLocation) {
        productsNames.clear();
        List<ProductsForLocationDTO> productsForLocation = productsForLocationService.getProductsForLocation(idLocation);
        List<ProductsForLocationDTO> filteredProducts = new ArrayList<>(productsForLocation.stream().filter(product -> product.getStock() > 0).toList());

        filteredProducts.sort(Comparator.comparing(ProductsForLocationDTO::getProductName));

        for (ProductsForLocationDTO producto : filteredProducts) {
            productsNames.add(producto.getProductName());
        }

        agregarVenta_comboBoxProducto.setItems(productsNames);
    }

    private void cargarCategorias() {
        combobox_tipoDeVenta.getItems().addAll("Venta", "Zero");
    }

    @FXML
    private void buscarPaciente() {
        String dni = agregarVenta_txtFieldDniPaciente.getText().trim();

        if (!dni.matches("\\d{8}")) {
            AlertMessages.mostrarAlerta("Ingrese un DNI válido de 8 dígitos.", Alert.AlertType.WARNING);
            return;
        }

        patientDTO = patientService.getPatient(dni);
        if (patientDTO == null) {
            AlertMessages.mostrarAlerta("Paciente no encontrado.", Alert.AlertType.ERROR);
        } else {
            agregarVenta_lblNombres.setText(patientDTO.getFirstName());
            agregarVenta_lblApellidos.setText(patientDTO.getLastName());
        }
    }

    @FXML
    private void cargarDatosProducto() {
        String productName = agregarVenta_comboBoxProducto.getSelectionModel().getSelectedItem();

        if (productName == null || productName.isEmpty()) {
            return;
        }

        String productId = productService.getProductIdByProductName(productName);
        ProductsForLocationDTO productInfo = productsForLocationService.getProductInLocation(productId, idLocation);

        if (productInfo != null) {
            agregarVenta_txtFieldPrecioProducto.setText(String.valueOf(productInfo.getPrice()));
            agregarVenta_lblStockProducto.setText(String.valueOf(productInfo.getStock()));
        } else {
            AlertMessages.mostrarAlerta("No se pudo cargar la información del producto.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void insertarSeleccion() {
        String productName = agregarVenta_comboBoxProducto.getValue();
        String cantidadTexto = agregarVenta_cantidadProducto.getText().trim();
        String precioTexto = agregarVenta_txtFieldPrecioProducto.getText().trim();

        if (productName == null || cantidadTexto.isEmpty() || precioTexto.isEmpty()) {
            AlertMessages.mostrarAlerta("Debe seleccionar un producto y completar los campos.", Alert.AlertType.WARNING);
            return;
        }

        int quantity, price;
        try {
            quantity = Integer.parseInt(cantidadTexto);
            price = Integer.parseInt(precioTexto);

            if (quantity <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            AlertMessages.mostrarAlerta("Ingrese una cantidad y precio válidos.", Alert.AlertType.WARNING);
            return;
        }

        String productId = productService.getProductIdByProductName(productName);
        int stock = productsForLocationService.getProductInLocation(productId, idLocation).getStock();

        if (quantity > stock) {
            AlertMessages.mostrarAlerta("No hay suficiente stock.", Alert.AlertType.ERROR);
            return;
        }

        for (ViewAddSaleDTO product : productsSelected) {
            if (product.getIdProduct().equals(productId)) {
                AlertMessages.mostrarAlerta("El producto ya está en la lista. Modifique la cantidad si es necesario.", Alert.AlertType.WARNING);
                return;
            }
        }

        productsSelected.add(new ViewAddSaleDTO(productId, productName, price, quantity, price * quantity));
        limpiarDatos();
        agregarVenta_tableViewProductosSeleccionados.setItems(productsSelected);
    }

    @FXML
    private void eliminarSeleccion() {
        ViewAddSaleDTO product = agregarVenta_tableViewProductosSeleccionados.getSelectionModel().getSelectedItem();

        if (product == null) {
            AlertMessages.mostrarAlerta("Seleccione un producto para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        productsSelected.remove(product);
        agregarVenta_tableViewProductosSeleccionados.setItems(productsSelected);
    }

    private void limpiarDatos() {
        agregarVenta_comboBoxProducto.setValue(null);
        agregarVenta_cantidadProducto.clear();
        agregarVenta_txtFieldPrecioProducto.clear();
        agregarVenta_lblStockProducto.setText(null);
    }

    @FXML
    private void agregarVenta() {
        if (patientDTO == null) {
            AlertMessages.mostrarAlerta("Debe buscar un paciente antes de continuar.", Alert.AlertType.WARNING);
            return;
        }

        if (productsSelected.isEmpty()) {
            AlertMessages.mostrarAlerta("Debe agregar al menos un producto.", Alert.AlertType.WARNING);
            return;
        }

        String diagnostico = agregarVenta_textArea_diagnostico.getText().trim();
        if (diagnostico.isEmpty()) {
            AlertMessages.mostrarAlerta("Ingrese un diagnóstico.", Alert.AlertType.WARNING);
            return;
        }

        String tipoVenta = combobox_tipoDeVenta.getValue();
        if (tipoVenta == null) {
            AlertMessages.mostrarAlerta("Seleccione un tipo de venta.", Alert.AlertType.WARNING);
            return;
        }

        try {
            String idSale = java.util.UUID.randomUUID().toString();

            List<SaleDetailDTO> saleDetails = new ArrayList<>();
            int total = 0;

            for (ViewAddSaleDTO product : productsSelected) {
                SaleDetailDTO detail = new SaleDetailDTO(idSale, product.getIdProduct(), idLocation, product.getQuantity(), product.getPrice(), product.getPrice() * product.getQuantity());
                saleDetails.add(detail);
                total += product.getSubtotal();
            }

            boolean detallesInsertados = saleDetailService.addSalesDetails(idSale, saleDetails);

            if (!detallesInsertados) {
                AlertMessages.mostrarAlerta("Error al registrar los detalles de la venta.", Alert.AlertType.ERROR);
                return;
            }

            SaleDTO updatedSale = new SaleDTO();
            updatedSale.setIdSale(idSale);
            updatedSale.setDNI(patientDTO.getDNI());
            updatedSale.setDiagnosis(diagnostico);
            updatedSale.setCategory(tipoVenta);
            updatedSale.setSaleDate();
            updatedSale.setIdLocation(idLocation);
            updatedSale.setSubtotal(total);
            boolean ventaActualizada = saleService.updateSale(updatedSale);

            if (ventaActualizada) {
                AlertMessages.mostrarAlerta("Venta registrada exitosamente.", Alert.AlertType.INFORMATION);
                cerrarVentana();
            } else {
                AlertMessages.mostrarAlerta("Error al actualizar la información de la venta.", Alert.AlertType.ERROR);
            }

        } catch (Exception e) {
            LOGGER.severe("Error al registrar la venta: " + e.getMessage());
            AlertMessages.mostrarAlerta("Ocurrió un error inesperado al procesar la venta.", Alert.AlertType.ERROR);
        }
    }

    private void cerrarVentana() {
        Stage currentStage = (Stage) agregarVenta_bttnCancelar.getScene().getWindow();
        currentStage.close();
    }
}
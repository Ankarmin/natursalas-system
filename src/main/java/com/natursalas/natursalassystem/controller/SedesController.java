package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dto.*;
import com.natursalas.natursalassystem.service.*;
import com.natursalas.natursalassystem.util.AlertMessages;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SedesController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(SedesController.class.getName());

    private final ObservableList<PatientDTO> patientsList = FXCollections.observableArrayList();
    private final ObservableList<String> tipoVentas = FXCollections.observableArrayList();
    private final ObservableList<ViewHistoryDTO> historyList = FXCollections.observableArrayList();
    private final ObservableList<ViewSaleDTO> salesList = FXCollections.observableArrayList();
    private final ObservableList<String> productsNames = FXCollections.observableArrayList();
    private final ObservableList<ViewInventaryDTO> incrementsList = FXCollections.observableArrayList();
    private final ObservableList<ProductsForLocationDTO> productsForLocationList = FXCollections.observableArrayList();
    private final ObservableList<PatientDTO> patientsListInfo = FXCollections.observableArrayList();
    @FXML
    private Button bttnInformacion;
    @FXML
    private Button bttnPacientes;
    @FXML
    private Button bttnInventario;
    @FXML
    private Button bttnVentas;
    @FXML
    private Label informacion_blPacientesNuevos;
    @FXML
    private TableColumn<PatientDTO, String> informacion_columna_apellidosPaciente;
    @FXML
    private TableColumn<PatientDTO, String> informacion_columna_dni;
    @FXML
    private TableColumn<PatientDTO, String> informacion_columna_nombrePaciente;
    @FXML
    private TableColumn<PatientDTO, String> informacion_columna_numeroPaciente;
    @FXML
    private Label informacion_lblPacientesAtendidos;
    @FXML
    private Label informacion_lblProductoMasVendido;
    @FXML
    private Label informacion_lblProductosVendidos;
    @FXML
    private TableView<PatientDTO> informacion_tableViewClientesAtendidos;
    @FXML
    private TableColumn<ProductsForLocationDTO, String> inventarios_columna_cantidad;
    @FXML
    private TableColumn<ProductsForLocationDTO, String> inventarios_columna_categoria;
    @FXML
    private TableColumn<ProductsForLocationDTO, String> inventarios_columna_idProducto;
    @FXML
    private TableColumn<ProductsForLocationDTO, Integer> inventarios_columna_precio;
    @FXML
    private TableColumn<ProductsForLocationDTO, String> inventarios_columna_producto;
    @FXML
    private TableView<ProductsForLocationDTO> inventarios_tableView;
    @FXML
    private TextField inventario_textFieldProductos;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblNombreCuenta;
    @FXML
    private TableColumn<PatientDTO, String> pacientes_columna_apellidos;
    @FXML
    private TableColumn<PatientDTO, String> pacientes_columna_dni;
    @FXML
    private TableColumn<PatientDTO, Integer> pacientes_columna_edad;
    @FXML
    private TableColumn<PatientDTO, Date> pacientes_columna_nacimiento;
    @FXML
    private TableColumn<PatientDTO, String> pacientes_columna_nombres;
    @FXML
    private TableColumn<PatientDTO, String> pacientes_columna_telefono;
    @FXML
    private TableColumn<ViewHistoryDTO, String> pacientes_historial_columna_diagnostico;
    @FXML
    private TableColumn<ViewHistoryDTO, Timestamp> pacientes_historial_columna_fecha;
    @FXML
    private TableColumn<ViewHistoryDTO, Integer> pacientes_historial_columna_precioTotal;
    @FXML
    private Label pacientes_historial_dni;
    @FXML
    private Label pacientes_historial_lastName;
    @FXML
    private Label pacientes_historial_nacimiento;
    @FXML
    private Label pacientes_historial_name;
    @FXML
    private TableView<ViewHistoryDTO> pacientes_historial_tableViewMovimientos;
    @FXML
    private Label pacientes_historial_telefono;
    @FXML
    private TableView<PatientDTO> pacientes_tableViewPacientes;
    @FXML
    private TextField pacientes_txtFieldBuscarDNI;
    @FXML
    private AnchorPane panelInformacion;
    @FXML
    private AnchorPane panelPacientes;
    @FXML
    private AnchorPane panelProductos;
    @FXML
    private AnchorPane panelVentas;
    @FXML
    private TableColumn<ViewInventaryDTO, Integer> productos_columna_cantidadAumentada;
    @FXML
    private TableColumn<ViewInventaryDTO, String> productos_columna_categoria;
    @FXML
    private TableColumn<ViewInventaryDTO, Timestamp> productos_columna_fechaAumento;
    @FXML
    private TableColumn<ViewInventaryDTO, String> productos_columna_idProducto;
    @FXML
    private TableColumn<ViewInventaryDTO, String> productos_columna_producto;
    @FXML
    private TableView<ViewInventaryDTO> productos_tableViewInventario;
    @FXML
    private TableColumn<ViewSaleDTO, Integer> ventas_columna_precioTotal;
    @FXML
    private TableColumn<ViewSaleDTO, String> ventas_columna_producto;
    @FXML
    private TableColumn<ViewSaleDTO, String> ventas_columna_cantidad;
    @FXML
    private TableColumn<ViewSaleDTO, String> ventas_columna_dniPaciente;
    @FXML
    private TableColumn<ViewSaleDTO, String> ventas_columna_tipoVenta;
    @FXML
    private TableColumn<ViewSaleDTO, Timestamp> ventas_columna_fechaVenta;
    @FXML
    private TableColumn<ViewSaleDTO, String> ventas_columna_nombrePaciente;
    @FXML
    private DatePicker ventas_filtrar_desdeDate;
    @FXML
    private DatePicker ventas_filtrar_hastaDate;
    @FXML
    private ComboBox<String> ventas_filtrar_pacientes;
    @FXML
    private ComboBox<String> ventas_filtrar_productos;
    @FXML
    private ComboBox<String> ventas_filtrar_tipoVenta;
    @FXML
    private TableView<ViewSaleDTO> ventas_tableViewVentas;

    private String idLocation;
    private PatientService patientService;
    private SaleDetailService saleDetailService;
    private SaleService saleService;
    private ProductsForLocationService productsForLocationService;
    private ProductIncreaseService productIncreaseService;
    private ProductService productService;
    private SedeInfoService sedeInfoService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBaseDatos();
        iniciarReloj();

        configurarColumnasInformacion();
        configurarColumnasPacientes();
        configurarColumnasHistorial();
        configurarColumnasVentas();
        configurarColumnasProductos();
        configurarColumnasIncrementos();

        agregarEventoDobleClickPacientes();
        agregarEventoDobleClickHistorial();

        cargarProductosComboBox();
        cargarTipoVentasComboBox();

        inicializarEventosBorrarFiltro();
    }

    private void configurarBaseDatos() {
        Connection connection = DatabaseConnection.getConnection();
        patientService = new PatientService(connection);
        saleService = new SaleService(connection);
        saleDetailService = new SaleDetailService(connection);
        productIncreaseService = new ProductIncreaseService(connection);
        productsForLocationService = new ProductsForLocationService(connection);
        productService = new ProductService(connection);
        sedeInfoService = new SedeInfoService(connection);
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
        lblNombreCuenta.setText(idLocation);
        cargarPacientes(idLocation);
        cargarVentas(idLocation);
        cargarPacientesComboBox(idLocation);
        cargarProductos(idLocation);
        cargarIncrementos(idLocation);
        cargarInformacion(idLocation);
    }

    private void iniciarReloj() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> lblFecha.setText(format.format(new Date()))));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void switchForm(ActionEvent event) {
        panelInformacion.setVisible(event.getSource() == bttnInformacion);
        panelPacientes.setVisible(event.getSource() == bttnPacientes);
        panelVentas.setVisible(event.getSource() == bttnVentas);
        panelProductos.setVisible(event.getSource() == bttnInventario);
    }

    @FXML
    private void cerrarSesion() {
        if (AlertMessages.mostrarConfirmacion("¿Está seguro que desea cerrar sesión?")) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/natursalas/natursalassystem/view/fxml/Login.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Inicio de Sesión");
                Stage currentStage = (Stage) lblNombreCuenta.getScene().getWindow();
                currentStage.close();

                stage.setOnCloseRequest(event -> {
                    Platform.exit();
                    System.exit(0);
                });

                stage.show();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error al cerrar sesión y cargar pantalla de login", e);
            }
        }
    }

    private void cargarInformacion(String idLocation) {
        patientsListInfo.clear();
        SedeInfoDTO sedeInfo = sedeInfoService.getSedeInfo(idLocation);
        informacion_lblProductoMasVendido.setText(sedeInfo.getBestSellingProduct());
        informacion_lblProductosVendidos.setText(String.valueOf(sedeInfo.getProductsSoldToday()));
        informacion_lblPacientesAtendidos.setText(String.valueOf(sedeInfo.getPatientsAttendedToday()));
        informacion_blPacientesNuevos.setText(String.valueOf(sedeInfo.getNewPatientsToday()));
        patientsListInfo.addAll(sedeInfo.getPatientsAttendedTodayList());
        informacion_tableViewClientesAtendidos.setItems(patientsListInfo);
        informacion_tableViewClientesAtendidos.refresh();
    }

    private void configurarColumnasPacientes() {
        pacientes_columna_dni.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        pacientes_columna_nombres.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        pacientes_columna_apellidos.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        pacientes_columna_edad.setCellValueFactory(new PropertyValueFactory<>("age"));
        pacientes_columna_telefono.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        pacientes_columna_nacimiento.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
    }

    private void configurarColumnasHistorial() {
        pacientes_historial_columna_fecha.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        pacientes_historial_columna_diagnostico.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        pacientes_historial_columna_precioTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }

    private void configurarColumnasVentas() {
        ventas_columna_fechaVenta.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        ventas_columna_tipoVenta.setCellValueFactory(new PropertyValueFactory<>("category"));
        ventas_columna_dniPaciente.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        ventas_columna_nombrePaciente.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        ventas_columna_producto.setCellValueFactory(new PropertyValueFactory<>("productName"));
        ventas_columna_cantidad.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ventas_columna_precioTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }

    private void configurarColumnasProductos() {
        inventarios_columna_idProducto.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        inventarios_columna_producto.setCellValueFactory(new PropertyValueFactory<>("productName"));
        inventarios_columna_categoria.setCellValueFactory(new PropertyValueFactory<>("category"));
        inventarios_columna_precio.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventarios_columna_cantidad.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    private void configurarColumnasIncrementos() {
        productos_columna_fechaAumento.setCellValueFactory(new PropertyValueFactory<>("dateOfEntry"));
        productos_columna_idProducto.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        productos_columna_producto.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productos_columna_categoria.setCellValueFactory(new PropertyValueFactory<>("category"));
        productos_columna_cantidadAumentada.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void configurarColumnasInformacion() {
        informacion_columna_dni.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        informacion_columna_nombrePaciente.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        informacion_columna_apellidosPaciente.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        informacion_columna_numeroPaciente.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    private void cargarPacientes(String idLocation) {
        patientsList.clear();
        List<PatientDTO> pacientes = patientService.getPatientsByLocation(idLocation);
        if (pacientes != null && !pacientes.isEmpty()) {
            patientsList.addAll(pacientes);
        }
        pacientes_tableViewPacientes.setItems(patientsList);
        pacientes_tableViewPacientes.refresh();
    }

    @FXML
    private void cargarPaciente() {
        String referencia = pacientes_txtFieldBuscarDNI.getText().trim().toLowerCase();

        if (referencia.isEmpty()) {
            pacientes_tableViewPacientes.setItems(patientsList);
            return;
        }

        List<String> palabrasClave = Arrays.asList(referencia.split("\\s+"));

        ObservableList<PatientDTO> filteredList = FXCollections.observableArrayList(patientsList.stream().filter(paciente -> palabrasClave.stream().anyMatch(palabra -> paciente.getDNI().toLowerCase().contains(palabra) || paciente.getFirstName().toLowerCase().contains(palabra) || paciente.getLastName().toLowerCase().contains(palabra))).collect(Collectors.toList()));

        pacientes_tableViewPacientes.setItems(filteredList);
        pacientes_tableViewPacientes.refresh();
    }

    @FXML
    private void agregarPaciente() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/natursalas/natursalassystem/view/fxml/SedeAgregarPaciente.fxml"));
            Parent root = fxmlLoader.load();

            SedeAgregarPacienteController controller = fxmlLoader.getController();
            controller.setIdLocation(idLocation);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Agregar Paciente");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            cargarPacientes(idLocation);
            cargarPacientesComboBox(idLocation);
            cargarInformacion(idLocation);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al abrir la ventana de agregar paciente para la sede: " + idLocation, e);
        }
    }

    @FXML
    private void editarPaciente() {
        PatientDTO paciente = pacientes_tableViewPacientes.getSelectionModel().getSelectedItem();

        if (paciente != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/natursalas/natursalassystem/view/fxml/SedeEditarPaciente.fxml"));
                Parent root = fxmlLoader.load();

                SedeEditarPacienteController controller = fxmlLoader.getController();
                controller.setPatient(paciente);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Editar Paciente");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                cargarPacientes(idLocation);
                cargarPacientesComboBox(idLocation);
                cargarInformacion(idLocation);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error al abrir la ventana de editar paciente para la sede: " + idLocation, e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Editar Paciente");
            alert.setHeaderText("No se ha seleccionado un paciente");
            alert.setContentText("Por favor, seleccione un paciente de la tabla para poder editarlo.");
            alert.showAndWait();
        }
    }

    private void cargarHistorialVentas(String dni) {
        historyList.clear();
        List<SaleDTO> sales = saleService.getSalesByDNI(dni);

        for (SaleDTO sale : sales) {
            historyList.add(new ViewHistoryDTO(sale.getIdSale(), sale.getSaleDate(), sale.getIdLocation(), sale.getDiagnosis(), sale.getSubtotal()));
        }

        pacientes_historial_tableViewMovimientos.setItems(historyList);
        pacientes_historial_tableViewMovimientos.refresh();
    }

    private void cargarVentas(String idLocation) {
        salesList.clear();
        List<SaleDTO> sales = saleService.getSalesByLocation(idLocation);

        for (SaleDTO sale : sales) {
            List<SaleDetailDTO> saleDetails = saleDetailService.getSalesDetailsBySaleId(sale.getIdSale());

            for (SaleDetailDTO saleDetail : saleDetails) {
                salesList.add(new ViewSaleDTO(sale.getIdSale(), sale.getCategory(), sale.getIdLocation(), sale.getSaleDate(), sale.getDNI(), patientService.getPatient(sale.getDNI()).getFullName(), productService.getProduct(saleDetail.getIdProduct()).getProductName(), saleDetail.getQuantity(), saleDetail.getSubtotal()));
            }
        }

        ventas_tableViewVentas.setItems(salesList);
        ventas_tableViewVentas.refresh();
    }

    @FXML
    private void agregarVenta() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/natursalas/natursalassystem/view/fxml/SedeAgregarVenta.fxml"));
            Parent root = fxmlLoader.load();

            SedeAgregarVentaController controller = fxmlLoader.getController();
            controller.setIdLocation(idLocation);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Agregar Venta");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            cargarVentas(idLocation);
            cargarProductos(idLocation);
            cargarInformacion(idLocation);
            cargarTipoVentasComboBox();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al abrir la ventana de agregar venta para la sede: " + idLocation, e);
        }
    }

    @FXML
    private void filtrarVentas() {
        ObservableList<ViewSaleDTO> filteredList = FXCollections.observableArrayList(salesList);

        String product = ventas_filtrar_productos.getSelectionModel().getSelectedItem();
        String fullname = ventas_filtrar_pacientes.getSelectionModel().getSelectedItem();
        Timestamp from = ventas_filtrar_desdeDate.getValue() != null ? Timestamp.valueOf(ventas_filtrar_desdeDate.getValue().atStartOfDay()) : null;
        Timestamp to = ventas_filtrar_hastaDate.getValue() != null ? Timestamp.valueOf(ventas_filtrar_hastaDate.getValue().atStartOfDay().plusDays(1)) : null;
        String saleType = ventas_filtrar_tipoVenta.getSelectionModel().getSelectedItem();

        if (product != null && !product.isEmpty()) {
            filteredList = filteredList.stream().filter(sale -> sale.getProductName().equals(product)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        if (fullname != null && !fullname.isEmpty()) {
            filteredList = filteredList.stream().filter(sale -> sale.getFullName().equals(fullname)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        if (from != null) {
            filteredList = filteredList.stream().filter(sale -> !sale.getSaleDate().before(from)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        if (to != null) {
            filteredList = filteredList.stream().filter(sale -> !sale.getSaleDate().after(to)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        if (saleType != null && !saleType.isEmpty()) {
            filteredList = filteredList.stream().filter(sale -> sale.getCategory().equals(saleType)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        ventas_tableViewVentas.setItems(filteredList);
        ventas_tableViewVentas.refresh();
    }

    private void cargarProductos(String idLocation) {
        productsForLocationList.clear();
        List<ProductsForLocationDTO> productsForLocation = productsForLocationService.getProductsForLocation(idLocation);
        List<ProductsForLocationDTO> filteredProducts = productsForLocation.stream().filter(product -> product.getStock() > 0).toList();
        productsForLocationList.addAll(filteredProducts);
        inventarios_tableView.setItems(productsForLocationList);
        inventarios_tableView.refresh();
    }

    @FXML
    private void buscarInventarioProductos() {
        String referencia = inventario_textFieldProductos.getText().trim().toLowerCase();

        if (referencia.isEmpty()) {
            inventarios_tableView.setItems(productsForLocationList);
            return;
        }

        List<String> palabrasClave = Arrays.asList(referencia.split("\\s+"));

        ObservableList<ProductsForLocationDTO> filteredList = FXCollections.observableArrayList(productsForLocationList.stream().filter(product -> palabrasClave.stream().anyMatch(palabra -> product.getProductName().toLowerCase().contains(palabra) || product.getIdProduct().toLowerCase().contains(palabra)  // También busca en idProduct
        )).collect(Collectors.toList()));

        inventarios_tableView.setItems(filteredList);
        inventarios_tableView.refresh();
    }

    private void cargarIncrementos(String idLocation) {
        incrementsList.clear();
        List<ProductsIncreaseDTO> productsIncrease = productIncreaseService.getProductsIncreaseByLocation(idLocation);

        for (ProductsIncreaseDTO productIncrease : productsIncrease) {
            ProductsForLocationDTO product = productsForLocationService.getProductInLocation(productIncrease.getIdProduct(), productIncrease.getIdLocation());
            if (product != null) {
                incrementsList.add(new ViewInventaryDTO(productIncrease.getDateOfEntry(), productIncrease.getIdLocation(), productIncrease.getIdProduct(), product.getProductName(), product.getCategory(), productIncrease.getQuantity()));
            }
        }
        productos_tableViewInventario.setItems(incrementsList);
        productos_tableViewInventario.refresh();
    }

    @FXML
    private void agregarIncremento() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/natursalas/natursalassystem/view/fxml/SedeAumentarProducto.fxml"));
            Parent root = fxmlLoader.load();

            SedeAumentarProductoController controller = fxmlLoader.getController();
            controller.setIdLocation(idLocation);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Aumentar productos");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            cargarProductos(idLocation);
            cargarIncrementos(idLocation);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al abrir la ventana de aumento de productos para la sede: " + idLocation, e);
        }
    }

    private void agregarEventoDobleClickPacientes() {
        pacientes_tableViewPacientes.setRowFactory(tv -> {
            TableRow<PatientDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    PatientDTO rowData = row.getItem();
                    cargarHistorialVentas(rowData.getDNI());
                    pacientes_historial_name.setText(rowData.getFirstName());
                    pacientes_historial_lastName.setText(rowData.getLastName());
                    pacientes_historial_dni.setText(rowData.getDNI());
                    pacientes_historial_telefono.setText(rowData.getPhoneNumber());
                    pacientes_historial_nacimiento.setText(rowData.getDateOfBirth().toString());
                }
            });
            return row;
        });
    }

    private void agregarEventoDobleClickHistorial() {
        pacientes_historial_tableViewMovimientos.setRowFactory(tv -> {
            TableRow<ViewHistoryDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    abrirVentanaSaleDetail(row.getItem());
                }
            });
            return row;
        });
    }

    private void abrirVentanaSaleDetail(ViewHistoryDTO history) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/natursalas/natursalassystem/view/fxml/SedeSaleDetails.fxml"));
            Parent root = fxmlLoader.load();
            SedeSaleDetailsController controller = fxmlLoader.getController();
            controller.cargarDetallesVenta(history.getIdSale());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Detalles de la Venta");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al abrir ventana de detalles de venta", e);
        }
    }

    private void cargarProductosComboBox() {
        productsNames.clear();
        List<ProductDTO> productos = productService.getAllProducts();

        for (ProductDTO producto : productos) {
            productsNames.add(producto.getProductName());
        }

        ventas_filtrar_productos.setItems(productsNames);
    }

    private void cargarTipoVentasComboBox() {
        tipoVentas.clear();
        List<SaleDTO> ventas = saleService.getAllSales();
        List<String> tipoVentasFiltered = ventas.stream().map(SaleDTO::getCategory).distinct().toList();
        tipoVentas.addAll(tipoVentasFiltered);
        ventas_filtrar_tipoVenta.setItems(tipoVentas);
    }

    private void cargarPacientesComboBox(String idLocation) {
        List<PatientDTO> pacientes = patientService.getPatientsByLocation(idLocation);
        ObservableList<String> pacientesNombres = FXCollections.observableArrayList();

        for (PatientDTO paciente : pacientes) {
            pacientesNombres.add(paciente.getFullName());
        }

        ventas_filtrar_pacientes.setItems(pacientesNombres);
    }

    private void inicializarEventosBorrarFiltro() {
        ventas_filtrar_productos.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                ventas_filtrar_productos.getSelectionModel().clearSelection();
                ventas_filtrar_productos.setValue(null);
                filtrarVentas();
            }
        });

        ventas_filtrar_pacientes.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                ventas_filtrar_pacientes.getSelectionModel().clearSelection();
                ventas_filtrar_pacientes.setValue(null);
                filtrarVentas();
            }
        });

        ventas_filtrar_desdeDate.getEditor().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                ventas_filtrar_desdeDate.setValue(null);
                filtrarVentas();
            }
        });

        ventas_filtrar_hastaDate.getEditor().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                ventas_filtrar_hastaDate.setValue(null);
                filtrarVentas();
            }
        });

        ventas_filtrar_tipoVenta.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                ventas_filtrar_tipoVenta.getSelectionModel().clearSelection();
                ventas_filtrar_tipoVenta.setValue(null);
                filtrarVentas();
            }
        });
    }

    @FXML
    private void refreshDatos() {
        cargarInformacion(idLocation);
        cargarPacientes(idLocation);
        cargarVentas(idLocation);
        cargarIncrementos(idLocation);
        cargarProductos(idLocation);
        cargarPacientesComboBox(idLocation);
        cargarProductosComboBox();
        cargarTipoVentasComboBox();
    }
}

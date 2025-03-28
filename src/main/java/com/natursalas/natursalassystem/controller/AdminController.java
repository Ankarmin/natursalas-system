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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AdminController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(AdminController.class.getName());

    private final ObservableList<PatientDTO> patientsList = FXCollections.observableArrayList();
    private final ObservableList<String> tipoVentas = FXCollections.observableArrayList();
    private final ObservableList<ViewHistoryDTO> historyList = FXCollections.observableArrayList();
    private final ObservableList<ViewSaleDTO> salesList = FXCollections.observableArrayList();
    private final ObservableList<ProductDTO> productsList = FXCollections.observableArrayList();
    private final ObservableList<ViewIncrementsDTO> incrementsList = FXCollections.observableArrayList();
    private final ObservableList<ViewAccountDTO> accountList = FXCollections.observableArrayList();
    private final ObservableList<String> productosNombres = FXCollections.observableArrayList();
    private final ObservableList<String> sedesNombres = FXCollections.observableArrayList();
    private final ObservableList<ViewAdminInfoDTO> adminInfoList = FXCollections.observableArrayList();

    @FXML
    private Button bttnCerrarSesion;
    @FXML
    private Button bttnCrearCuenta;
    @FXML
    private Button bttnIncrementos;
    @FXML
    private Button bttnInformacion;
    @FXML
    private Button bttnPacientes;
    @FXML
    private Button bttnProductos;
    @FXML
    private Button bttnVentas;
    @FXML
    private TableColumn<ViewAccountDTO, String> cuentas_columna_correo;
    @FXML
    private TableColumn<ViewAccountDTO, String> cuentas_columna_sede;
    @FXML
    private TableColumn<ViewAccountDTO, String> cuentas_columna_ubicacion;
    @FXML
    private Button cuentas_crear_bttnCrear;
    @FXML
    private CheckBox cuentas_crear_mostrarContrasena;
    @FXML
    private PasswordField cuentas_crear_pswFieldContrasena;
    @FXML
    private TextField cuentas_crear_txtFieldContrasena;
    @FXML
    private TextField cuentas_crear_txtFieldCorreo;
    @FXML
    private TextField cuentas_crear_txtFieldSede;
    @FXML
    private TextField cuentas_crear_txtFieldUbicacion;
    @FXML
    private Button cuentas_editar_bttnEditar;
    @FXML
    private Button cuentas_editar_bttnEliminar;
    @FXML
    private CheckBox cuentas_editar_mostrarContrasena;
    @FXML
    private PasswordField cuentas_editar_pswFieldContrasena;
    @FXML
    private TextField cuentas_editar_txtFieldContrasena;
    @FXML
    private TextField cuentas_editar_txtFieldCorreo;
    @FXML
    private TextField cuentas_editar_txtFieldSede_;
    @FXML
    private TextField cuentas_editar_txtFieldUbicacion;
    @FXML
    private TableView<ViewAccountDTO> cuentas_tableViewCuentas;
    @FXML
    private ImageView font_refresh;
    @FXML
    private TableColumn<ViewIncrementsDTO, Integer> incrementos_columna_cantidadAumentada;
    @FXML
    private TableColumn<ViewIncrementsDTO, String> incrementos_columna_categoriaProducto;
    @FXML
    private TableColumn<ViewIncrementsDTO, Timestamp> incrementos_columna_fecha;
    @FXML
    private TableColumn<ViewIncrementsDTO, String> incrementos_columna_idProducto;
    @FXML
    private TableColumn<ViewIncrementsDTO, String> incrementos_columna_nombreProducto;
    @FXML
    private TableColumn<ViewIncrementsDTO, String> incrementos_columna_sede;
    @FXML
    private DatePicker incrementos_filtrar_desdeDate;
    @FXML
    private DatePicker incrementos_filtrar_hastaDate;
    @FXML
    private ComboBox<String> incrementos_filtrar_productos;
    @FXML
    private ComboBox<String> incrementos_filtrar_sede;
    @FXML
    private Label incrementos_lblTotalIncrementos;
    @FXML
    private TableView<ViewIncrementsDTO> incrementos_tableView;
    @FXML
    private Label informacion_blPacientesNuevos;
    @FXML
    private TableColumn<ViewAdminInfoDTO, Integer> informacion_columna_pacientesAtendidos;
    @FXML
    private TableColumn<ViewAdminInfoDTO, Integer> informacion_columna_pacientesNuevos;
    @FXML
    private TableColumn<ViewAdminInfoDTO, String> informacion_columna_productoMasVendido;
    @FXML
    private TableColumn<ViewAdminInfoDTO, Integer> informacion_columna_productosVendidos;
    @FXML
    private TableColumn<ViewAdminInfoDTO, String> informacion_columna_sede;
    @FXML
    private Label informacion_lblPacientesAtendidos;
    @FXML
    private Label informacion_lblProductosVendidos;
    @FXML
    private Label informacion_lblSedeMasVentas;
    @FXML
    private TableView<ViewAdminInfoDTO> informacion_tableViewVentasPorSede;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblNombreCuenta;
    @FXML
    private Label lblPath1;
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
    private TableColumn<PatientDTO, String> pacientes_columna_sede;
    @FXML
    private TableColumn<PatientDTO, String> pacientes_columna_telefono;
    @FXML
    private ComboBox<String> pacientes_comboBoxSede;
    @FXML
    private TableColumn<ViewHistoryDTO, String> pacientes_historial_columna_diagnostico;
    @FXML
    private TableColumn<ViewHistoryDTO, Timestamp> pacientes_historial_columna_fecha;
    @FXML
    private TableColumn<ViewHistoryDTO, BigDecimal> pacientes_historial_columna_precioTotal;
    @FXML
    private TableColumn<ViewHistoryDTO, String> pacientes_historial_columna_sede;
    @FXML
    private Label pacientes_historial_dni;
    @FXML
    private Label pacientes_historial_lastName;
    @FXML
    private Label pacientes_historial_nacimiento;
    @FXML
    private Label pacientes_historial_name;
    @FXML
    private TableView<ViewHistoryDTO> pacientes_historial_tableView;
    @FXML
    private Label pacientes_historial_telefono;
    @FXML
    private Label pacientes_lblTotalPacientes;
    @FXML
    private TableView<PatientDTO> pacientes_tableView;
    @FXML
    private TextField pacientes_txtFieldBuscarDNI;
    @FXML
    private AnchorPane panelCrearCuenta;
    @FXML
    private AnchorPane panelIncrementos;
    @FXML
    private AnchorPane panelInformacion;
    @FXML
    private AnchorPane panelPacientes;
    @FXML
    private AnchorPane panelProductos;
    @FXML
    private AnchorPane panelVentas;
    @FXML
    private Button productos_bttnAgregar;
    @FXML
    private Button productos_bttnEditar;
    @FXML
    private TableColumn<ProductDTO, String> productos_columna_categoria;
    @FXML
    private TableColumn<ProductDTO, String> productos_columna_id;
    @FXML
    private TableColumn<ProductDTO, String> productos_columna_nombre;
    @FXML
    private TableColumn<ProductDTO, BigDecimal> productos_columna_precio;
    @FXML
    private Label productos_lblTotalProductos;
    @FXML
    private TableView<ProductDTO> productos_tableView_Productos;
    @FXML
    private TextField productos_textFieldProductos;
    @FXML
    private TableColumn<ViewSaleDTO, BigDecimal> ventas_columna_precioTotal;
    @FXML
    private TableColumn<ViewSaleDTO, Integer> ventas_columna_cantidad;
    @FXML
    private TableColumn<ViewSaleDTO, String> ventas_columna_dniPaciente;
    @FXML
    private TableColumn<ViewSaleDTO, Timestamp> ventas_columna_fechaVenta;
    @FXML
    private TableColumn<ViewSaleDTO, String> ventas_columna_paciente;
    @FXML
    private TableColumn<ViewSaleDTO, String> ventas_columna_producto;
    @FXML
    private TableColumn<ViewSaleDTO, String> ventas_columna_sede;
    @FXML
    private TableColumn<ViewSaleDTO, String> ventas_columna_tipoVenta;
    @FXML
    private DatePicker ventas_filtrar_desdeDate;
    @FXML
    private DatePicker ventas_filtrar_hastaDate;
    @FXML
    private ComboBox<String> ventas_filtrar_productos;
    @FXML
    private ComboBox<String> ventas_filtrar_sede;
    @FXML
    private ComboBox<String> ventas_filtrar_tipoVenta;
    @FXML
    private Label ventas_lblTotalVentas;
    @FXML
    private TableView<ViewSaleDTO> ventas_tableView;

    private PatientService patientService;
    private SaleDetailService saleDetailService;
    private SaleService saleService;
    private ProductsForLocationService productsForLocationService;
    private ProductIncreaseService productIncreaseService;
    private ProductService productService;
    private LocationService locationService;
    private UserService userService;
    private AdminInfoService adminInfoService;

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
        configurarColumnasCuentas();

        cargarInformacion();
        cargarPacientes();
        cargarVentas();
        cargarProductos();
        cargarIncrementos();
        cargarCuentas();

        agregarEventoDobleClickPacientes();
        agregarEventoDobleClickHistorial();
        agregarEventoDobleClickCuentas();

        cargarProductosComboBox();
        cargarTipoVentasComboBox();
        cargarSedesComboBox();

        inicializarEventosBorrarFiltroPacientes();
        inicializarEventosBorrarFiltroVentas();
        inicializarEventosBorrarFiltroIncrementos();
    }

    private void configurarBaseDatos() {
        Connection connection = DatabaseConnection.getConnection();
        patientService = new PatientService(connection);
        saleService = new SaleService(connection);
        saleDetailService = new SaleDetailService(connection);
        productIncreaseService = new ProductIncreaseService(connection);
        productsForLocationService = new ProductsForLocationService(connection);
        locationService = new LocationService(connection);
        userService = new UserService(connection);
        productService = new ProductService(connection);
        adminInfoService = new AdminInfoService(connection);
    }

    @FXML
    private void switchForm(ActionEvent event) {
        panelInformacion.setVisible(event.getSource() == bttnInformacion);
        panelPacientes.setVisible(event.getSource() == bttnPacientes);
        panelVentas.setVisible(event.getSource() == bttnVentas);
        panelCrearCuenta.setVisible(event.getSource() == bttnCrearCuenta);
        panelProductos.setVisible(event.getSource() == bttnProductos);
        panelIncrementos.setVisible(event.getSource() == bttnIncrementos);
    }

    @FXML
    private void cerrarSesion() {
        if (AlertMessages.mostrarConfirmacion("¿Está seguro de que desea cerrar sesión?")) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/natursalas/natursalassystem/view/fxml/Login.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Inicio de Sesión");
                stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/natursalas/natursalassystem/view/assets/logo.png"))));
                Stage currentStage = (Stage) bttnCerrarSesion.getScene().getWindow();
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

    private void iniciarReloj() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> lblFecha.setText(format.format(new Date()))));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void mostrarContrasena() {
        if (cuentas_editar_mostrarContrasena.isSelected()) {
            cuentas_editar_txtFieldContrasena.setText(cuentas_editar_pswFieldContrasena.getText());
            cuentas_editar_txtFieldContrasena.setVisible(true);
            cuentas_editar_pswFieldContrasena.setVisible(false);
        } else {
            cuentas_editar_pswFieldContrasena.setText(cuentas_editar_txtFieldContrasena.getText());
            cuentas_editar_pswFieldContrasena.setVisible(true);
            cuentas_editar_txtFieldContrasena.setVisible(false);
        }

        if (cuentas_crear_mostrarContrasena.isSelected()) {
            cuentas_crear_txtFieldContrasena.setText(cuentas_crear_pswFieldContrasena.getText());
            cuentas_crear_txtFieldContrasena.setVisible(true);
            cuentas_crear_pswFieldContrasena.setVisible(false);
        } else {
            cuentas_crear_pswFieldContrasena.setText(cuentas_crear_txtFieldContrasena.getText());
            cuentas_crear_pswFieldContrasena.setVisible(true);
            cuentas_crear_txtFieldContrasena.setVisible(false);
        }
    }

    private void configurarColumnasInformacion() {
        informacion_columna_sede.setCellValueFactory(new PropertyValueFactory<>("idLocation"));
        informacion_columna_productosVendidos.setCellValueFactory(new PropertyValueFactory<>("productsSoldToday"));
        informacion_columna_pacientesAtendidos.setCellValueFactory(new PropertyValueFactory<>("patientsAttendedToday"));
        informacion_columna_pacientesNuevos.setCellValueFactory(new PropertyValueFactory<>("newPatientsToday"));
        informacion_columna_productoMasVendido.setCellValueFactory(new PropertyValueFactory<>("bestSellingProduct"));
    }

    private void configurarColumnasPacientes() {
        pacientes_columna_sede.setCellValueFactory(new PropertyValueFactory<>("idLocation"));
        pacientes_columna_dni.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        pacientes_columna_apellidos.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        pacientes_columna_nombres.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        pacientes_columna_edad.setCellValueFactory(new PropertyValueFactory<>("age"));
        pacientes_columna_telefono.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        pacientes_columna_nacimiento.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
    }

    private void configurarColumnasHistorial() {
        pacientes_historial_columna_fecha.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        pacientes_historial_columna_diagnostico.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        pacientes_historial_columna_precioTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        pacientes_historial_columna_sede.setCellValueFactory(new PropertyValueFactory<>("idLocation"));
    }

    private void configurarColumnasVentas() {
        ventas_columna_sede.setCellValueFactory(new PropertyValueFactory<>("idLocation"));
        ventas_columna_fechaVenta.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        ventas_columna_tipoVenta.setCellValueFactory(new PropertyValueFactory<>("category"));
        ventas_columna_dniPaciente.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        ventas_columna_paciente.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        ventas_columna_producto.setCellValueFactory(new PropertyValueFactory<>("productName"));
        ventas_columna_cantidad.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ventas_columna_precioTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }

    private void configurarColumnasProductos() {
        productos_columna_id.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        productos_columna_nombre.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productos_columna_categoria.setCellValueFactory(new PropertyValueFactory<>("category"));
        productos_columna_precio.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void configurarColumnasIncrementos() {
        incrementos_columna_fecha.setCellValueFactory(new PropertyValueFactory<>("dateOfEntry"));
        incrementos_columna_sede.setCellValueFactory(new PropertyValueFactory<>("idLocation"));
        incrementos_columna_idProducto.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        incrementos_columna_nombreProducto.setCellValueFactory(new PropertyValueFactory<>("productName"));
        incrementos_columna_categoriaProducto.setCellValueFactory(new PropertyValueFactory<>("category"));
        incrementos_columna_cantidadAumentada.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void configurarColumnasCuentas() {
        cuentas_columna_sede.setCellValueFactory(new PropertyValueFactory<>("idLocation"));
        cuentas_columna_ubicacion.setCellValueFactory(new PropertyValueFactory<>("address"));
        cuentas_columna_correo.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void cargarInformacion() {
        adminInfoList.clear();
        AdminInfoDTO adminInfo = adminInfoService.getAdminInfo();
        informacion_lblSedeMasVentas.setText(adminInfo.getBestSellingLocation());
        informacion_lblProductosVendidos.setText(String.valueOf(adminInfo.getProductsSoldToday()));
        informacion_lblPacientesAtendidos.setText(String.valueOf(adminInfo.getPatientsAttendedToday()));
        informacion_blPacientesNuevos.setText(String.valueOf(adminInfo.getNewPatientsToday()));
        adminInfoList.addAll(adminInfo.getAdminInfoList());
        informacion_tableViewVentasPorSede.setItems(adminInfoList);
        informacion_tableViewVentasPorSede.refresh();
    }

    private void cargarPacientes() {
        patientsList.clear();
        List<PatientDTO> pacientes = patientService.getAllPatients();
        if (pacientes != null && !pacientes.isEmpty()) {
            patientsList.addAll(pacientes);
        }
        pacientes_tableView.setItems(patientsList);
        pacientes_tableView.refresh();

        pacientes_lblTotalPacientes.setText(String.valueOf(patientsList.size()));
    }

    @FXML
    private void cargarPaciente() {
        String referencia = pacientes_txtFieldBuscarDNI.getText().trim().toLowerCase();
        String filtroSeleccionado = pacientes_comboBoxSede.getValue();

        ObservableList<PatientDTO> listaBase = (filtroSeleccionado != null && !filtroSeleccionado.isEmpty()) ? FXCollections.observableArrayList(patientsList.stream().filter(paciente -> paciente.getIdLocation().equalsIgnoreCase(filtroSeleccionado)).collect(Collectors.toList())) : patientsList;

        if (referencia.isEmpty()) {
            pacientes_tableView.setItems(listaBase);
        } else {
            List<String> palabrasClave = Arrays.asList(referencia.split("\\s+"));

            ObservableList<PatientDTO> filteredList = FXCollections.observableArrayList(listaBase.stream().filter(paciente -> palabrasClave.stream().anyMatch(palabra -> paciente.getDNI().toLowerCase().contains(palabra) || paciente.getFirstName().toLowerCase().contains(palabra) || paciente.getLastName().toLowerCase().contains(palabra))).collect(Collectors.toList()));

            pacientes_tableView.setItems(filteredList);
        }

        pacientes_tableView.refresh();
        pacientes_lblTotalPacientes.setText(String.valueOf(pacientes_tableView.getItems().size()));
    }

    @FXML
    private void filtrarPacientes() {
        pacientes_txtFieldBuscarDNI.clear();

        ObservableList<PatientDTO> filteredList = FXCollections.observableArrayList(patientsList);

        String location = pacientes_comboBoxSede.getSelectionModel().getSelectedItem();

        if (location != null && !location.isEmpty()) {
            filteredList = filteredList.stream().filter(patient -> patient.getIdLocation().equals(location)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        pacientes_tableView.setItems(filteredList);
        pacientes_tableView.refresh();

        pacientes_lblTotalPacientes.setText(String.valueOf(filteredList.size()));
    }

    private void cargarHistorialVentas(String dni) {
        historyList.clear();
        List<SaleDTO> sales = saleService.getSalesByDNI(dni);

        for (SaleDTO sale : sales) {
            historyList.add(new ViewHistoryDTO(sale.getIdSale(), sale.getSaleDate(), sale.getIdLocation(), sale.getDiagnosis(), sale.getSubtotal()));
        }

        pacientes_historial_tableView.setItems(historyList);
        pacientes_historial_tableView.refresh();
    }

    private void cargarVentas() {
        salesList.clear();
        List<SaleDTO> sales = saleService.getAllSales();

        for (SaleDTO sale : sales) {
            List<SaleDetailDTO> saleDetails = saleDetailService.getSalesDetailsBySaleId(sale.getIdSale());

            for (SaleDetailDTO saleDetail : saleDetails) {
                salesList.add(new ViewSaleDTO(sale.getIdSale(), sale.getCategory(), sale.getIdLocation(), sale.getSaleDate(), sale.getDNI(), patientService.getPatient(sale.getDNI()).getFullName(), productService.getProduct(saleDetail.getIdProduct()).getProductName(), saleDetail.getQuantity(), saleDetail.getSubtotal()));
            }
        }

        ventas_tableView.setItems(salesList);
        ventas_tableView.refresh();

        ventas_lblTotalVentas.setText(String.valueOf(salesList.size()));
    }

    @FXML
    private void filtrarVentas() {
        ObservableList<ViewSaleDTO> filteredList = FXCollections.observableArrayList(salesList);

        String product = ventas_filtrar_productos.getSelectionModel().getSelectedItem();
        String location = ventas_filtrar_sede.getSelectionModel().getSelectedItem();
        Timestamp from = ventas_filtrar_desdeDate.getValue() != null ? Timestamp.valueOf(ventas_filtrar_desdeDate.getValue().atStartOfDay()) : null;
        Timestamp to = ventas_filtrar_hastaDate.getValue() != null ? Timestamp.valueOf(ventas_filtrar_hastaDate.getValue().atStartOfDay().plusDays(1)) : null;

        if (product != null && !product.isEmpty()) {
            filteredList = filteredList.stream().filter(sale -> sale.getProductName().equals(product)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        if (location != null && !location.isEmpty()) {
            filteredList = filteredList.stream().filter(sale -> sale.getIdLocation().equals(location)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        if (from != null) {
            filteredList = filteredList.stream().filter(sale -> !sale.getSaleDate().before(from)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        if (to != null) {
            filteredList = filteredList.stream().filter(sale -> !sale.getSaleDate().after(to)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        if (ventas_filtrar_tipoVenta.getSelectionModel().getSelectedItem() != null) {
            filteredList = filteredList.stream().filter(sale -> sale.getCategory().equals(ventas_filtrar_tipoVenta.getSelectionModel().getSelectedItem())).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        ventas_tableView.setItems(filteredList);
        ventas_tableView.refresh();

        ventas_lblTotalVentas.setText(String.valueOf(filteredList.size()));
    }

    private void cargarProductos() {
        productsList.clear();
        List<ProductDTO> products = productService.getAllProducts();

        if (products != null && !products.isEmpty()) {
            productsList.addAll(products);
        }

        productos_tableView_Productos.setItems(productsList);
        productos_tableView_Productos.refresh();

        productos_lblTotalProductos.setText(String.valueOf(productsList.size()));
    }

    @FXML
    private void agregarProducto() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/natursalas/natursalassystem/view/fxml/AdminAgregarProducto.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Agregar Producto");
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/natursalas/natursalassystem/view/assets/logo.png"))));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            limpiarFiltrosProductos();
            cargarProductos();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al abrir ventana de agregar producto: {0}", e.getMessage());
            AlertMessages.mostrarAlerta("Ocurrió un error al abrir la ventana para agregar un producto.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void editarProducto() {
        ProductDTO product = productos_tableView_Productos.getSelectionModel().getSelectedItem();

        if (product == null) {
            AlertMessages.mostrarAlerta("Por favor, seleccione un producto de la tabla para poder editarlo.", Alert.AlertType.WARNING);
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/natursalas/natursalassystem/view/fxml/AdminEditarProducto.fxml"));
            Parent root = fxmlLoader.load();

            AdminEditarProductoController controller = fxmlLoader.getController();
            controller.setProductDTO(product);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Producto");
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/natursalas/natursalassystem/view/assets/logo.png"))));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            limpiarFiltrosProductos();
            cargarProductos();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al abrir ventana de editar producto: {0}", e.getMessage());
            AlertMessages.mostrarAlerta("Ocurrió un error al abrir la ventana para editar un producto.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void buscarProductos() {
        String referencia = productos_textFieldProductos.getText().trim().toLowerCase();

        if (referencia.isEmpty()) {
            productos_tableView_Productos.setItems(productsList);
            productos_tableView_Productos.refresh();
            productos_lblTotalProductos.setText(String.valueOf(productsList.size()));
            return;
        }

        List<String> palabrasClave = Arrays.asList(referencia.split("\\s+"));

        ObservableList<ProductDTO> filteredList = FXCollections.observableArrayList(productsList.stream().filter(product -> palabrasClave.stream().anyMatch(palabra -> product.getProductName().toLowerCase().contains(palabra) || product.getIdProduct().toLowerCase().contains(palabra))).collect(Collectors.toList()));

        productos_tableView_Productos.setItems(filteredList);
        productos_lblTotalProductos.setText(String.valueOf(filteredList.size()));
    }

    private void cargarIncrementos() {
        incrementsList.clear();
        List<ProductsIncreaseDTO> productsIncrease = productIncreaseService.getAllProductsIncreases();

        for (ProductsIncreaseDTO productIncrease : productsIncrease) {
            ProductsForLocationDTO productForLocation = productsForLocationService.getProductInLocation(productIncrease.getIdProduct(), productIncrease.getIdLocation());
            ProductDTO product = productService.getProduct(productIncrease.getIdProduct());
            if (product != null) {
                incrementsList.add(new ViewIncrementsDTO(productIncrease.getDateOfEntry(), productIncrease.getIdLocation(), productIncrease.getIdProduct(), product.getProductName(), product.getCategory(), productIncrease.getQuantity()));
            }
        }
        incrementos_tableView.setItems(incrementsList);
        incrementos_tableView.refresh();

        incrementos_lblTotalIncrementos.setText(String.valueOf(incrementsList.size()));
    }

    @FXML
    private void filtrarIncrementos() {
        ObservableList<ViewIncrementsDTO> filteredList = FXCollections.observableArrayList(incrementsList);

        String product = incrementos_filtrar_productos.getSelectionModel().getSelectedItem();
        String location = incrementos_filtrar_sede.getSelectionModel().getSelectedItem();
        Timestamp from = incrementos_filtrar_desdeDate.getValue() != null ? Timestamp.valueOf(incrementos_filtrar_desdeDate.getValue().atStartOfDay()) : null;
        Timestamp to = incrementos_filtrar_hastaDate.getValue() != null ? Timestamp.valueOf(incrementos_filtrar_hastaDate.getValue().atStartOfDay().plusDays(1)) : null;

        if (product != null && !product.isEmpty()) {
            filteredList = filteredList.stream().filter(increments -> increments.getProductName().equals(product)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        if (location != null && !location.isEmpty()) {
            filteredList = filteredList.stream().filter(increments -> increments.getIdLocation().equals(location)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        if (from != null) {
            filteredList = filteredList.stream().filter(increments -> !increments.getDateOfEntry().before(from)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        if (to != null) {
            filteredList = filteredList.stream().filter(increments -> !increments.getDateOfEntry().after(to)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        incrementos_tableView.setItems(filteredList);
        incrementos_tableView.refresh();

        incrementos_lblTotalIncrementos.setText(String.valueOf(filteredList.size()));
    }

    private void cargarCuentas() {
        accountList.clear();
        List<UserDTO> users = userService.getAllUsers();

        for (UserDTO user : users) {
            LocationDTO location = locationService.getLocation(user.getIdLocation());
            if (location != null) {
                accountList.add(new ViewAccountDTO(user.getIdLocation(), location.getAddress(), user.getEmail(), user.getPassword()));
            }
        }
        cuentas_tableViewCuentas.setItems(accountList);
        cuentas_tableViewCuentas.refresh();
    }

    @FXML
    private void crearCuenta() {
        String email = cuentas_crear_txtFieldCorreo.getText();
        String password = cuentas_crear_txtFieldContrasena.getText();
        String location = cuentas_crear_txtFieldSede.getText();
        String address = cuentas_crear_txtFieldUbicacion.getText();

        if (email.isEmpty() || password.isEmpty() || location.isEmpty() || address.isEmpty()) {
            AlertMessages.mostrarAlerta("Todos los campos deben estar rellenados.", Alert.AlertType.WARNING);
            return;
        }

        LocationDTO locationDTO = locationService.getLocation(location);
        if (locationDTO == null) {
            locationDTO = new LocationDTO(location, address);
            if (!locationService.addLocation(locationDTO)) {
                AlertMessages.mostrarAlerta("Error al crear la ubicación.", Alert.AlertType.ERROR);
                return;
            }
        }

        UserDTO userDTO = new UserDTO(email, password, locationDTO.getIdLocation());
        if (userService.addUser(userDTO)) {
            AlertMessages.mostrarAlerta("Cuenta creada correctamente.", Alert.AlertType.INFORMATION);
            cargarCuentas();
        } else {
            AlertMessages.mostrarAlerta("Error al crear la cuenta.", Alert.AlertType.ERROR);
        }

        limpiarCamposCrear();
        cargarCuentas();
    }

    @FXML
    private void editarCuenta() {
        String email = cuentas_editar_txtFieldCorreo.getText().trim();
        String password = cuentas_editar_txtFieldContrasena.getText().trim();
        String address = cuentas_editar_txtFieldUbicacion.getText().trim();

        ViewAccountDTO selectedAccount = cuentas_tableViewCuentas.getSelectionModel().getSelectedItem();

        if (selectedAccount == null) {
            AlertMessages.mostrarAlerta("No se ha seleccionado ninguna cuenta.", Alert.AlertType.WARNING);
            return;
        }

        String oldEmail = selectedAccount.getEmail();
        String idLocation = selectedAccount.getIdLocation();

        if (email.isEmpty() || password.isEmpty() || address.isEmpty()) {
            AlertMessages.mostrarAlerta("Todos los campos deben estar rellenados.", Alert.AlertType.WARNING);
            return;
        }

        UserDTO userDTO = new UserDTO(email, password, idLocation);
        boolean userUpdated = userService.updateUser(userDTO, oldEmail);

        if (!userUpdated) {
            AlertMessages.mostrarAlerta("Error al actualizar la cuenta.", Alert.AlertType.ERROR);
            return;
        }

        LocationDTO locationDTO = new LocationDTO(idLocation, address);
        boolean locationUpdated = locationService.updateLocation(locationDTO);

        if (!locationUpdated) {
            AlertMessages.mostrarAlerta("Error al actualizar la dirección de la sede.", Alert.AlertType.ERROR);
            return;
        }

        AlertMessages.mostrarAlerta("Cuenta actualizada correctamente.", Alert.AlertType.INFORMATION);

        limpiarCamposEditar();
        cargarCuentas();
    }

    @FXML
    private void eliminarCuenta() {
        String email = cuentas_editar_txtFieldCorreo.getText();
        String password = cuentas_editar_txtFieldContrasena.getText();
        String location = cuentas_editar_txtFieldSede_.getText();
        String address = cuentas_editar_txtFieldUbicacion.getText();

        if (email.isEmpty() || password.isEmpty() || location.isEmpty() || address.isEmpty()) {
            AlertMessages.mostrarAlerta("Todos los campos deben estar rellenados.", Alert.AlertType.WARNING);
            return;
        }

        if (!AlertMessages.mostrarConfirmacion("¿Está seguro de que desea eliminar esta cuenta?")) {
            return;
        }

        if (userService.deleteUser(email)) {
            AlertMessages.mostrarAlerta("Cuenta eliminada correctamente.", Alert.AlertType.INFORMATION);
            cargarCuentas();
        } else {
            AlertMessages.mostrarAlerta("Error al eliminar la cuenta.", Alert.AlertType.ERROR);
        }

        limpiarCamposEditar();
        cargarCuentas();
    }

    private void agregarEventoDobleClickPacientes() {
        pacientes_tableView.setRowFactory(tv -> {
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
        pacientes_historial_tableView.setRowFactory(tv -> {
            TableRow<ViewHistoryDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    abrirVentanaSaleDetail(row.getItem());
                }
            });
            return row;
        });
    }

    private void agregarEventoDobleClickCuentas() {
        cuentas_tableViewCuentas.setRowFactory(tv -> {
            TableRow<ViewAccountDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ViewAccountDTO rowData = row.getItem();
                    cuentas_editar_pswFieldContrasena.setText(rowData.getPassword());
                    cuentas_editar_txtFieldContrasena.setText(rowData.getPassword());
                    cuentas_editar_txtFieldCorreo.setText(rowData.getEmail());
                    cuentas_editar_txtFieldSede_.setText(rowData.getIdLocation());
                    cuentas_editar_txtFieldUbicacion.setText(rowData.getAddress());
                }
            });
            return row;
        });
    }

    private void abrirVentanaSaleDetail(ViewHistoryDTO history) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/natursalas/natursalassystem/view/fxml/AdminSaleDetails.fxml"));
            Parent root = fxmlLoader.load();

            AdminSaleDetailsController controller = fxmlLoader.getController();
            controller.cargarDetallesVenta(history.getIdSale());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Detalles de la Venta");
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/natursalas/natursalassystem/view/assets/logo.png"))));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al abrir ventana de detalles de venta", e);
        }
    }

    private void limpiarCamposEditar() {
        cuentas_editar_pswFieldContrasena.clear();
        cuentas_editar_txtFieldCorreo.clear();
        cuentas_editar_txtFieldContrasena.clear();
        cuentas_editar_txtFieldSede_.clear();
        cuentas_editar_txtFieldUbicacion.clear();
    }

    private void limpiarCamposCrear() {
        cuentas_crear_pswFieldContrasena.clear();
        cuentas_crear_txtFieldCorreo.clear();
        cuentas_crear_txtFieldContrasena.clear();
        cuentas_crear_txtFieldSede.clear();
        cuentas_crear_txtFieldUbicacion.clear();
    }

    private void cargarTipoVentasComboBox() {
        tipoVentas.clear();
        List<SaleDTO> ventas = saleService.getAllSales();
        List<String> tipoVentasFiltered = ventas.stream().map(SaleDTO::getCategory).distinct().toList();
        tipoVentas.addAll(tipoVentasFiltered);
        ventas_filtrar_tipoVenta.setItems(tipoVentas);
    }

    private void cargarProductosComboBox() {
        productosNombres.clear();
        List<ProductDTO> productos = productService.getAllProducts();

        productos.sort(Comparator.comparing(ProductDTO::getProductName));

        for (ProductDTO producto : productos) {
            productosNombres.add(producto.getProductName());
        }

        ventas_filtrar_productos.setItems(productosNombres);
        incrementos_filtrar_productos.setItems(productosNombres);
    }

    private void cargarSedesComboBox() {
        sedesNombres.clear();
        List<LocationDTO> sedes = locationService.getAllLocations();

        sedes.sort(Comparator.comparing(LocationDTO::getIdLocation));

        for (LocationDTO sede : sedes) {
            sedesNombres.add(sede.getIdLocation());
        }

        pacientes_comboBoxSede.setItems(sedesNombres);
        ventas_filtrar_sede.setItems(sedesNombres);
        incrementos_filtrar_sede.setItems(sedesNombres);
    }

    private void inicializarEventosBorrarFiltroPacientes() {
        pacientes_comboBoxSede.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                pacientes_comboBoxSede.getSelectionModel().clearSelection();
                pacientes_comboBoxSede.setValue(null);
                filtrarPacientes();
            }
        });
    }

    private void inicializarEventosBorrarFiltroVentas() {
        ventas_filtrar_productos.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                ventas_filtrar_productos.getSelectionModel().clearSelection();
                ventas_filtrar_productos.setValue(null);
                filtrarVentas();
            }
        });

        ventas_filtrar_sede.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                ventas_filtrar_sede.getSelectionModel().clearSelection();
                ventas_filtrar_sede.setValue(null);
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

    private void inicializarEventosBorrarFiltroIncrementos() {
        incrementos_filtrar_productos.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                incrementos_filtrar_productos.getSelectionModel().clearSelection();
                incrementos_filtrar_productos.setValue(null);
                filtrarIncrementos();
            }
        });

        incrementos_filtrar_sede.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                incrementos_filtrar_sede.getSelectionModel().clearSelection();
                incrementos_filtrar_sede.setValue(null);
                filtrarIncrementos();
            }
        });

        incrementos_filtrar_desdeDate.getEditor().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                incrementos_filtrar_desdeDate.setValue(null);
                filtrarIncrementos();
            }
        });

        incrementos_filtrar_hastaDate.getEditor().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                incrementos_filtrar_hastaDate.setValue(null);
                filtrarIncrementos();
            }
        });
    }

    @FXML
    private void refreshDatos() {
        cargarInformacion();
        cargarPacientes();
        cargarVentas();
        cargarProductos();
        cargarIncrementos();
        cargarCuentas();
        cargarTipoVentasComboBox();
        cargarProductosComboBox();
        cargarSedesComboBox();

        limpiarFiltrosPacientes();
        limpiarFiltrosProductos();
        limpiarFiltrosEditarCuenta();
        limpiarFiltrosCrearCuenta();
        limpiarCamposEditar();
        limpiarCamposCrear();
    }

    private void limpiarFiltrosPacientes() {
        pacientes_txtFieldBuscarDNI.clear();
        pacientes_comboBoxSede.getSelectionModel().clearSelection();
        pacientes_comboBoxSede.setValue(null);
    }

    private void limpiarFiltrosProductos() {
        productos_textFieldProductos.clear();
    }

    private void limpiarFiltrosEditarCuenta() {
        cuentas_editar_txtFieldCorreo.clear();
        cuentas_editar_txtFieldContrasena.clear();
        cuentas_editar_txtFieldSede_.clear();
        cuentas_editar_txtFieldUbicacion.clear();
    }

    private void limpiarFiltrosCrearCuenta() {
        cuentas_crear_txtFieldCorreo.clear();
        cuentas_crear_txtFieldContrasena.clear();
        cuentas_crear_txtFieldSede.clear();
        cuentas_crear_txtFieldUbicacion.clear();
    }
}
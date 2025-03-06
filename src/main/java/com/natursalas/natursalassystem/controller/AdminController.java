package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dto.*;
import com.natursalas.natursalassystem.service.*;
import com.natursalas.natursalassystem.util.AlertMessages;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AdminController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(AdminController.class.getName());
    private final AlertMessages alerta = new AlertMessages();
    private final ObservableList<PatientDTO> patientsList = FXCollections.observableArrayList();
    private final ObservableList<HistoryDTO> historyList = FXCollections.observableArrayList();
    private final ObservableList<SaleSpecialDTO> salesList = FXCollections.observableArrayList();
    private final ObservableList<InventaryDTO> inventaryList = FXCollections.observableArrayList();
    private final ObservableList<AccountDTO> accountList = FXCollections.observableArrayList();
    private final ObservableList<String> productosNombres = FXCollections.observableArrayList();
    private final ObservableList<String> sedesNombres = FXCollections.observableArrayList();
    @FXML
    private Button bttnCerrarSesion;
    @FXML
    private Button bttnCrearCuenta;
    @FXML
    private Button bttnInformacion;
    @FXML
    private Button bttnInventarios;
    @FXML
    private Button bttnPacientes;
    @FXML
    private Button bttnVentas;
    @FXML
    private TableColumn<AccountDTO, String> cuentas_columna_correo;
    @FXML
    private TableColumn<AccountDTO, String> cuentas_columna_sede;
    @FXML
    private TableColumn<AccountDTO, String> cuentas_columna_ubicacion;
    @FXML
    private TableView<AccountDTO> cuentas_tableViewCuentas;
    @FXML
    private Label informacion_blPacientesNuevos;
    @FXML
    private TableColumn<?, ?> informacion_columna_pacientesAtendidos;
    @FXML
    private TableColumn<?, ?> informacion_columna_pacientesNuevos;
    @FXML
    private TableColumn<?, ?> informacion_columna_productoMasVendido;
    @FXML
    private TableColumn<?, ?> informacion_columna_productosVendidos;
    @FXML
    private TableColumn<?, ?> informacion_columna_sede;
    @FXML
    private Label informacion_lblPacientesAtendidos;
    @FXML
    private Label informacion_lblProductosVendidos;
    @FXML
    private Label informacion_lblSedeMasVentas;
    @FXML
    private TableView<?> informacion_tableViewVentasPorSede;
    @FXML
    private TableColumn<InventaryDTO, Integer> inventarios_columna_cantidadAumentada;
    @FXML
    private TableColumn<InventaryDTO, String> inventarios_columna_categoriaProducto;
    @FXML
    private TableColumn<InventaryDTO, Timestamp> inventarios_columna_fecha;
    @FXML
    private TableColumn<InventaryDTO, String> inventarios_columna_idProducto;
    @FXML
    private TableColumn<InventaryDTO, String> inventarios_columna_nombreProducto;
    @FXML
    private TableColumn<InventaryDTO, String> inventarios_columna_sede;
    @FXML
    private TableView<InventaryDTO> inventarios_tableView;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblNombreCuenta;
    @FXML
    private Label lblPath;
    @FXML
    private Label lblPath1;
    @FXML
    private TableColumn<PatientDTO, String> pacientes_columna_apellidos;
    @FXML
    private TableColumn<PatientDTO, String> pacientes_columna_dni;
    @FXML
    private TableColumn<PatientDTO, String> pacientes_columna_edad;
    @FXML
    private TableColumn<PatientDTO, String> pacientes_columna_nacimiento;
    @FXML
    private TableColumn<PatientDTO, String> pacientes_columna_nombres;
    @FXML
    private TableColumn<PatientDTO, String> pacientes_columna_sede;
    @FXML
    private TableColumn<PatientDTO, String> pacientes_columna_telefono;
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
    private TableColumn<HistoryDTO, String> pacientes_historial_columna_diagnostico;
    @FXML
    private TableColumn<HistoryDTO, Timestamp> pacientes_historial_columna_fecha;
    @FXML
    private TableColumn<HistoryDTO, Integer> pacientes_historial_columna_precioTotal;
    @FXML
    private TableColumn<HistoryDTO, String> pacientes_historial_columna_sede;
    @FXML
    private Label pacientes_historial_dni;
    @FXML
    private Label pacientes_historial_lastName;
    @FXML
    private Label pacientes_historial_nacimiento;
    @FXML
    private Label pacientes_historial_name;
    @FXML
    private TableView<HistoryDTO> pacientes_historial_tableViewMovimientos;
    @FXML
    private Label pacientes_historial_telefono;
    @FXML
    private TableView<PatientDTO> pacientes_tableViewPacientes;
    @FXML
    private TextField pacientes_txtFieldBuscarDNI;
    @FXML
    private AnchorPane panelCrearCuenta;
    @FXML
    private AnchorPane panelInformacion;
    @FXML
    private AnchorPane panelPacientes;
    @FXML
    private AnchorPane panelVentas;
    @FXML
    private AnchorPane pnlInventarios;
    @FXML
    private TableColumn<SaleSpecialDTO, Integer> ventas_columna_PrecioTotal;
    @FXML
    private TableColumn<SaleSpecialDTO, String> ventas_columna_producto;
    @FXML
    private TableColumn<SaleSpecialDTO, String> ventas_columna_dniPaciente;
    @FXML
    private TableColumn<SaleSpecialDTO, Timestamp> ventas_columna_fechaVenta;
    @FXML
    private TableColumn<SaleSpecialDTO, String> ventas_columna_paciente;
    @FXML
    private TableColumn<SaleSpecialDTO, String> ventas_columna_sede;
    @FXML
    private DatePicker ventas_filtrar_desdeDate;
    @FXML
    private DatePicker ventas_filtrar_hastaDate;
    @FXML
    private ComboBox<?> ventas_filtrar_pacientes;
    @FXML
    private ComboBox<String> ventas_filtrar_productos;
    @FXML
    private ComboBox<String> ventas_filtrar_sede;
    @FXML
    private TableView<SaleSpecialDTO> ventas_tableViewVentas;
    private PatientService patientService;
    private SaleDetailService saleDetailService;
    private SaleService saleService;
    private ProductsForLocationService productsForLocationService;
    private ProductIncreaseService productIncreaseService;
    private ProductService productService;
    private LocationService locationService;
    private UserService userService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBaseDatos();
        iniciarReloj();

        configurarColumnasPacientes();
        configurarColumnasHistorial();
        configurarColumnasVentas();
        configurarColumnasInventario();
        configurarColumnasCuentas();

        cargarPacientes();
        cargarVentas();
        cargarInventario();
        cargarCuentas();

        agregarEventoDobleClickPacientes();
        agregarEventoDobleClickHistorial();
        agregarEventoDobleClickCuentas();

        cargarProductosComboBox();
        cargarSedesComboBox();

        inicializarEventosBorrarFiltro();
    }

    @FXML
    private void switchForm(ActionEvent event) {
        panelInformacion.setVisible(event.getSource() == bttnInformacion);
        panelPacientes.setVisible(event.getSource() == bttnPacientes);
        panelVentas.setVisible(event.getSource() == bttnVentas);
        panelCrearCuenta.setVisible(event.getSource() == bttnCrearCuenta);
        pnlInventarios.setVisible(event.getSource() == bttnInventarios);
    }

    private void iniciarReloj() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    Platform.runLater(() -> lblFecha.setText(format.format(new Date())));
                } catch (InterruptedException e) {
                    LOGGER.log(Level.SEVERE, "Error en el reloj", e);
                }
            }
        }).start();
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

    private void configurarColumnasPacientes() {
        pacientes_columna_sede.setCellValueFactory(new PropertyValueFactory<>("idLocation"));
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
        pacientes_historial_columna_sede.setCellValueFactory(new PropertyValueFactory<>("idLocation"));
    }

    private void configurarColumnasVentas() {
        ventas_columna_sede.setCellValueFactory(new PropertyValueFactory<>("idLocation"));
        ventas_columna_fechaVenta.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        ventas_columna_dniPaciente.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        ventas_columna_paciente.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        ventas_columna_producto.setCellValueFactory(new PropertyValueFactory<>("productName"));
        ventas_columna_PrecioTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }

    private void configurarColumnasInventario() {
        inventarios_columna_fecha.setCellValueFactory(new PropertyValueFactory<>("dateOfEntry"));
        inventarios_columna_sede.setCellValueFactory(new PropertyValueFactory<>("idLocation"));
        inventarios_columna_idProducto.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        inventarios_columna_nombreProducto.setCellValueFactory(new PropertyValueFactory<>("productName"));
        inventarios_columna_categoriaProducto.setCellValueFactory(new PropertyValueFactory<>("category"));
        inventarios_columna_cantidadAumentada.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void configurarColumnasCuentas() {
        cuentas_columna_sede.setCellValueFactory(new PropertyValueFactory<>("idLocation"));
        cuentas_columna_ubicacion.setCellValueFactory(new PropertyValueFactory<>("address"));
        cuentas_columna_correo.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void cargarHistorialVentas(String dni) {
        historyList.clear();
        List<SaleDTO> sales = saleService.getSalesByDNI(dni);

        for (SaleDTO sale : sales) {
            historyList.add(new HistoryDTO(sale.getIdSale(), sale.getSaleDate(), sale.getIdLocation(), sale.getDiagnosis(), sale.getSubtotal()));
        }

        pacientes_historial_tableViewMovimientos.setItems(historyList);
        pacientes_historial_tableViewMovimientos.refresh();
    }

    private void cargarPacientes() {
        patientsList.clear();
        List<PatientDTO> pacientes = patientService.getAllPatients();
        if (pacientes != null && !pacientes.isEmpty()) {
            patientsList.addAll(pacientes);
        }
        pacientes_tableViewPacientes.setItems(patientsList);
        pacientes_tableViewPacientes.refresh();
    }

    @FXML
    private void cargarPaciente() {
        String dni = pacientes_txtFieldBuscarDNI.getText().trim();

        if (dni.isEmpty()) {
            pacientes_tableViewPacientes.setItems(patientsList);
        } else {
            ObservableList<PatientDTO> filteredList = FXCollections.observableArrayList(patientsList.stream().filter(paciente -> paciente.getDNI().startsWith(dni)).collect(Collectors.toList()));
            pacientes_tableViewPacientes.setItems(filteredList);
        }

        pacientes_tableViewPacientes.refresh();
    }

    private void cargarVentas() {
        salesList.clear();
        List<SaleDTO> sales = saleService.getAllSales();

        for (SaleDTO sale : sales) {
            List<SaleDetailDTO> saleDetails = saleDetailService.getSalesDetailsBySaleId(sale.getIdSale());

            for (SaleDetailDTO saleDetail : saleDetails) {
                SaleSpecialDTO saleSpecial = new SaleSpecialDTO(sale.getIdSale(), sale.getIdLocation(), sale.getSaleDate(), sale.getDNI(), patientService.getPatient(sale.getDNI()).getFullName(), productService.getProduct(saleDetail.getIdProduct()).getProductName(), saleDetail.getSubtotal());
                salesList.add(saleSpecial);
            }
        }

        ventas_tableViewVentas.setItems(salesList);
        ventas_tableViewVentas.refresh();
    }

    @FXML
    private void filtrarVentas() {
        ObservableList<SaleSpecialDTO> filteredList = FXCollections.observableArrayList(salesList);

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

        ventas_tableViewVentas.setItems(filteredList);
        ventas_tableViewVentas.refresh();
    }

    private void cargarInventario() {
        inventaryList.clear();
        List<ProductsIncreaseDTO> productsIncrease = productIncreaseService.getAllProductsIncreases();

        for (ProductsIncreaseDTO productIncrease : productsIncrease) {
            ProductsForLocationDTO product = productsForLocationService.getProductInLocation(productIncrease.getIdProduct(), productIncrease.getIdLocation());
            if (product != null) {
                inventaryList.add(new InventaryDTO(productIncrease.getDateOfEntry(), productIncrease.getIdLocation(), productIncrease.getIdProduct(), product.getProductName(), product.getCategory(), productIncrease.getQuantity()));
            }
        }
        inventarios_tableView.setItems(inventaryList);
        inventarios_tableView.refresh();
    }

    private void cargarCuentas() {
        accountList.clear();
        List<UserDTO> users = userService.getAllUsers();

        for (UserDTO user : users) {
            LocationDTO location = locationService.getLocation(user.getIdLocation());
            if (location != null) {
                accountList.add(new AccountDTO(user.getIdLocation(), location.getAddress(), user.getEmail(), user.getPassword()));
            }
        }
        cuentas_tableViewCuentas.setItems(accountList);
        cuentas_tableViewCuentas.refresh();
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
            TableRow<HistoryDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    HistoryDTO rowData = row.getItem();
                    abrirVentanaSaleDetail(rowData);
                }
            });
            return row;
        });
    }

    private void agregarEventoDobleClickCuentas() {
        cuentas_tableViewCuentas.setRowFactory(tv -> {
            TableRow<AccountDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    AccountDTO rowData = row.getItem();
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

    private void abrirVentanaSaleDetail(HistoryDTO history) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/natursalas/natursalassystem/view/fxml/SaleDetailsAdmin.fxml"));
            Parent root = fxmlLoader.load();

            SaleDetailsController controller = fxmlLoader.getController();
            controller.cargarDetallesVenta(history.getIdSale());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Detalles de la Venta");

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void crearCuenta() {
        String email = cuentas_crear_txtFieldCorreo.getText();
        String password = cuentas_crear_txtFieldContrasena.getText();
        String location = cuentas_crear_txtFieldSede.getText();
        String address = cuentas_crear_txtFieldUbicacion.getText();

        if (email.isEmpty() || password.isEmpty() || location.isEmpty() || address.isEmpty()) {
            alerta.mensajeError("Todos los campos deben estar rellenados.");
            return;
        }

        LocationDTO locationDTO = locationService.getLocation(location);
        if (locationDTO == null) {
            locationDTO = new LocationDTO(location, address);
            if (!locationService.addLocation(locationDTO)) {
                alerta.mensajeError("Error al crear la ubicación.");
                return;
            }
        }

        UserDTO userDTO = new UserDTO(email, password, locationDTO.getIdLocation());
        if (userService.addUser(userDTO)) {
            alerta.mensajeConfirmacion("Cuenta creada correctamente.");
            cargarCuentas();
        } else {
            alerta.mensajeError("Error al crear la cuenta.");
        }

        limpiarCamposCrear();
        cargarCuentas();
    }

    @FXML
    private void editarCuenta() {
        String email = cuentas_editar_txtFieldCorreo.getText().trim();
        String password = cuentas_editar_txtFieldContrasena.getText().trim();
        String address = cuentas_editar_txtFieldUbicacion.getText().trim();

        AccountDTO selectedAccount = cuentas_tableViewCuentas.getSelectionModel().getSelectedItem();

        if (selectedAccount == null) {
            alerta.mensajeError("No se ha seleccionado ninguna cuenta.");
            return;
        }

        String oldEmail = selectedAccount.getEmail();
        String idLocation = selectedAccount.getIdLocation();

        if (email.isEmpty() || password.isEmpty() || address.isEmpty()) {
            alerta.mensajeError("Todos los campos deben estar rellenados.");
            return;
        }

        UserDTO userDTO = new UserDTO(email, password, idLocation);
        boolean userUpdated = userService.updateUser(userDTO, oldEmail);

        if (!userUpdated) {
            alerta.mensajeError("Error al actualizar la cuenta.");
            return;
        }

        LocationDTO locationDTO = new LocationDTO(idLocation, address);
        boolean locationUpdated = locationService.updateLocation(locationDTO);

        if (!locationUpdated) {
            alerta.mensajeError("Error al actualizar la dirección de la sede.");
            return;
        }

        alerta.mensajeConfirmacion("Cuenta actualizada correctamente.");
        cargarCuentas();
        limpiarCamposEditar();
    }

    @FXML
    private void eliminarCuenta() {
        String email = cuentas_editar_txtFieldCorreo.getText();
        String password = cuentas_editar_txtFieldContrasena.getText();
        String location = cuentas_editar_txtFieldSede_.getText();
        String address = cuentas_editar_txtFieldUbicacion.getText();

        if (email.isEmpty() || password.isEmpty() || location.isEmpty() || address.isEmpty()) {
            alerta.mensajeError("Todos los campos deben estar rellenados.");
            return;
        }

        if (userService.deleteUser(email)) {
            alerta.mensajeConfirmacion("Cuenta eliminada correctamente.");
            cargarCuentas();
        } else {
            alerta.mensajeError("Error al eliminar la cuenta.");
        }

        limpiarCamposEditar();
        cargarCuentas();
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

    @FXML
    private void cerrarSesion() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/natursalas/natursalassystem/view/fxml/Login.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Inicio de Sesión");
            Stage currentStage = (Stage) bttnCerrarSesion.getScene().getWindow();
            currentStage.close();

            stage.setOnCloseRequest(event -> {
                Platform.exit();
                System.exit(0);
            });

            stage.show();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al cerrar sesión", e);
        }
    }

    private void cargarProductosComboBox() {
        productosNombres.clear();
        List<ProductDTO> productos = productService.getAllProducts();

        for (ProductDTO producto : productos) {
            productosNombres.add(producto.getProductName());
        }

        ventas_filtrar_productos.setItems(productosNombres);
    }

    private void cargarSedesComboBox() {
        sedesNombres.clear();
        List<LocationDTO> sedes = locationService.getAllLocations();

        for (LocationDTO sede : sedes) {
            sedesNombres.add(sede.getIdLocation());
        }

        ventas_filtrar_sede.setItems(sedesNombres);
    }

    private void inicializarEventosBorrarFiltro() {
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
    }
}
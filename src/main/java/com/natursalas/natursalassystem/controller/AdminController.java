package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dao.PatientDAO;
import com.natursalas.natursalassystem.model.dao.SaleDAO;
import com.natursalas.natursalassystem.model.dao.SalesDetailDAO;
import com.natursalas.natursalassystem.model.dto.PatientDTO;
import com.natursalas.natursalassystem.model.dto.SaleDTO;
import com.natursalas.natursalassystem.model.dto.SaleDetailView;
import com.natursalas.natursalassystem.model.dto.SalesDetailDTO;
import com.natursalas.natursalassystem.service.DatabaseConnection;
import com.natursalas.natursalassystem.util.AlertMessages;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

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
    private TableColumn<?, ?> cuentas_columna_correo;

    @FXML
    private TableColumn<?, ?> cuentas_columna_sede;

    @FXML
    private TableColumn<?, ?> cuentas_columna_ubicacion;

    @FXML
    private TableView<?> cuentas_tableViewCuentas;

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
    private TableColumn<?, ?> inventarios_columna_cantidadAumentada;

    @FXML
    private TableColumn<?, ?> inventarios_columna_categoriaProducto;

    @FXML
    private TableColumn<?, ?> inventarios_columna_fecha;

    @FXML
    private TableColumn<?, ?> inventarios_columna_idProducto;

    @FXML
    private TableColumn<?, ?> inventarios_columna_nombreProducto;

    @FXML
    private TableColumn<?, ?> inventarios_columna_sede;

    @FXML
    private TableView<?> inventarios_tableView;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblNombreCuenta;

    @FXML
    private Label lblPath;

    @FXML
    private Label lblPath1;

    @FXML
    private Button pacientes_bttnBuscar;

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
    private Button pacientes_crear_bttnCrear;

    @FXML
    private CheckBox pacientes_crear_mostrarContrasena;

    @FXML
    private PasswordField pacientes_crear_pswFieldContrasena;

    @FXML
    private TextField pacientes_crear_txtFieldContrasena;

    @FXML
    private TextField pacientes_crear_txtFieldCorreo;

    @FXML
    private TextField pacientes_crear_txtFieldSede;

    @FXML
    private TextField pacientes_crear_txtFieldUbicacion;

    @FXML
    private Button pacientes_editar_bttnEditar;

    @FXML
    private Button pacientes_editar_bttnEliminar;

    @FXML
    private CheckBox pacientes_editar_mostrarContrasena;

    @FXML
    private PasswordField pacientes_editar_pswFieldContrasena;

    @FXML
    private TextField pacientes_editar_txtFieldContraseña;

    @FXML
    private TextField pacientes_editar_txtFieldCorreo;

    @FXML
    private TextField pacientes_editar_txtFieldSede_;

    @FXML
    private TextField pacientes_editar_txtFieldUbicacion;

    @FXML
    private TableColumn<SaleDetailView, String> pacientes_historial_columna_diagnostico;

    @FXML
    private TableColumn<SaleDetailView, Timestamp> pacientes_historial_columna_fecha;

    @FXML
    private TableColumn<SaleDetailView, Integer> pacientes_historial_columna_precioTotal;

    @FXML
    private TableColumn<SaleDetailView, String> pacientes_historial_columna_sede;

    @FXML
    private Label pacientes_historial_dni;

    @FXML
    private Label pacientes_historial_lastName;

    @FXML
    private Label pacientes_historial_nacimiento;

    @FXML
    private Label pacientes_historial_name;

    @FXML
    private TableView<SaleDetailView> pacientes_historial_tableViewMovimientos;

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
    private Button ventas_bttnFiltrar;

    @FXML
    private TableColumn<?, ?> ventas_columna_PrecioTotal;

    @FXML
    private TableColumn<?, ?> ventas_columna_diagnostico;

    @FXML
    private TableColumn<?, ?> ventas_columna_dniPaciente;

    @FXML
    private TableColumn<?, ?> ventas_columna_fechaVenta;

    @FXML
    private TableColumn<?, ?> ventas_columna_paciente;

    @FXML
    private TableColumn<?, ?> ventas_columna_sede;

    @FXML
    private DatePicker ventas_filtrar_desdeDate;

    @FXML
    private DatePicker ventas_filtrar_hastaDate;

    @FXML
    private ComboBox<?> ventas_filtrar_pacientes;

    @FXML
    private ComboBox<?> ventas_filtrar_productos;

    @FXML
    private ComboBox<?> ventas_filtrar_sede;

    @FXML
    private TableView<SaleDetailView> ventas_tableViewVentas;

    private final AlertMessages alerta = new AlertMessages();
    private PatientDAO patientDAO;
    private SaleDAO saleDAO;
    private SalesDetailDAO salesDetailDAO;
    private final ObservableList<PatientDTO> pacientesList = FXCollections.observableArrayList();
    private final ObservableList<SaleDetailView> saleDetailViewsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBaseDatos();
        configurarColumnasPacientes();
        configurarColumnasHistorial();
        cargarPacientes();
        iniciarReloj();
        agregarEventoDobleClickPacientes();
    }

    public void switchForm(ActionEvent event) {
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
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @FXML
    public void mostrarContrasena() {
        if (pacientes_editar_mostrarContrasena.isSelected()) {
            pacientes_editar_txtFieldContraseña.setText(pacientes_editar_pswFieldContrasena.getText());
            pacientes_editar_txtFieldContraseña.setVisible(true);
            pacientes_editar_pswFieldContrasena.setVisible(false);
        } else {
            pacientes_editar_pswFieldContrasena.setText(pacientes_editar_txtFieldContraseña.getText());
            pacientes_editar_pswFieldContrasena.setVisible(true);
            pacientes_editar_txtFieldContraseña.setVisible(false);
        }

        if (pacientes_crear_mostrarContrasena.isSelected()) {
            pacientes_crear_txtFieldContrasena.setText(pacientes_crear_pswFieldContrasena.getText());
            pacientes_crear_txtFieldContrasena.setVisible(true);
            pacientes_crear_pswFieldContrasena.setVisible(false);
        } else {
            pacientes_crear_pswFieldContrasena.setText(pacientes_crear_txtFieldContrasena.getText());
            pacientes_crear_pswFieldContrasena.setVisible(true);
            pacientes_crear_txtFieldContrasena.setVisible(false);
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

    private void cargarHistorialVentas(String dni) {
        saleDetailViewsList.clear();
        List<SaleDTO> sales = saleDAO.getSalesByDNI(dni);
        for (SaleDTO sale : sales) {
            List<SalesDetailDTO> salesDetails = salesDetailDAO.getSalesDetailsBySaleId(sale.getIdSale());
            for (SalesDetailDTO salesDetail : salesDetails) {
                saleDetailViewsList.add(new SaleDetailView(sale.getSaleDate(), sale.getIdLocation(), sale.getDiagnosis(), salesDetail.getSubtotal()));
            }
        }
        pacientes_historial_tableViewMovimientos.setItems(saleDetailViewsList);
        pacientes_historial_tableViewMovimientos.refresh();
    }

    private void cargarPacientes() {
        pacientesList.clear();
        List<PatientDTO> pacientes = patientDAO.getAllPatients();
        if (pacientes != null && !pacientes.isEmpty()) {
            pacientesList.addAll(pacientes);
        }
        pacientes_tableViewPacientes.setItems(pacientesList);
        pacientes_tableViewPacientes.refresh();
    }

    private void configurarBaseDatos() {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            patientDAO = new PatientDAO(connection);
            saleDAO = new SaleDAO(connection);
            salesDetailDAO = new SalesDetailDAO(connection);
        } else {
            alerta.mensajeError("No se pudo establecer la conexión con la base de datos.");
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
}
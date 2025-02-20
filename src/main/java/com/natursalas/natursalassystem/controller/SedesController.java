package com.natursalas.natursalassystem.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class SedesController implements Initializable {

    @FXML
    private Button bttnCerrarSesion;

    @FXML
    private Button bttnInformacion;

    @FXML
    private Button bttnPacientes;

    @FXML
    private Button bttnProductos;

    @FXML
    private Button bttnVentas;

    @FXML
    private Label informacion_blPacientesNuevos;

    @FXML
    private TableColumn<?, ?> informacion_columna_apellidosPaciente;

    @FXML
    private TableColumn<?, ?> informacion_columna_dni;

    @FXML
    private TableColumn<?, ?> informacion_columna_nombrePaciente;

    @FXML
    private TableColumn<?, ?> informacion_columna_numeroPaciente;

    @FXML
    private Label informacion_lblPacientesAtendidos;

    @FXML
    private Label informacion_lblProductoMasVendido;

    @FXML
    private Label informacion_lblProductosVendidos;

    @FXML
    private TableView<?> informacion_tableViewClientesAtendidos;

    @FXML
    private TableColumn<?, ?> inventarios_columna_cantidad;

    @FXML
    private TableColumn<?, ?> inventarios_columna_categoria;

    @FXML
    private TableColumn<?, ?> inventarios_columna_idProducto;

    @FXML
    private TableColumn<?, ?> inventarios_columna_precio;

    @FXML
    private TableColumn<?, ?> inventarios_columna_producto;

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
    private Button pacientes_bttnAgregarPaciente;

    @FXML
    private Button pacientes_bttnBuscar;

    @FXML
    private Button pacientes_bttnEditarPaciente;

    @FXML
    private TableColumn<?, ?> pacientes_columna_apellidos;

    @FXML
    private TableColumn<?, ?> pacientes_columna_dni;

    @FXML
    private TableColumn<?, ?> pacientes_columna_edad;

    @FXML
    private TableColumn<?, ?> pacientes_columna_nacimiento;

    @FXML
    private TableColumn<?, ?> pacientes_columna_nombres;

    @FXML
    private TableColumn<?, ?> pacientes_columna_telefono;

    @FXML
    private TableColumn<?, ?> pacientes_historial_columna_diagnostico;

    @FXML
    private TableColumn<?, ?> pacientes_historial_columna_fecha;

    @FXML
    private TableColumn<?, ?> pacientes_historial_columna_precioTotal;

    @FXML
    private Label pacientes_historial_dni;

    @FXML
    private Label pacientes_historial_lastName;

    @FXML
    private Label pacientes_historial_nacimiento;

    @FXML
    private Label pacientes_historial_name;

    @FXML
    private TableView<?> pacientes_historial_tableViewMovimientos;

    @FXML
    private Label pacientes_historial_telefono;

    @FXML
    private TableView<?> pacientes_tableViewPacientes;

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
    private Button productos_bttnAgregar;

    @FXML
    private Button productos_bttnAumentar;

    @FXML
    private Button productos_bttnEditar;

    @FXML
    private TableColumn<?, ?> productos_columna_cantidadAumentada;

    @FXML
    private TableColumn<?, ?> productos_columna_categoria;

    @FXML
    private TableColumn<?, ?> productos_columna_fechaAumento;

    @FXML
    private TableColumn<?, ?> productos_columna_idProducto;

    @FXML
    private TableColumn<?, ?> productos_columna_producto;

    @FXML
    private TableView<?> productos_tableViewInventario;

    @FXML
    private Button ventas_bttnAgregarVenta;

    @FXML
    private Button ventas_bttnFiltrar;

    @FXML
    private TableColumn<?, ?> ventas_columna_PrecioTotal;

    @FXML
    private TableColumn<?, ?> ventas_columna_apellidosPaciente;

    @FXML
    private TableColumn<?, ?> ventas_columna_diagnostico;

    @FXML
    private TableColumn<?, ?> ventas_columna_dniPaciente;

    @FXML
    private TableColumn<?, ?> ventas_columna_fechaVenta;

    @FXML
    private TableColumn<?, ?> ventas_columna_nombrePaciente;

    @FXML
    private DatePicker ventas_filtrar_desdeDate;

    @FXML
    private DatePicker ventas_filtrar_hastaDate;

    @FXML
    private ComboBox<?> ventas_filtrar_pacientes;

    @FXML
    private ComboBox<?> ventas_filtrar_productos;

    @FXML
    private TableView<?> ventas_tableViewVentas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarReloj();
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

    public void switchForm(ActionEvent event) {
        panelInformacion.setVisible(event.getSource() == bttnInformacion);
        panelPacientes.setVisible(event.getSource() == bttnPacientes);
        panelVentas.setVisible(event.getSource() == bttnVentas);
        panelProductos.setVisible(event.getSource() == bttnProductos);
    }
}

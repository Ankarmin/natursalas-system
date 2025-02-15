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

public class AdminController implements Initializable {

    @FXML
    private Button bttnCerrarSesion;

    @FXML
    private Button bttnCrearCuenta;

    @FXML
    private Button bttnInformacion;

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
    private TableColumn<?, ?> pacientes_columna_dni;

    @FXML
    private TableColumn<?, ?> pacientes_columna_edad;

    @FXML
    private TableColumn<?, ?> pacientes_columna_nacimiento;

    @FXML
    private TableColumn<?, ?> pacientes_columna_nombre;

    @FXML
    private TableColumn<?, ?> pacientes_columna_sede;

    @FXML
    private TableColumn<?, ?> pacientes_columna_telefono;

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
    private TableColumn<?, ?> pacientes_historial_columna_cantidad;

    @FXML
    private TableColumn<?, ?> pacientes_historial_columna_fecha;

    @FXML
    private TableColumn<?, ?> pacientes_historial_columna_precioTotal;

    @FXML
    private TableColumn<?, ?> pacientes_historial_columna_producto;

    @FXML
    private TableColumn<?, ?> pacientes_historial_columna_sede;

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
    private AnchorPane panelCrearCuenta;

    @FXML
    private AnchorPane panelInformacion;

    @FXML
    private AnchorPane panelPacientes;

    @FXML
    private AnchorPane panelVentas;

    @FXML
    private Button ventas_bttnFiltrar;

    @FXML
    private TableColumn<?, ?> ventas_columna_PrecioTotal;

    @FXML
    private TableColumn<?, ?> ventas_columna_cantidadVendida;

    @FXML
    private TableColumn<?, ?> ventas_columna_codigo;

    @FXML
    private TableColumn<?, ?> ventas_columna_paciente;

    @FXML
    private TableColumn<?, ?> ventas_columna_precioUnitario;

    @FXML
    private TableColumn<?, ?> ventas_columna_producto;

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
    private TableView<?> ventas_tableViewVentas;

    // PA CAMBIAR ENTRE PANELES
    public void switchForm(ActionEvent event){
        if(event.getSource() == bttnInformacion){
            panelInformacion.setVisible(true);
            panelPacientes.setVisible(false);
            panelVentas.setVisible(false);
            panelCrearCuenta.setVisible(false);
        } else if(event.getSource() == bttnPacientes){
            panelInformacion.setVisible(false);
            panelPacientes.setVisible(true);
            panelVentas.setVisible(false);
            panelCrearCuenta.setVisible(false);
        } else if(event.getSource() == bttnVentas){
            panelInformacion.setVisible(false);
            panelPacientes.setVisible(false);
            panelVentas.setVisible(true);
            panelCrearCuenta.setVisible(false);
        } else if(event.getSource() == bttnCrearCuenta){
            panelInformacion.setVisible(false);
            panelPacientes.setVisible(false);
            panelVentas.setVisible(false);
            panelCrearCuenta.setVisible(true);
        }
    }

    // PA PONER LA FECHA Y HORA EN TIEMPO REAL
    public void runTime() {

        new Thread() {

            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true) {
                    try {

                        Thread.sleep(1000); // 1000 ms = 1s

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Platform.runLater(() -> {
                        lblFecha.setText(format.format(new Date()));
                    });
                }
            }
        }.start();

    }

    //pa mostrar contraseña ps serrano gaaa
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runTime();
    }
}

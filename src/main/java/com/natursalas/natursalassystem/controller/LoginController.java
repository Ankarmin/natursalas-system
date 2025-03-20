package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dto.UserDTO;
import com.natursalas.natursalassystem.service.DatabaseConnection;
import com.natursalas.natursalassystem.service.UserService;
import com.natursalas.natursalassystem.util.AlertMessages;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class LoginController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @FXML
    private Button bttnIngresar;
    @FXML
    private CheckBox checkBoxMostrarContrasena;
    @FXML
    private PasswordField txtContrasenaUsuario;
    @FXML
    private TextField txtMostrarContrasena;
    @FXML
    private TextField txtNombreUsuario;

    private UserService userService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarBaseDatos();
        configurarEventosTeclado();
        txtMostrarContrasena.setVisible(false);
    }

    private void configurarBaseDatos() {
        Connection connection = DatabaseConnection.getConnection();
        userService = new UserService(connection);
    }

    @FXML
    private void ingresar() {
        String userName = txtNombreUsuario.getText().trim();
        String password = obtenerPassword();

        if (userName.isEmpty() || password.isEmpty()) {
            AlertMessages.mostrarAlerta("Todos los campos deben estar llenos.", Alert.AlertType.WARNING);
            return;
        }

        if (userService == null) {
            AlertMessages.mostrarAlerta("No se puede iniciar sesión sin conexión a la base de datos.", Alert.AlertType.ERROR);
            return;
        }

        autenticarUsuario(userName, password);
    }

    private String obtenerPassword() {
        return txtContrasenaUsuario.isVisible() ? txtContrasenaUsuario.getText() : txtMostrarContrasena.getText();
    }

    private void autenticarUsuario(String userName, String password) {
        try {
            UserDTO user = userService.getUser(userName);
            if (user != null && user.getPassword().equals(password)) {
                AlertMessages.mostrarAlerta("Inicio de sesión exitoso.", Alert.AlertType.INFORMATION);
                abrirPanelSegunRol(user.getRole(), user.getIdLocation());
            } else {
                AlertMessages.mostrarAlerta("Nombre de usuario o contraseña incorrectos.", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            AlertMessages.mostrarAlerta("Error al procesar el inicio de sesión.", Alert.AlertType.ERROR);
            LOGGER.severe("Error en la autenticación del usuario " + userName + ": " + e.getMessage());
        }
    }

    private void abrirPanelSegunRol(String role, String idLocation) {
        switch (role) {
            case "admin":
                abrirVentana("Sistema de Administrador", "/com/natursalas/natursalassystem/view/fxml/Admin.fxml", null);
                break;
            case "user":
                abrirVentana("Sistema para Sede", "/com/natursalas/natursalassystem/view/fxml/Sedes.fxml", idLocation);
                break;
            default:
                AlertMessages.mostrarAlerta("Rol de usuario no reconocido.", Alert.AlertType.ERROR);
        }
    }

    private void abrirVentana(String titulo, String fxmlPath, String idLocation) {
        try {
            Stage stage = (Stage) bttnIngresar.getScene().getWindow();
            stage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = fxmlLoader.load();

            if (idLocation != null) {
                SedesController controller = fxmlLoader.getController();
                controller.setIdLocation(idLocation);
            }

            Stage nuevaVentana = new Stage();
            nuevaVentana.setTitle(titulo);
            nuevaVentana.setScene(new Scene(root));
            nuevaVentana.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/natursalas/natursalassystem/view/assets/logo.png"))));
            nuevaVentana.setOnCloseRequest(event -> {
                Platform.exit();
                System.exit(0);
            });
            nuevaVentana.show();
        } catch (IOException e) {
            AlertMessages.mostrarAlerta("Error al abrir la ventana de " + titulo + ".", Alert.AlertType.ERROR);
            LOGGER.severe("No se pudo abrir la ventana: " + titulo + " (" + fxmlPath + "). Error: " + e.getMessage());
        }
    }

    @FXML
    private void mostrarContrasena() {
        boolean mostrar = checkBoxMostrarContrasena.isSelected();
        txtMostrarContrasena.setVisible(mostrar);
        txtContrasenaUsuario.setVisible(!mostrar);
        if (mostrar) {
            txtMostrarContrasena.setText(txtContrasenaUsuario.getText());
        } else {
            txtContrasenaUsuario.setText(txtMostrarContrasena.getText());
        }
    }

    private void configurarEventosTeclado() {
        txtNombreUsuario.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                ingresar();
            }
        });

        txtContrasenaUsuario.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                ingresar();
            }
        });

        txtMostrarContrasena.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                ingresar();
            }
        });
    }
}
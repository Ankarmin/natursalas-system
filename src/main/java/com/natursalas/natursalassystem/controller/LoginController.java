package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dao.UserDAO;
import com.natursalas.natursalassystem.service.DatabaseConnection;
import com.natursalas.natursalassystem.util.AlertMessages;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
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

    private final AlertMessages alerta = new AlertMessages();
    private UserDAO userDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            userDAO = new UserDAO(connection);
        } else {
            alerta.mensajeError("No se pudo establecer la conexión con la base de datos.");
            return;
        }

        txtMostrarContrasena.setVisible(false);
    }

    @FXML
    public void ingresar() {
        String userName = txtNombreUsuario.getText();
        String password = txtContrasenaUsuario.isVisible() ? txtContrasenaUsuario.getText() : txtMostrarContrasena.getText();

        if (userName.isEmpty() || password.isEmpty()) {
            alerta.mensajeError("Todos los campos deben estar llenos.");
            return;
        }

        if (userDAO == null) {
            alerta.mensajeError("No se puede iniciar sesión sin conexión a la base de datos.");
            return;
        }

        try {
            if (userDAO.loginUser(userName, password)) {
                alerta.mensajeConfirmacion("Inicio de sesión exitoso.");
            } else {
                alerta.mensajeError("Nombre de usuario o contraseña incorrectos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            alerta.mensajeError("Error al procesar el inicio de sesión.");
        }
    }

    @FXML
    public void mostrarContrasena() {
        if (checkBoxMostrarContrasena.isSelected()) {
            txtMostrarContrasena.setText(txtContrasenaUsuario.getText());
            txtMostrarContrasena.setVisible(true);
            txtContrasenaUsuario.setVisible(false);
        } else {
            txtContrasenaUsuario.setText(txtMostrarContrasena.getText());
            txtContrasenaUsuario.setVisible(true);
            txtMostrarContrasena.setVisible(false);
        }
    }
}

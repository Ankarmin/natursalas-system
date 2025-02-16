package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dao.UserDAO;
import com.natursalas.natursalassystem.model.dto.UserDTO;
import com.natursalas.natursalassystem.service.DatabaseConnection;
import com.natursalas.natursalassystem.util.AlertMessages;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    // LoginController.java

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
            UserDTO user = userDAO.getUserDetails(userName);
            if (user != null && user.getPassword().equals(password)) {
                alerta.mensajeConfirmacion("Inicio de sesión exitoso.");
                if ("admin".equals(user.getRole())) {
                    openAdmin();
                } else if ("user".equals(user.getRole())) {
                    openUser();
                } else {
                    alerta.mensajeError("Rol de usuario no reconocido.");
                }
            } else {
                alerta.mensajeError("Nombre de usuario o contraseña incorrectos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            alerta.mensajeError("Error al procesar el inicio de sesión.");
        }
    }

    private void openAdmin() {
        try {
            Stage stage = (Stage) bttnIngresar.getScene().getWindow();
            stage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/natursalas/natursalassystem/view/fxml/Admin.fxml"));
            Parent root = fxmlLoader.load();
            Stage adminStage = new Stage();
            adminStage.setTitle("Admin Panel");
            adminStage.setScene(new Scene(root));
            adminStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            alerta.mensajeError("Error al abrir la ventana de Admin.");
        }
    }

    private void openUser() {
        try {
            Stage stage = (Stage) bttnIngresar.getScene().getWindow();
            stage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/natursalas/natursalassystem/view/fxml/User.fxml"));
            Parent root = fxmlLoader.load();
            Stage userStage = new Stage();
            userStage.setTitle("User Panel");
            userStage.setScene(new Scene(root));
            userStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            alerta.mensajeError("Error al abrir la ventana de User.");
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
package com.natursalas.natursalassystem.model.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
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

    private AlertMessages alerta = new AlertMessages();

    //al presionar el boton Ingresar, verificará esto
    public void ingresar(){
        if (txtNombreUsuario.getText().isEmpty() || txtContrasenaUsuario.getText().isEmpty()){
            alerta.mensajeError("Todos los campos deben estar llenos.");
        } else {
            //haces tu codigo pa coger el texto en textFields y verificar el database gaaa
            try {
                //este if es para que no haya error cruces al presionar el checkbox de mostrar contraseña
                if (!txtMostrarContrasena.isVisible()) {
                    if (!txtMostrarContrasena.getText().equals(txtContrasenaUsuario.getText())) {
                        txtMostrarContrasena.setText(txtContrasenaUsuario.getText());
                    }
                } else {
                    if (!txtMostrarContrasena.getText().equals(txtContrasenaUsuario.getText())) {
                        txtContrasenaUsuario.setText(txtMostrarContrasena.getText());
                    }
                }

                //sigues con tu codigo para verificar los datos en los textfields
                //si los datos coinciden, pones esta linea:
                alerta.mensajeInformacion("Inicio de sesión exitoso.");

                //si los datos no coinciden pones esta liena:
                alerta.mensajeError("Nombre de usuario o contraseña incorrecto.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //para mostrar contrasena cuando marque el checkbox NO CAMBIES EL NOMBRE DE LA FUNCION
    public void mostrarContrasena() {
        if (checkBoxMostrarContrasena.isSelected()) {
            txtMostrarContrasena.setText(txtContrasenaUsuario.getText());
            txtMostrarContrasena.setVisible(true);
            txtContrasenaUsuario.setVisible(false);
        } else {
            txtContrasenaUsuario.setText(txtMostrarContrasena.getText());
            txtMostrarContrasena.setVisible(false);
            txtContrasenaUsuario.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}

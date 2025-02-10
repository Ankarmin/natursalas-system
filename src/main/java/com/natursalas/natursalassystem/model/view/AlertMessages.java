package com.natursalas.natursalassystem.model.view;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertMessages {
    private Alert alert;

    public void mensajeError(String mensaje) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Mensaje de error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void mensajeInformacion(String mensaje) {
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mensaje de información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public boolean mensajeConfirmacion(String mensaje) {

        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Mensaje de confirmación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {
            return true;
        } else {
            return false;
        }

    }
}

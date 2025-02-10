module com.natursalas.natursalassystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.natursalas.natursalassystem to javafx.fxml;
    exports com.natursalas.natursalassystem;
}
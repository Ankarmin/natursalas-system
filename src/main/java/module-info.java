module com.natursalas.natursalassystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.natursalas.natursalassystem to javafx.graphics;
    opens com.natursalas.natursalassystem.view.fxml to javafx.fxml;
    opens com.natursalas.natursalassystem.controller to javafx.fxml;
    opens com.natursalas.natursalassystem.util to javafx.fxml;
    opens com.natursalas.natursalassystem.model.dto to javafx.base;

    exports com.natursalas.natursalassystem.controller;
    exports com.natursalas.natursalassystem.util;
}

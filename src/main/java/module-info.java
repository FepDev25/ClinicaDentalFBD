module com.cultodeportivo.proyecto_fbd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.cultodeportivo.proyecto_fbd to javafx.fxml;
    opens com.cultodeportivo.Modelos to javafx.base;
    exports com.cultodeportivo.proyecto_fbd;
}

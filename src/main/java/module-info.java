module com.decorateur {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.decorateur to javafx.fxml;
    exports com.decorateur;
}

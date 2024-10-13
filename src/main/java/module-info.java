module com.example.tp1_conception {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens com.example.tp1_conception to javafx.fxml;
    exports com.example.tp1_conception;
}
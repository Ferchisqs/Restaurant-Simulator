module com.example.restaurantsimulator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.restaurantsimulator to javafx.fxml;
    exports com.example.restaurantsimulator;
}
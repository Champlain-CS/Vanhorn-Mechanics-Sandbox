module com.example.vanhornmechanicssimulator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.vanhornmechanicssimulator to javafx.fxml;
    exports com.example.vanhornmechanicssimulator;
}
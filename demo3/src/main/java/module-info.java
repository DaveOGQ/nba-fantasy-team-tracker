module demo3.app.demo3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens demo3.app.demo3 to javafx.fxml;
    exports demo3.app.demo3;
}
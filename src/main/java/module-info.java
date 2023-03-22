module com.example.frontenduzbox {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens uzBox to javafx.fxml;
    exports uzBox;
}
module frontEndUZBox.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;

    opens uzBox to javafx.fxml;
    exports uzBox;
}
module frontEndUZBox.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;

    opens uzBox to javafx.fxml;
    exports uzBox;
    exports uzBox.user;
    opens uzBox.user to javafx.fxml;
    exports uzBox.user.session;
    opens uzBox.user.session to javafx.fxml;
    exports uzBox.user.session.authorization;
    opens uzBox.user.session.authorization to javafx.fxml;
    exports uzBox.popups;
    opens uzBox.popups to javafx.fxml;
    exports uzBox.filesystem.file;
    opens uzBox.filesystem.file to javafx.fxml;
}
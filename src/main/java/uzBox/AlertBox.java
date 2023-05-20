package uzBox;

import javafx.scene.control.Alert;

public class AlertBox {

    public void alertErr(String title, String header, String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(error);
        alert.showAndWait();
    }

    public void alertOk(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

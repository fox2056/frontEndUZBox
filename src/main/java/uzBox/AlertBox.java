package uzBox;

import javafx.scene.control.Alert;

public class AlertBox {

    void alertErr(String title, String header, String blad) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(blad);
        alert.showAndWait();
    }

    void alertOk(String title, String header, String potwierdzenie) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(potwierdzenie);
        alert.showAndWait();
    }
}

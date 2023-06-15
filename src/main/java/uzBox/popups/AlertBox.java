package uzBox.popups;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

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

    public Alert confirmationDialog(String title, String header, String content){
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        ButtonType confirmation = new ButtonType("Tak", ButtonBar.ButtonData.YES);
        ButtonType cancel = new ButtonType("Nie", ButtonBar.ButtonData.NO);
        dialog.getButtonTypes().setAll(confirmation,cancel);
        return dialog;
    }
}

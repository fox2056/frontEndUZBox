package uzBox.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uzBox.AlertBox;
import uzBox.user.authorization.UserAuthorizationController;

import java.io.IOException;


public class RegistrationService {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void loginFormsShow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/uzBox/loginForm.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("registrationForm.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void registerUser(ActionEvent event) throws IOException {
        AlertBox alertbox = new AlertBox();
        User user = new User(loginField.getText(), passwordField.getText());
        UserAuthorizationController userAuthorizationController = new UserAuthorizationController(user);
        String response = userAuthorizationController.registerUser();
        if (response == "200") {
            alertbox.alertOk("Rejestracja", "Rezultat:", "Zarejestrowałeś się");
            loginFormsShow(event);
        } else {
            alertbox.alertErr("Błąd", "Nie można zarejestrować się", response);
        };

    }
}
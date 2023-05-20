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
import uzBox.Controller;
import uzBox.user.authorization.UserAuthorizationController;
import uzBox.user.session.authorization.SessionController;

import java.io.IOException;

public class LoginService {

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;


    @FXML
    public void appFormsShow(ActionEvent event, SessionController sessionController) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/uzBox/uzBox.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setUserSession(sessionController);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loginUser(ActionEvent event) throws IOException {
        AlertBox alertbox = new AlertBox();
        User user = new User(nameField.getText(), passwordField.getText());
        UserAuthorizationController userAuthorizationController = new UserAuthorizationController(user);
        String response = userAuthorizationController.loginUser();
        if (response == "200") {
            alertbox.alertOk("Logowanie", "Rezultat:", "Zalogowałeś się");
            SessionController sessionController = new SessionController(userAuthorizationController.retrieveUserSession());
            appFormsShow(event, sessionController);
        } else {
            alertbox.alertErr("Błąd", "Nie można zalogować się", response);
        }
    }

    @FXML
    public void registrationFormsShow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/uzBox/registrationForm.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

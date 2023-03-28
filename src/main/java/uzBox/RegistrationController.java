package uzBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class RegistrationController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField emailField;

    @FXML
    private Button loginButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    void loginFormsShow(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void registrate(ActionEvent event) {


        User user = new User(nameField.getText(), passwordField.getText());
        System.out.println(user.getLogin() + " " + user.getHaslo());

    }


}

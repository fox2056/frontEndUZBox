package uzBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void registrationFormsShow(ActionEvent event) throws IOException  {
        root = FXMLLoader.load(getClass().getResource("registrationForm.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loginFormsShow(ActionEvent event) throws IOException  {
        root = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void zalogujSie(ActionEvent event) throws IOException  {
        root = FXMLLoader.load(getClass().getResource("uzBox.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
package uzBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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

    @FXML
    public void ustawieniaWindow(ActionEvent event) throws IOException {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ustawienia");

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Ustawienia.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);

        window.setScene(scene);

        window.showAndWait();
    }


}
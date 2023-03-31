package uzBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



public class RegistrationController {

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

    void blad(String exc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Błąd");
        alert.setHeaderText("Nie można zarejestrować się");
        alert.setContentText(exc);

        alert.showAndWait();
    }


    @FXML
    void loginFormsShow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void registrate(ActionEvent event) throws IOException {

        User user = new User(nameField.getText(), passwordField.getText());


        String url = "http://localhost:5050/user/register/";
        String json = "{\"login\": \"" + user.getLogin() + "\", \"password\": \"" + user.getHaslo() + "\"}";

        System.out.println(json);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        // Ustaw nagłówek Content-Type na application/json
        httpPost.setHeader("Content-Type", "application/json");

        // Ustaw treść żądania
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);

        // Wyślij żądanie
        CloseableHttpResponse response = client.execute(httpPost);

        // Odczytaj odpowiedź
        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            String responseBody = EntityUtils.toString(responseEntity);
            System.out.println(responseBody);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rejestracja");
            alert.setHeaderText("Rezultat:");
            alert.setContentText("Zarejestrowałeś się");
            alert.showAndWait();
            loginFormsShow(event);
        }

        // Zamknij połączenie
        response.close();
        client.close();

    }
}

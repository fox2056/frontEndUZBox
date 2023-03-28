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
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.io.OutputStream;


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
    void registrate(ActionEvent event) {

        User user = new User(nameField.getText(), passwordField.getText());
        System.out.println(user.getLogin() + " " + user.getHaslo());

        try {
            // Utwórz obiekt JSON z danymi
            String userJson = String.format("{\"login\": \"%s\", \"password\": \"%s\"}",
                    user.getLogin(),
                    user.getHaslo());
            // Utwórz połączenie HTTP
            URL url = new URL("http://localhost:5050/user/register/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Wyślij dane JSON do backendu
            OutputStream os = connection.getOutputStream();
            os.write(userJson.getBytes());
            os.flush();
            os.close();

            // Pobierz odpowiedź od backendu
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Rejestracja");
                alert.setHeaderText("Rezultat:");
                alert.setContentText("Zarejestrowałeś się");
                alert.showAndWait();
                loginFormsShow(event);

            } else {
                blad("Nie wiadomo");
            }

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            blad(e.getMessage());
            throw new RuntimeException(e);
        }
    }}

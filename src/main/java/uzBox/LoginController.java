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

import java.io.*;
import java.lang.Object;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class LoginController {
    @FXML
    private Button loginButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    void appFormsShow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("uzBox.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    void blad(String exc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Błąd");
        alert.setHeaderText("Nie można zalogować się");
        alert.setContentText(exc);

        alert.showAndWait();
    }

    @FXML
    public void zalogujSie(ActionEvent event) throws IOException {

        User user = new User(nameField.getText(), passwordField.getText());

        try {
            // Utwórz obiekt JSON z danymi
            String userJson = String.format("{\"login\": \"%s\", \"password\": \"%s\"}",
                    user.getLogin(),
                    user.getHaslo());
            // Utwórz połączenie HTTP
            URL url = new URL("http://localhost:5050/user/login/");
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
                alert.setTitle("Logowanie");
                alert.setHeaderText("Rezultat:");
                alert.setContentText("Zalogowałeś się");
                alert.showAndWait();
                appFormsShow(event);

            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (connection.getInputStream())));

                String output;
                StringBuilder response = new StringBuilder();

                while ((output = br.readLine()) != null) {
                    response.append(output);
                }

                connection.disconnect();

                String responseBody = response.toString();

                blad(responseBody);
            }

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            blad(" Wyjątek " + e.getMessage() +" \n ");
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void registrationFormsShow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registrationForm.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

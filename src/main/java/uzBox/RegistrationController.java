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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.io.OutputStream;


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
    void loginFormsShow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
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
                System.out.println("Backend response: " + connection.getInputStream().toString());
            } else {
                System.out.println("Backend returned an error: " + connection.getResponseCode());
            }

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }}

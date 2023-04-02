package uzBox;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import okhttp3.*;

import java.io.IOException;


public class RegistrationController {

    private static final String URL = "http://localhost:5050/user/register/";

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    void blad(String exc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Błąd");
        alert.setHeaderText("Nie można zarejestrować się");
        alert.setContentText(exc);

        alert.showAndWait();
    }

    void jestOk() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rejestracja");
        alert.setHeaderText("Rezultat:");
        alert.setContentText("Zarejestrowałeś się");
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
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\n    \"login\": \""+user.getLogin()+"\",\n    \"password\": \""+user.getHaslo()+"\"\n}", mediaType);
        Request request = new Request.Builder()
                .url("http://localhost:5050/user/register/")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        if (response.code() == 200) {
            jestOk();
            loginFormsShow(event);
        } else {
            String message = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(message);
            String err = jsonNode.get("error").asText();
            blad(err);
        };

    }
}
package uzBox;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import okhttp3.*;

import java.io.IOException;


public class RegistrationController {

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void loginFormsShow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void zarejestrujSie(ActionEvent event) throws IOException {
        AlertBox alertbox = new AlertBox();
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
            alertbox.alertOk("Rejestracja", "Rezultat:", "Zarejestrowałeś się");
            loginFormsShow(event);
        } else {
            String message = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(message);
            String err = jsonNode.get("error").asText();
            alertbox.alertErr("Błąd", "Nie można zarejestrować się", err);
        };

    }
}
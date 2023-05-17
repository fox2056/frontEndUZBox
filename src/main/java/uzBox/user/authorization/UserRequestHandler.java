package uzBox.user.authorization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class UserRequestHandler {
    private final UserRequestConstructor userRequestConstructor;
    private final OkHttpClient client;
    private Response response;

    public UserRequestHandler(UserRequestConstructor userRequestConstructor) {
        this.userRequestConstructor = userRequestConstructor;
        this.client = new OkHttpClient().newBuilder()
                .build();
    }

    public void sendUserRequest(){
        Request userRequest = userRequestConstructor.getUserRequest();
        try {
            this.response = client.newCall(userRequest).execute();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String userRequestErrorMessage(){
        //TODO: stworzyc klase ktora zamiena json z odpowiedzi do stringa
        String err;
        try {
            String message = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(message);
            err = jsonNode.get("error").asText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return err;
    }

    public String retrieveSessionUUID(){
        String sessionId = null;
        try {
            String message = response.body()
                    .string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(message);
            sessionId = jsonNode.get("id").asText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sessionId;
    }
    public boolean isUserRequestSuccessful(){
        return this.response.isSuccessful();
    }

    public boolean isServerAlive(){
        if(this.response == null|| this.response.code() == 404){
            return false;
        }
        return true;
    }

}

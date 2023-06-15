package uzBox.user.authorization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uzBox.json.JsonErrorMessageHandler;

import java.io.IOException;
import java.util.Objects;

public class UserRequestHandler {
    private final UserRequestConstructor userRequestConstructor;
    private final OkHttpClient client;
    private Response response;
    private final JsonErrorMessageHandler errorMessageHandler;

    public UserRequestHandler(UserRequestConstructor userRequestConstructor) {
        this.userRequestConstructor = userRequestConstructor;
        this.client = new OkHttpClient().newBuilder()
                .build();
        this.errorMessageHandler = new JsonErrorMessageHandler();
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
        return errorMessageHandler.retrieveError(response);
    }

    public String retrieveSessionUUID(){
        String sessionId = null;
        if(!this.response.isSuccessful()){
            return "";
        }
        try {
            String message = Objects.requireNonNull(response.body())
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

package uzBox.user.session.authorization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class SessionRequestHandler {
    private final SessionRequestConstructor sessionRequestConstructor;
    private final OkHttpClient client;
    private Response response;

    public SessionRequestHandler(SessionRequestConstructor sessionRequestConstructor) {
        this.sessionRequestConstructor = sessionRequestConstructor;
        this.client = new OkHttpClient().newBuilder().build();
    }

    public void sendUserRequest(){
        Request sessionRequest = sessionRequestConstructor.getSessionRequest();
        try {
            this.response = client.newCall(sessionRequest).execute();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String sessionRequestErrorMessage(){
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

    public boolean isSessionRequestSuccessful(){
        return this.response.isSuccessful();
    }

    public boolean isServerAlive(){
        if(this.response == null|| this.response.code() == 404){
            return false;
        }
        return true;
    }
}

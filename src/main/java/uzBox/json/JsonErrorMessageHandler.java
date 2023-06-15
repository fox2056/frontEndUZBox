package uzBox.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;

import java.io.IOException;

public class JsonErrorMessageHandler {

    public String retrieveError(Response response){
        String err;
        if(response.isSuccessful()){
            return "Request not Sucessfull";
        }
        if(response.code() == 500){
            return "Internal server error";
        }
        try {
            String message = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(message);
            err = jsonNode.get("errorMessage").asText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return err;
    }

}

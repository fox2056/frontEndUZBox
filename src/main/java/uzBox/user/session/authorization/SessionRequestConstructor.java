package uzBox.user.session.authorization;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class SessionRequestConstructor {

    final private String sessionUUID;
    private Request sessionRequest;

    public SessionRequestConstructor(String sessionUUID) {
        this.sessionUUID = sessionUUID;
    }

    public void constructSessionRequest(String endpoint) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("", mediaType);
        this.sessionRequest = new Request.Builder()
                .url("http://localhost:5050/" + endpoint)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", sessionUUID)
                .build();
    }

    public Request getSessionRequest(){
        return this.sessionRequest;
    }
}

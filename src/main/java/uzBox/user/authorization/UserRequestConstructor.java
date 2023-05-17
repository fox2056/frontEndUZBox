package uzBox.user.authorization;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import uzBox.user.User;

public class UserRequestConstructor {

    final private User user;

    private Request userRequest;

    public UserRequestConstructor(User user) {
        this.user = user;
    }

    public void constructUserRequest(String endpoint){
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\n    \"login\": \""+ user.getLogin()+"\",\n    \"password\": \""+ user.getPassword()+"\"\n}", mediaType);
        this.userRequest = new Request.Builder()
                .url("http://localhost:5050/" + endpoint)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
    }

    public Request getUserRequest() {
        return userRequest;
    }

}

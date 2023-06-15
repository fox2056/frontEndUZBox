package uzBox.user.authorization;

import uzBox.json.JsonErrorMessageHandler;
import uzBox.user.User;
import uzBox.user.session.Session;

public class UserAuthorizationController {
    private final User user;
    private final UserRequestConstructor userRequestConstructor;
    private final UserRequestHandler userRequestHandler;
    private final JsonErrorMessageHandler errorMessageHandler;


    public UserAuthorizationController(User user) {
        this.user = user;
        this.userRequestConstructor = new UserRequestConstructor(this.user);
        this.userRequestHandler = new UserRequestHandler(userRequestConstructor);
        this.errorMessageHandler = new JsonErrorMessageHandler();
    }

    public String registerUser(){
        userRequestConstructor.constructUserRequest("user/register/");
        return getResponse();
    }

    public String loginUser(){
        userRequestConstructor.constructUserRequest("user/login/");
        return getResponse();
    }

    private String getResponse() {
        userRequestHandler.sendUserRequest();
        if(userRequestHandler.isServerAlive()){
        if(userRequestHandler.isUserRequestSuccessful()){
            return "200";
        }
            return userRequestHandler.userRequestErrorMessage();
        }
        return "Cannot connect to server";
    }

    public Session retrieveUserSession(){
        String sessionId = userRequestHandler.retrieveSessionUUID();
        return Session.getInstace(user.getLogin(), sessionId);
    }
}

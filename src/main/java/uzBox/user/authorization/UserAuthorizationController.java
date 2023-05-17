package uzBox.user.authorization;

import uzBox.user.User;
import uzBox.user.UserSession;

public class UserAuthorizationController {
    private final User user;
    private final UserRequestConstructor userRequestConstructor;
    private final UserRequestHandler userRequestHandler;


    public UserAuthorizationController(User user) {
        this.user = user;
        this.userRequestConstructor = new UserRequestConstructor(this.user);
        this.userRequestHandler = new UserRequestHandler(userRequestConstructor);
    }

    public String registerUser(){
        userRequestConstructor.constructUserRequest("user/register/");
        userRequestHandler.sendUserRequest();
        if(userRequestHandler.isServerAlive()){
            if(userRequestHandler.isUserRequestSuccessful()){
                return "200";
            }
            return userRequestHandler.userRequestErrorMessage();
        }
        return "Cannot connect to server";
    }

    public String loginUser(){
        userRequestConstructor.constructUserRequest("user/login/");
        userRequestHandler.sendUserRequest();
        if(userRequestHandler.isServerAlive()){
        if(userRequestHandler.isUserRequestSuccessful()){
            return "200";
        }
        return userRequestHandler.userRequestErrorMessage();
        }
        return "Cannot connect to server";
    }

    public UserSession retrieveSessionUUID(){
        String sessionId = userRequestHandler.retrieveSessionUUID();
        return UserSession.getInstace(user.getLogin(), sessionId);
    }
}

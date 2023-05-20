package uzBox.user.session.authorization;

import uzBox.user.session.Session;

import java.util.Objects;

public class SessionController {
    private final Session session;
    private final SessionRequestConstructor sessionRequestConstructor;
    private final SessionRequestHandler sessionRequestHandler;

    public SessionController(Session session) {
        this.session = session;
        this.sessionRequestConstructor = new SessionRequestConstructor(getSessionUUID());
        this.sessionRequestHandler = new SessionRequestHandler(sessionRequestConstructor);
    }


    public String getUsernane(){
        return session.getUserName();
    }

    public String getSessionUUID(){
        return session.getSessionUUID();
    }

    public String logoutSession(){
        sessionRequestConstructor.constructSessionRequest("user/logout/");
        sessionRequestHandler.sendUserRequest();
        if(sessionRequestHandler.isServerAlive()){
            if (sessionRequestHandler.isSessionRequestSuccessful()){
                return "200";
            }
            return sessionRequestHandler.sessionRequestErrorMessage();
        }
        return "Cannot connect to server";
    }

    public boolean doesSessionExists(){
        return !Objects.equals(session.getSessionUUID(), "");
    }
}

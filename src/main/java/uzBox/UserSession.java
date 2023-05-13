package uzBox;

public final class UserSession {

    private static UserSession instance;

    private String userName;
    private String sessionUUID;

    private UserSession(String userName, String sessionUUID) {
        this.userName = userName;
        this.sessionUUID = sessionUUID;
    }

    public static UserSession getInstace(String userName, String sessionUUID) {
        if(instance == null) {
            instance = new UserSession(userName, sessionUUID);
        }
        return instance;
    }

    public static UserSession getInstance() {
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public String getSessionUUID() {
        return sessionUUID;
    }

    public void cleanUserSession() {
        userName = "";// or null
        sessionUUID = "";// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", privileges=" + sessionUUID +
                '}';
    }
}

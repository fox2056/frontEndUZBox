package uzBox.user.session;

public final class Session {

    private static Session instance;

    private String userName;
    private String sessionUUID;

    private Session(String userName, String sessionUUID) {
        this.userName = userName;
        this.sessionUUID = sessionUUID;
    }

    public static Session getInstace(String userName, String sessionUUID) {
        if(instance == null) {
            instance = new Session(userName, sessionUUID);
        }
        return instance;
    }

    public static Session getInstance() {
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

package uzBox;

public class User {
    private final String login;
    private final String haslo;

    public User(String login, String haslo) {
        this.login = login;
        this.haslo = haslo;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }
}

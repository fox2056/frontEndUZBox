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

    @Override
    public String toString() {

        return "{" +
                "login:'" + login + '\'' +
                ", haslo:'" + haslo + '\'' +
                '}';
    }

    public String getHaslo() {
        return haslo;
    }
}

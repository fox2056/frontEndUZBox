package uzBox.user;

public class User {
    private final String login;
    private final String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {

        return "{" +
                "login:'" + login + '\'' +
                ", haslo:'" + password + '\'' +
                '}';
    }

    public String credentialsToJson(){
        return "{\n    \"login\": \""+ this.getLogin()+"\",\n    \"password\": \""+ this.getPassword()+"\"\n}";
    }

    public String getPassword() {
        return password;
    }
}

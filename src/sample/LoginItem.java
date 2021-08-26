package sample;

import java.util.Objects;

public class LoginItem {

    private final String username;
    private final String password;

    public LoginItem(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginItem loginItem = (LoginItem) o;
        return username.equals(loginItem.username) && password.equals(loginItem.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "LoginItem{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

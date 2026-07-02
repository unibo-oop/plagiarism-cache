package model.user;

import java.io.Serializable;

public class UserProfile implements Serializable {

    private static final transient long serialVersionUID = 7059418592537089664L;

    private String username;
    private String password;

    public UserProfile(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "username=" + username + ", password=" + password + "; ";
    }
}

package org.hasan;

public class User {
    private String password;
    private String emailAddress;

    // Needed for Jackson to generator JSON
    public User() {}

    public User(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}

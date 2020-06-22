package org.hasan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@RestController
public class Application {
    private final String CONNECTION_STRING = "jdbc:postgresql://db:5432/postgres";
    private final String DB_USER = "dockeruser";
    private final String DB_PASSWORD = "docker";

    @RequestMapping(value = "/status", method= RequestMethod.GET)
    public String getStatus() {
        return "OK";
    }

    @RequestMapping(value = "/users", method= RequestMethod.GET)
    public List<User> getUsers() throws SQLException {
        // Hardcoded connection String only here for demo purposes
        Connection con = DriverManager.getConnection(CONNECTION_STRING, DB_USER, DB_PASSWORD);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM account");

        List<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(new User(rs.getString("email"), rs.getString("password")));
        }
        con.close();
        return users;
    }

    @RequestMapping(value = "/users", method= RequestMethod.POST)
    public void createUser(@RequestBody User newUser) throws SQLException {
        // Hardcoded connection String only here for demo purposes
        Connection con = DriverManager.getConnection(CONNECTION_STRING, DB_USER, DB_PASSWORD);

        PreparedStatement stmt = con.prepareStatement("INSERT INTO account(email, password) VALUES(?, ?)");
        stmt.setString(1, newUser.getEmailAddress());
        stmt.setString(2, newUser.getPassword());
        int row = stmt.executeUpdate();
        con.close();
        return;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "9000"));
        app.run(args);
    }
}

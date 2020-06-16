package org.hasan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class Application {
    @RequestMapping(value = "/status", method= RequestMethod.GET)
    public String getStatus() {
        return "OK";
    }

    @RequestMapping(value = "/users", method= RequestMethod.GET)
    public List<User> getUsers() throws SQLException {
        // Hardcoded connection String only here for demo purposes
        Connection con = DriverManager.getConnection("jdbc:postgresql://db:5432/postgres", "dockeruser", "docker");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from account");

        List<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(new User(rs.getString("username"), rs.getString("password"), rs.getString("email")));
        }
        con.close();
        return users;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

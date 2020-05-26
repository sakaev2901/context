package com.example.demo.context;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class SQLiteConnection {

    private Connection connection;

    public SQLiteConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't get class. No driver found");
            e.printStackTrace();
        }
        this.connection = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/game.db3");
    }

    public Connection getConnection() {
        return connection;
    }
}

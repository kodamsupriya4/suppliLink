package com.edutech.progressive.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseConnectionManager {

    private static final Properties PROPS = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/main/resources/application.properties")) {
            PROPS.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
    }

    private DatabaseConnectionManager() {
        // utility class
    }

    public static Connection getConnection() throws SQLException {
        String url = PROPS.getProperty("spring.datasource.url");
        String user = PROPS.getProperty("spring.datasource.username");
        String password = PROPS.getProperty("spring.datasource.password");

        return DriverManager.getConnection(url, user, password);
    }
}
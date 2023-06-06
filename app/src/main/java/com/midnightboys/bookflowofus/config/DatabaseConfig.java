package com.midnightboys.bookflowofus.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {
    /* MODE DEVELOPMENT (Auto create if not exist) */
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/db/db_bookflowofus.db";
    /* MODE PRODUCTION */
    // private static final String DB_URL = "jdbc:sqlite::resource:db/db_bookflowofus.db";

    protected static Connection connection;
    protected static Statement statement;
    protected static PreparedStatement preparedStatement;
    protected static ResultSet resultSet;
    protected static String query;

    protected static void connection() {
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

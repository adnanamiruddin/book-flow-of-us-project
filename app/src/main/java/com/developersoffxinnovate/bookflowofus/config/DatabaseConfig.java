package com.developersoffxinnovate.bookflowofus.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {
    private static final String DB_URL = "jdbc:sqlite:db/db_borrowbook.db";
    // Nanti kasih nama db_projectfx.db !

    protected static Connection connection;
    protected static Statement statement;
    protected static PreparedStatement preparedStatement;
    protected static ResultSet resultSet;
    protected static String query;

    protected static void connection() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            // System.out.println("Connection Status: Success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

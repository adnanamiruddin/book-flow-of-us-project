package com.developersoffxinnovate.bookflowofus.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.developersoffxinnovate.bookflowofus.config.DatabaseConfig;

public class AdminController extends DatabaseConfig {

    public static boolean validateLoginAdmin(String user, String password) {
        connection();
        query = "SELECT user, password FROM admin WHERE user=? AND password=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);

            try (ResultSet loginAdmin = preparedStatement.executeQuery()) {
                return loginAdmin.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

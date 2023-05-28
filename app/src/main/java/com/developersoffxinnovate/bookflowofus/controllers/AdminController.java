package com.developersoffxinnovate.bookflowofus.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.developersoffxinnovate.bookflowofus.config.DatabaseConfig;
import com.developersoffxinnovate.bookflowofus.models.Admin;

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

    public static Admin getAdminByUser(String user) {
        Admin admin = null;
        connection();
        query = "SELECT * FROM admin WHERE user=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);

            try (ResultSet adminResult = preparedStatement.executeQuery()) {
                while (adminResult.next()) {
                    int idAdmin = adminResult.getInt("id");
                    String userAdmin = adminResult.getString("user");
                    String passwordAdmin = adminResult.getString("password");

                    admin = new Admin(idAdmin, userAdmin, passwordAdmin);
                    return admin;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}

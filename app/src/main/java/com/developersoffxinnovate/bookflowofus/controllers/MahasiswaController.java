package com.developersoffxinnovate.bookflowofus.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.developersoffxinnovate.bookflowofus.config.DatabaseConfig;

public class MahasiswaController extends DatabaseConfig {

    public static void getDataMahasiswa() {
        connection();
        query = "SELECT * FROM mahasiswa";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("nama"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean validateLogin(String nim, String password) {
        connection();
        query = "SELECT nim, password FROM mahasiswa WHERE nim=? AND password=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nim);
            preparedStatement.setString(2, password);

            try (ResultSet loginUser = preparedStatement.executeQuery()) {
                return loginUser.next(); // bernilai true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // default
    }
}
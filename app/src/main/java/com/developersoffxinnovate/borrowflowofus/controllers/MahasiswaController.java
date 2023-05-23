package com.developersoffxinnovate.borrowflowofus.controllers;

import java.sql.SQLException;

import com.developersoffxinnovate.borrowflowofus.config.DatabaseConfig;

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
}

package com.developersoffxinnovate.bookflowofus.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.developersoffxinnovate.bookflowofus.config.DatabaseConfig;
import com.developersoffxinnovate.bookflowofus.models.Book;

public class BooksController extends DatabaseConfig {

    public static void getDataBuku() {
        connection();
        query = "SELECT * FROM buku";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("judul") + "  " + resultSet.getString("pengarang"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Book getBookById(int id) {
        Book book = null;
        connection();
        query = "SELECT * FROM buku WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            try (ResultSet bookResult = preparedStatement.executeQuery()) {
                while (bookResult.next()) {
                    int idBuku = bookResult.getInt("id");
                    String judul = bookResult.getString("judul");
                    String pengarang = bookResult.getString("pengarang");
                    String penerbit = bookResult.getString("penerbit");
                    int tahunTerbit = bookResult.getInt("tahun_terbit");
                    int stocks = bookResult.getInt("stocks");

                    book = new Book(idBuku, judul, pengarang, penerbit, tahunTerbit, stocks);
                    return book;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}

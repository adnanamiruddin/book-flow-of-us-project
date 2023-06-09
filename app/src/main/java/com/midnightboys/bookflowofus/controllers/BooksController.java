package com.midnightboys.bookflowofus.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.midnightboys.bookflowofus.config.DatabaseConfig;
import com.midnightboys.bookflowofus.models.Book;

public class BooksController extends DatabaseConfig {

    public static void createTableBuku() {
        connection();
        query = "CREATE TABLE IF NOT EXISTS buku (" +
                "id INTEGER NOT NULL UNIQUE," +
                "judul TEXT NOT NULL," +
                "pengarang TEXT," +
                "penerbit TEXT NOT NULL," +
                "tahun_terbit INTEGER," +
                "stok INTEGER NOT NULL," +
                "PRIMARY KEY(id AUTOINCREMENT)" +
                ")";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public static List<Book> getAllBuku() {
        List<Book> books = new ArrayList<>();
        createTableBuku();
        query = "SELECT * FROM buku";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String judul = resultSet.getString("judul");
                String pengarang = resultSet.getString("pengarang");
                String penerbit = resultSet.getString("penerbit");
                int tahunTerbit = resultSet.getInt("tahun_terbit");
                int stok = resultSet.getInt("stok");

                Book book = new Book(id, judul, pengarang, penerbit, tahunTerbit, stok);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static Book getBookById(int id) {
        Book book = null;
        createTableBuku();
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
                    int stok = bookResult.getInt("stok");

                    book = new Book(idBuku, judul, pengarang, penerbit, tahunTerbit, stok);
                    return book;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public static void updateJumlahBukuDipinjam(int idBuku, int howMany) {
        createTableBuku();
        query = "UPDATE buku SET stok=stok+? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, howMany);
            preparedStatement.setInt(2, idBuku);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean validateStok(int idBuku) {
        createTableBuku();
        query = "SELECT stok FROM buku WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idBuku);

            try (ResultSet validateResult = preparedStatement.executeQuery()) {
                if (validateResult.next()) {
                    if (validateResult.getInt("stok") > 0) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

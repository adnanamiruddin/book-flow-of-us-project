package com.developersoffxinnovate.bookflowofus.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.developersoffxinnovate.bookflowofus.config.DatabaseConfig;
import com.developersoffxinnovate.bookflowofus.models.Admin;
import com.developersoffxinnovate.bookflowofus.models.DataPeminjamanBuku;

public class AdminController extends DatabaseConfig {

    public static void createTableAdmin() {
        connection();
        query = "CREATE TABLE IF NOT EXISTS admin (" +
                "id INTEGER NOT NULL UNIQUE," +
                "user TEXT NOT NULL UNIQUE," +
                "password TEXT NOT NULL," +
                "PRIMARY KEY(id AUTOINCREMENT)" +
                ")";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();

            // Insert default Admin
            String insertQuery = "INSERT OR IGNORE INTO admin (id, user, password) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Adnan");
            preparedStatement.setString(3, "pas123");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean validateLoginAdmin(String user, String password) {
        createTableAdmin();
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
        createTableAdmin();
        query = "SELECT * FROM admin WHERE user=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);

            try (ResultSet adminResult = preparedStatement.executeQuery()) {
                while (adminResult.next()) {
                    int idAdmin = adminResult.getInt("id");
                    String userAdmin = adminResult.getString("user");

                    admin = new Admin(idAdmin, userAdmin);
                    return admin;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public static List<DataPeminjamanBuku> getAllDataPeminjamanBuku() {
        List<DataPeminjamanBuku> dataPeminjamanBuku = new ArrayList<>();
        createTableAdmin();
        query = "SELECT * FROM peminjaman_buku";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idMahasiswa = resultSet.getInt("id_mahasiswa");
                int idBuku = resultSet.getInt("id_buku");
                String tanggalPinjam = resultSet.getString("tanggal_pinjam");
                String tanggalKembali = resultSet.getString("tanggal_kembali");
                String status = resultSet.getString("status");

                DataPeminjamanBuku peminjamanBuku = new DataPeminjamanBuku(id, idMahasiswa, idBuku, tanggalPinjam, tanggalKembali, status);
                dataPeminjamanBuku.add(peminjamanBuku);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataPeminjamanBuku;
    }

    public static DataPeminjamanBuku getDataPeminjamanBukuById(int id) {
        DataPeminjamanBuku dataPeminjamanBuku = null;
        createTableAdmin();
        query = "SELECT * FROM peminjaman_buku WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            try (ResultSet dataResult = preparedStatement.executeQuery()) {
                while (dataResult.next()) {
                    int idData = dataResult.getInt("id");
                    int idMahasiswa = dataResult.getInt("id_mahasiswa");
                    int idBuku = dataResult.getInt("id_buku");
                    String tanggalPinjam = dataResult.getString("tanggal_pinjam");
                    String tanggalKembali = dataResult.getString("tanggal_kembali");
                    String status = dataResult.getString("status");

                    dataPeminjamanBuku = new DataPeminjamanBuku(idData, idMahasiswa, idBuku, tanggalPinjam, tanggalKembali, status);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataPeminjamanBuku;
    }

    public static boolean validateAddBook(String judul, String pengarang, String penerbit, int tahunTerbit, int stok) {
        createTableAdmin();
        query = "INSERT INTO buku(judul, pengarang, penerbit, tahun_terbit, stok) VALUES (?, ?, ?, ?, ?)";
        if (judul.isEmpty() || pengarang.isEmpty()) {
            return false;
        }
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, judul);
            preparedStatement.setString(2, pengarang);
            preparedStatement.setString(3, penerbit);
            preparedStatement.setInt(4, tahunTerbit);
            preparedStatement.setInt(5, stok);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

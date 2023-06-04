package com.developersoffxinnovate.bookflowofus.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.developersoffxinnovate.bookflowofus.config.DatabaseConfig;
import com.developersoffxinnovate.bookflowofus.models.Mahasiswa;

public class MahasiswaController extends DatabaseConfig {

    public static void createTableMahasiswa() {
        connection();
        query = "CREATE TABLE IF NOT EXISTS mahasiswa (" +
                "id INTEGER NOT NULL UNIQUE," +
                "nama TEXT NOT NULL," +
                "nim TEXT NOT NULL UNIQUE," +
                "prodi TEXT NOT NULL," +
                "alamat TEXT NOT NULL," +
                "no_telp TEXT NOT NULL UNIQUE," +
                "password TEXT NOT NULL," +
                "buku_dipinjam INTEGER DEFAULT 0," +
                "PRIMARY KEY(id AUTOINCREMENT)" +
                ")";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
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

    public static boolean validateRegister(String nama, String nim, String prodi, String alamat, String noTelp, String password) {
        connection();
        query = "INSERT INTO mahasiswa (nama, nim, prodi, alamat, no_telp, password) VALUES (?, ?, ?, ?, ?, ?)";
        if (nama.isEmpty() || nim.isEmpty() || prodi.isEmpty() || alamat.isEmpty() || noTelp.isEmpty() || password.isEmpty()) {
            return false;
        }
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, nim);
            preparedStatement.setString(3, prodi);
            preparedStatement.setString(4, alamat);
            preparedStatement.setString(5, noTelp);
            preparedStatement.setString(6, password);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Mahasiswa getMahasiswaById(int id) {
        Mahasiswa mahasiswa = null;
        connection();
        query = "SELECT id, nama, nim, prodi, buku_dipinjam FROM mahasiswa WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            try (ResultSet mahasiswaResult = preparedStatement.executeQuery()) {
                while (mahasiswaResult.next()) {
                    int idMahasiswa = mahasiswaResult.getInt("id");
                    String namaMahasiswa = mahasiswaResult.getString("nama");
                    String nimMahasiswa = mahasiswaResult.getString("nim");
                    String prodiMahasiswa = mahasiswaResult.getString("prodi");
                    int bukuDipinjam = mahasiswaResult.getInt("buku_dipinjam");

                    mahasiswa = new Mahasiswa(idMahasiswa, namaMahasiswa, nimMahasiswa, prodiMahasiswa, bukuDipinjam);
                    return mahasiswa;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mahasiswa;
    }

    public static Mahasiswa getMahasiswaByNim(String nim) {
        Mahasiswa mahasiswa = null;
        connection();
        query = "SELECT id, nama, nim, prodi, buku_dipinjam FROM mahasiswa WHERE nim=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nim);

            try (ResultSet mahasiswaResult = preparedStatement.executeQuery()) {
                while (mahasiswaResult.next()) {
                    int idMahasiswa = mahasiswaResult.getInt("id");
                    String namaMahasiswa = mahasiswaResult.getString("nama");
                    String nimMahasiswa = mahasiswaResult.getString("nim");
                    String prodiMahasiswa = mahasiswaResult.getString("prodi");
                    int bukuDipinjam = mahasiswaResult.getInt("buku_dipinjam");

                    mahasiswa = new Mahasiswa(idMahasiswa, namaMahasiswa, nimMahasiswa, prodiMahasiswa, bukuDipinjam);
                    return mahasiswa;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mahasiswa;
    }

    public static void updateJumlahBukuDipinjamMahasiswa(int idMahasiswa, int howMany) {
        connection();
        query = "UPDATE mahasiswa SET buku_dipinjam=buku_dipinjam+? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, howMany);
            preparedStatement.setInt(2, idMahasiswa);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean validatePinjamBuku(int idMahasiswa) {
        connection();
        query = "SELECT buku_dipinjam FROM mahasiswa WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idMahasiswa);

            try (ResultSet validateResult = preparedStatement.executeQuery()) {
                if (validateResult.next()) {
                    if (validateResult.getInt("buku_dipinjam") < 2) {
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

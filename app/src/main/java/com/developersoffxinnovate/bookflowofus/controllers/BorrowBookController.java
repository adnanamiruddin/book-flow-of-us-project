package com.developersoffxinnovate.bookflowofus.controllers;

import java.sql.SQLException;
import java.time.LocalDate;

import com.developersoffxinnovate.bookflowofus.config.DatabaseConfig;

public class BorrowBookController extends DatabaseConfig {
    public static boolean pinjamBuku(int idMahasiswa, int idBuku) {
        LocalDate tanggalPinjam = LocalDate.now();
        LocalDate tanggalKembali = tanggalPinjam.plusDays(7);

        connection();
        query = "INSERT INTO peminjaman_buku (id_mahasiswa, id_buku, tanggal_pinjam, tanggal_kembali, status) VALUES (?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idMahasiswa);
            preparedStatement.setInt(2, idBuku);
            preparedStatement.setString(3, tanggalPinjam.toString());
            preparedStatement.setString(4, tanggalKembali.toString());
            preparedStatement.setString(5, "pinjam");

            int updatedRowsAffected = preparedStatement.executeUpdate();
            if (updatedRowsAffected > 0) {
                MahasiswaController.updateJumlahBukuDipinjamMahasiswa(idMahasiswa, 1);
                // BooksController.updateJumlahBukuDipinjam(idBuku, -1);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // default ygy
    }
}
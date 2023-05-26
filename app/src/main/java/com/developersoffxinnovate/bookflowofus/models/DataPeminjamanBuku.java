package com.developersoffxinnovate.bookflowofus.models;

public class DataPeminjamanBuku {
    private int id;
    private int idMahasiswa;
    private int idBuku;
    private String tanggalPinjam;
    private String tanggalKembali;
    private String status;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdMahasiswa() {
        return idMahasiswa;
    }
    public void setIdMahasiswa(int idMahasiswa) {
        this.idMahasiswa = idMahasiswa;
    }
    public int getIdBuku() {
        return idBuku;
    }
    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }
    public String getTanggalPinjam() {
        return tanggalPinjam;
    }
    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }
    public String getTanggalKembali() {
        return tanggalKembali;
    }
    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
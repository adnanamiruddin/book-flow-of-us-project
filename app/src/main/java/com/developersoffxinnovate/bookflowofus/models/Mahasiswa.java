package com.developersoffxinnovate.bookflowofus.models;

public class Mahasiswa extends Model {
    private String nama;
    private String nim;
    private String prodi;
    private String alamat;
    private String noTelp;
    private String password;

    public Mahasiswa(String nama, String nim, String prodi, String alamat, String noTelp, String password) {
        this.nama = nama;
        this.nim = nim;
        this.prodi = prodi;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.password = password;
    }

    public String getNama() {
        return nama;
    }    
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }
    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getProdi() {
        return prodi;
    }
    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}

package com.developersoffxinnovate.bookflowofus.models;

public class Book {
    private int id;
    private String judul;
    private String pengarang;
    private String penerbit;
    private int tahunterbit;
    private int stok;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getPengarang() {
        return pengarang;
    }
    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }
    public String getPenerbit() {
        return penerbit;
    }
    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
    public int getTahunterbit() {
        return tahunterbit;
    }
    public void setTahunterbit(int tahunterbit) {
        this.tahunterbit = tahunterbit;
    }
    public int getStok() {
        return stok;
    }
    public void setStok(int stok) {
        this.stok = stok;
    }

}

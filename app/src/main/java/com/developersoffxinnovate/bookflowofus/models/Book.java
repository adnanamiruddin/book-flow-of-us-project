package com.developersoffxinnovate.bookflowofus.models;

public class Book extends Model {
    private String judul;
    private String pengarang;
    private String penerbit;
    private int tahunterbit;
    private int stok;

    public Book(String judul, String pengarang, String penerbit, int tahunterbit, int stok) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.tahunterbit = tahunterbit;
        this.stok = stok; 
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

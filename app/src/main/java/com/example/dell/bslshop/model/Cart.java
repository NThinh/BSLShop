package com.example.dell.bslshop.model;

public class Cart {
    public int idsp;
    public String tensp;
    public long giasp;
    public String hinhanh;
    public int soluong;
    public String size;
    public long dongiale;
    public boolean check ;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public long getDongiale() {
        return dongiale;
    }
    public void setDongiale(long dongiale) {
        this.dongiale = dongiale;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Cart(int idsp, String tensp, long giasp, String hinhanh, int soluong, String size, long dongiale, boolean check) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhanh = hinhanh;
        this.soluong = soluong;
        this.size = size;
        this.dongiale = dongiale;
        this.check = check;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getGiasp() {
        return giasp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}

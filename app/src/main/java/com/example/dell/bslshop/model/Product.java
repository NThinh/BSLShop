package com.example.dell.bslshop.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String tensp;
    private int giasp;
    private String hinhanh;
    private String mota;
    private int idsanpham;

    public Product() {
    }

    public Product(int id, String tensp, int giasp, String hinhanh, String mota, int idsanpham) {
        this.id = id;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhanh = hinhanh;
        this.mota = mota;
        this.idsanpham = idsanpham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public int getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}

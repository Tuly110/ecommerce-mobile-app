package com.example.medicineshop_app.model;

import java.io.Serializable;

public class SanPhamMoi implements Serializable {
    int id;
    String tensp;
    String hinhanh;
    String mota;
    String giasp;
    int loai;

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

    public String getGiasp() {
        return giasp;
    }

    public void setGiasp() {
        this.giasp = giasp;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }
}

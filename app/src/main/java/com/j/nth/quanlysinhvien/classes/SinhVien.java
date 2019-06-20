package com.j.nth.quanlysinhvien.classes;

import java.util.Date;

public class SinhVien {
    String MaSV;
    String TenSV;
    String LopSV;
    Date NamSinh;
    String ChuyenNganh;
    byte HinhAnh[];

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String maSV) {
        MaSV = maSV;
    }

    public String getTenSV() {
        return TenSV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public String getLopSV() {
        return LopSV;
    }

    public void setLopSV(String lopSV) {
        LopSV = lopSV;
    }

    public Date getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(Date namSinh) {
        NamSinh = namSinh;
    }

    public String getChuyenNganh() {
        return ChuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        ChuyenNganh = chuyenNganh;
    }

    public byte[] getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public SinhVien(String maSV, String tenSV, String lopSV, Date namSinh, String chuyenNganh, byte[] hinhAnh) {
        MaSV = maSV;
        TenSV = tenSV;
        LopSV = lopSV;
        NamSinh = namSinh;
        ChuyenNganh = chuyenNganh;
        HinhAnh = hinhAnh;
    }
}

package com.j.nth.quanlysinhvien.classes;

public class TaiKhoan {
    String ID_ACCOUNT;
    String USER_NAME;
    String PASSWORD;
    int PREMISSION;

    public TaiKhoan(String ID_ACCOUNT, String USER_NAME, String PASSWORD, int PREMISSION) {
        this.ID_ACCOUNT = ID_ACCOUNT;
        this.USER_NAME = USER_NAME;
        this.PASSWORD = PASSWORD;
        this.PREMISSION = PREMISSION;
    }

    public String getID_ACCOUNT() {
        return ID_ACCOUNT;
    }

    public void setID_ACCOUNT(String ID_ACCOUNT) {
        this.ID_ACCOUNT = ID_ACCOUNT;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public int getPREMISSION() {
        return PREMISSION;
    }

    public void setPREMISSION(int PREMISSION) {
        this.PREMISSION = PREMISSION;
    }
}

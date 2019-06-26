package com.j.nth.quanlysinhvien.classes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //truy vankhong tra ve kq
    public void query(String sql)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    public void insertSinhVien(SinhVien sinhVien) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO SINHVIEN VALUES(?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,sinhVien.getMaSV());
        statement.bindString(2,sinhVien.getTenSV());
        statement.bindString(3,sinhVien.getLopSV());
        statement.bindString(4,sinhVien.getNamSinh().toString());
        statement.bindString(5,sinhVien.getChuyenNganh());
        statement.bindBlob(6,sinhVien.getHinhAnh());

        statement.executeInsert();
    }
    public void updateSinhVien(SinhVien sinhVien) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE SINHVIEN SET TENSV = ?,LOPSV = ?,CN = ?,NAMSINH = ?,HINHANH = ? WHERE MASV = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(6,sinhVien.getMaSV());
        statement.bindString(1,sinhVien.getTenSV());
        statement.bindString(2,sinhVien.getLopSV());
        statement.bindString(3,sinhVien.getNamSinh().toString());
        statement.bindString(4,sinhVien.getChuyenNganh());
        statement.bindBlob(5,sinhVien.getHinhAnh());

        statement.executeUpdateDelete();
    }
    public long insertAccount(NhanVien nhanVien)
    {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "INSERT INTO sql_account(USER_NAME,PASSWORD,PREMISSION) VALUES(?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,nhanVien.getUSER_NAME());
        statement.bindString(1,nhanVien.getPASSWORD());
        statement.bindDouble(1, nhanVien.getPREMISSION());
        long id = statement.executeInsert();
        return id;
    }

    public Cursor getAccount(String username,String pass)
    {
        String sql= "SELECT * WHERE USER_NAME='"+username+"' AND PASSWORD='"+pass+"'";
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_tableStaff="CREATE TABLE IF NOT EXISTS STAFF (" +
                "STAFF_ID VARCHAR(100) PRIMARY KEY," +
                "NAME_STAFF VARCHAR(100)," +
                "AGE_STAFF INT," +
                "ADDRESS VARCHAR(100)," +
                "ID_ACCOUNT INTEGER" +
                ")";
        String sql_account = "CREATE TABLE IF NOT EXISTS ACCOUNT(" +
                "ID_ACCOUNT INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USER_NAME VARCHAR(200)," +
                "PASSWORD VARCHAR(200)," +
                "PREMISSION INT" +
                ")";
        db.execSQL(sql_tableStaff);
        db.execSQL(sql_account);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

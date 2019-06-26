package com.j.nth.quanlysinhvien.classes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        getReadableDatabase();
        Log.i("DB", "dbManager");
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

    //khi đăng kí xong thì sẽ trả về mã của tài khoản vừa đăng kí.
    public long insertAccount(TaiKhoan taiKhoan)
    {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "INSERT INTO ACCOUNT(USER_NAME,PASSWORD,PREMISSION) VALUES(?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,taiKhoan.getUSER_NAME());
        statement.bindString(2,taiKhoan.getPASSWORD());
        statement.bindDouble(3, taiKhoan.getPREMISSION());

        return statement.executeInsert();
    }

    public Cursor getAccount(String username,String pass)
    {
        String sql= "SELECT * FROM ACCOUNT WHERE USER_NAME='"+username+"' AND PASSWORD='"+pass+"'";
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    public boolean check_exist_user(String user)
    {
        String sql= "SELECT * FROM ACCOUNT WHERE USER_NAME='"+user+"'";
        Log.d("AAA",sql);
        SQLiteDatabase database = getReadableDatabase();
        if(database.rawQuery(sql,null).getCount()>0)
            return true;
        return false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DB", "dbOnCreate");
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

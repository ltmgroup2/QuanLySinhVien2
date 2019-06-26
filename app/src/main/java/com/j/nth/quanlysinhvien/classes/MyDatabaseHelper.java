package com.j.nth.quanlysinhvien.classes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public MyDatabaseHelper(Context context) {
        super(context, "QuanLyNhanVien.sqlite", null, 2);
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
    public void insertSTAFF(STAFF STAFF) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO STAFF (NAME_STAFF,AGE_STAFF,ADDRESS,IMAGE) VALUES(?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, STAFF.getName());
        statement.bindString(2, STAFF.getAge());
        statement.bindString(3, STAFF.getAddress());
        statement.bindBlob(4, STAFF.getImage());

        statement.executeInsert();
    }
    public void updateSinhVien(STAFF STAFF) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE STAFF SET NAME_STAFF = ?,AGE_STAFF = ?,ADDRESS = ?,IMAGE = ? WHERE STAFF_ID = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindLong(5, STAFF.getId());
        statement.bindString(1, STAFF.getName());
        statement.bindString(2, STAFF.getAge());
        statement.bindString(3, STAFF.getAddress());
        statement.bindBlob(4, STAFF.getImage());

        statement.executeUpdateDelete();
    }

    //khi đăng kí xong thì sẽ trả về mã của tài khoản vừa đăng kí.
    public long insertAccount(TaiKhoan taiKhoan)
    {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "INSERT INTO ACCOUNT(USER_NAME,PASSWORD) VALUES(?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,taiKhoan.getUSER_NAME());
        statement.bindString(2,taiKhoan.getPASSWORD());

        return statement.executeInsert();
    }

    public Cursor getAccount(String username,String pass)
    {
        String sql= "SELECT * FROM ACCOUNT WHERE USER_NAME= ? AND PASSWORD= ?";
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,new String[]{username,pass});
    }

    public long check_exist_user(String user,String pass)
    {
        String sql= "SELECT * FROM ACCOUNT WHERE USER_NAME= ? AND PASSWORD= ?";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor =  database.rawQuery(sql,new String[]{user,pass});
        try {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        catch (Exception e) {
            return -1;
        }
    }

    public boolean check_exist_user(String user)
    {
        String sql= "SELECT * FROM ACCOUNT WHERE USER_NAME= ?";
        Log.d("AAA",sql);
        SQLiteDatabase database = getReadableDatabase();
        if(database.rawQuery(sql,new String[]{user}).getCount()>0)
            return true;
        return false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DB", "dbOnCreate");
//        String sql2= "DROP TABLE IF EXISTS STAFF";
//        db.execSQL(sql2);
        String sql_tableStaff="CREATE TABLE IF NOT EXISTS STAFF (" +
                "STAFF_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME_STAFF VARCHAR(100)," +
                "AGE_STAFF INTEGER," +
                "ADDRESS VARCHAR(100)," +
                "IMAGE BLOB" +
                ")";
        String sql_account = "CREATE TABLE IF NOT EXISTS ACCOUNT(" +
                "ID_ACCOUNT INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USER_NAME VARCHAR(200)," +
                "PASSWORD VARCHAR(200)" +
                ")";
        db.execSQL(sql_tableStaff);
        db.execSQL(sql_account);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

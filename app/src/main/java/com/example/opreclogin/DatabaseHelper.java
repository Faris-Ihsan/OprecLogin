package com.example.opreclogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DBname = "oprec.db";
    private static final int DBversion= 1;
    private static final String TBLsession= "session";
    private static final String TBLuser = "tabel_user";

    public DatabaseHelper(Context context) {
        super(context, DBname, null, DBversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TBLsession + "(" +
                "id INTEGER PRIMARY KEY," +
                "login TEXT(50)" +
                ");" );

        db.execSQL("CREATE TABLE " + TBLuser + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email TEXT(100)," +
                "username TEXT(100)," +
                "password TEXT(100)," +
                "date_added DATETIME" +
                ");");

        db.execSQL("INSERT INTO "+ TBLsession + "(id, login)VALUES(1, 'Kosong')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBLsession );
        db.execSQL("DROP TABLE IF EXISTS " + TBLuser);
    }

    //check session
    public Boolean checkSession (String sessionValues){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBLsession + " WHERE login = ?", new String[]{sessionValues});
        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    //update session
    public boolean updateSession(String sessionValues, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login",sessionValues);
        long update = db.update(TBLsession, contentValues, "id = "+id, null);
        if (update == -1){
            return false;
        }
        else {
            return true;
        }
    }

    /*
        Database Registrasi
     */

    //insert user
    public boolean insertUser(String email, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("username",username);
        contentValues.put("password", password);
        long insert = db.insert(TBLuser, null, contentValues);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    //palidasi login
    public boolean checkLogin(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBLuser + " WHERE username = ? " +
                "AND password = ?", new String[]{username, password});
        if (cursor.getCount()> 0){
            return true;
        }
        else return false;
    }
}

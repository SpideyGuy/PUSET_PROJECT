package com.example.spidey.helloworld.Asset;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by spidey on 1/2/17.
 */


//alt+enter
public class DbHelperClass extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "MyBioAccount.db";
    public static final String TABLE_NAME = "MyAccount";

    public DbHelperClass(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( id integer primary key,Firstname VARCHAR, Lastname VARCHAR, Username VARCHAR, Email VARCHAR, Contact VARCHAR, Password VARCHAR, DOB VARCHAR, Gender VARCHAR)");
        Log.d("testDatabase", "testDatabase");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertInfo(String firstname, String lastname, String username, String contact, String email, String password, String DOB, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Firstname", firstname);
        contentValues.put("Lastname", lastname);
        contentValues.put("Username", lastname);
        contentValues.put("Email", email);
        contentValues.put("Password", password);
        contentValues.put("Contact", contact);
        contentValues.put("DOB", DOB);
        contentValues.put("Gender", gender);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where Username = '" + username + "' and Password='" + password + "'", null);
        return res;
    }

}

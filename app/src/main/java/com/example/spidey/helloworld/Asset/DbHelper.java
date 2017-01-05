package com.example.spidey.helloworld.Asset;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by spidey on 12/29/16.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MeroBlog.db";
    public static final String TABLE_NAME = "FORM";
    public static final String CONTACTS_COLUMN_FULLNAME = "FullName";
    public static final String CONTACTS_COLUMN_USERNAME = "Username";
    public static final String CONTACTS_COLUMN_EMAIL = "Email";
    public static final String CONTACTS_COLUMN_PASSWORD = "Password";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(id integer primary key, FullName VARCHAR,Username VARCHAR,Email VARCHAR,Password VARCHAR);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);

    }

    public boolean insertInfo(String fullname, String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_NAME, null, "Username" + "=? AND Password=?", new String[]{username, password}, null, null, null);
//        if (cursor != null)
//            return cursor;
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where Username = '" + username + "' and Password='" + password + "'", null);
        return res;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Username, Email from " + TABLE_NAME, null);
        return res;
    }

    public boolean deleteRow(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_NAME, "Email" + "= '" + email + "'", null) > 0;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

}

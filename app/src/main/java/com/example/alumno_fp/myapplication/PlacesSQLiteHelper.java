package com.example.alumno_fp.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlacesSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Places (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100), comments TEXT)";

    public PlacesSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Places");
        db.execSQL(sqlCreate);
    }
}

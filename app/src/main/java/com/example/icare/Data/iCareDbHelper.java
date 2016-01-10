package com.example.icare.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kiran on 1/10/16.
 */
public class iCareDbHelper extends SQLiteOpenHelper {

    // Database name
    private static final String DATABASE_NAME = "iCare.db";

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Constructor to create database
    public iCareDbHelper(Context context) {
        super(context, this.DATABASE_NAME, null, this.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

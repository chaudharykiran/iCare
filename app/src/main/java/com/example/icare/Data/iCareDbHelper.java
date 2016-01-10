package com.example.icare.Data;

import android.content.ContentProvider;
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

    // iCareDbHelper to create database
    public iCareDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // SQL statement in order to create Food table
        final String SQL_CREATE_FOOD_TABLE =
                "CREATE TABLE " + iCareContract.FoodEntry.TABLE_NAME + "(" +
                        iCareContract.FoodEntry._ID + "INTEGER PRIMARY KEY," +
                        iCareContract.FoodEntry.COLUMN_FOOD_ITEM_NAME + "TEXT NOT NULL," +
                        " )";

        // SQL statement in order to create Exercise table
        final String SQL_CREATE_EXERCISE_TABLE =
                "CREATE TABLE " + iCareContract.ExerciseEntry.TABLE_NAME + "(" +
                        iCareContract.ExerciseEntry._ID + "INTEGER PRIMARY KEY," +
                        iCareContract.ExerciseEntry.COLUMN_EXERCISE_NAME + "TEXT NOT NULL," +
                " )";

        // Execute sql statement in order to create sql table in database
        db.execSQL(SQL_CREATE_EXERCISE_TABLE);
        db.execSQL(SQL_CREATE_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // SQL statement in order to delete table in the database
        final String SQL_DELETE_FOOD_TABLE =
                "DROP TABLE IF EXISTS " + iCareContract.FoodEntry.TABLE_NAME ;
        final String SQL_DELETE_EXERCISE_TABLE =
                "DROP TABLE IF EXISTS " + iCareContract.ExerciseEntry.TABLE_NAME;

        db.execSQL(SQL_DELETE_EXERCISE_TABLE);
        db.execSQL(SQL_DELETE_FOOD_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

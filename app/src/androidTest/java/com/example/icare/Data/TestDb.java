package com.example.icare.Data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import java.util.HashSet;

/**
 * Created by kiran on 1/10/16.
 */
public class TestDb extends AndroidTestCase {

    public static String LOG_TAG = TestDb.class.getSimpleName();

    // Since we want to each test with clean database
    private void deleteTheDatabase() {
        mContext.deleteDatabase(iCareDbHelper.DATABASE_NAME);
    }

    public void setUp() {
        deleteTheDatabase();
    }

    /**
     * Create this test in order to testDb
     */
    public void testCreateDb() throws Throwable {
        // build a HashSet of all of the table names we wish to look for
        // Note that there will be another table in the DB that stores the
        // Android metadata (db version information)
        final HashSet<String> tableNameHashSet = new HashSet<String >();
        tableNameHashSet.add(iCareContract.ExerciseEntry.TABLE_NAME);
        tableNameHashSet.add(iCareContract.FoodEntry.TABLE_NAME);

        mContext.deleteDatabase(iCareDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new iCareDbHelper(
                this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());

        // have we created the tables we want?
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        assertTrue("Error: This means that the database has not been created correctly.",
                c.moveToFirst());

        // verify that the tables have been created
        do {
            tableNameHashSet.remove(c.getString(0));
        } while (c.moveToNext());

        // if this fails, it means that our database doesn't contain both the food entry
        // and exercise entry tables
        assertTrue("Error: Our database was created without both the food entry and exercise entry tables",
                tableNameHashSet.isEmpty());

        // now, do our tables contains the correct columns?
        c = db.rawQuery("PRAGMA table_info(" + iCareContract.FoodEntry.TABLE_NAME + ")",
                null);

        assertTrue("Error: This means that we were unable to query the database for table information.",
                c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> foodColumnHashSet = new HashSet<String>();
        foodColumnHashSet.add(iCareContract.FoodEntry._ID);
        foodColumnHashSet.add(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_NAME);

        int columnNameIndex = c.getColumnIndex("name");
        Log.v(LOG_TAG, String.valueOf(columnNameIndex));
        do {
            String columnName = c.getString(columnNameIndex);
            Log.v(LOG_TAG, columnName);
            foodColumnHashSet.remove(columnName);
        } while (c.moveToNext());

        // if this fails, it means that our database doesn't contaion all of the required food
        // entry columns
        assertTrue("Error: The database doesn't contain all of the required exercise entry columns",
                foodColumnHashSet.isEmpty());
        db.close();
    }
}

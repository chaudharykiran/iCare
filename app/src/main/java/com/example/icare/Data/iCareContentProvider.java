package com.example.icare.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by kiran on 1/12/16.
 */
public class iCareContentProvider extends ContentProvider {

    private static final String LOG_TAG = iCareContentProvider.class.getSimpleName();

    // Create Uri matcher used by this content provider
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private iCareDbHelper mOpenHelper;

    static final int EXERCISE = 100;
    static final int FOOD = 200;

    /**
     * Here we create UriMatcher for Food and Exercise table.
     * @return
     */
    private static UriMatcher buildUriMatcher() {

        final UriMatcher matcher= new UriMatcher(sUriMatcher.NO_MATCH);
        final String authority = iCareContract.CONTENT_AUTHORITY;

        // For each type of Uri, we want to create corresponding code
        matcher.addURI(authority, iCareContract.PATH_EXERCISE, EXERCISE);

        matcher.addURI(authority, iCareContract.PATH_FOOD, FOOD);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new iCareDbHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor returnCursor;

        switch (sUriMatcher.match(uri)) {
            case EXERCISE: {
                returnCursor = mOpenHelper.getReadableDatabase().query(
                        iCareContract.ExerciseEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case FOOD: {
                returnCursor = mOpenHelper.getReadableDatabase().query(
                        iCareContract.FoodEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unsupported uri: " + uri);
        }
        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.v(LOG_TAG, "insert called.");

        // Create a new database
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case EXERCISE : {
                long _id = db.insert(iCareContract.ExerciseEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = iCareContract.ExerciseEntry.buildExerciesUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case FOOD: {
                long _id = db.insert(iCareContract.FoodEntry.TABLE_NAME, null, values);

                if (_id > 0) {
                    returnUri = iCareContract.FoodEntry.buildFoodUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}

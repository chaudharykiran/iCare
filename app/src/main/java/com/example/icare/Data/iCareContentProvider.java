package com.example.icare.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by kiran on 1/12/16.
 */
public class iCareContentProvider extends ContentProvider {

    // Create Uri matcher used by this content provider
    private static final UriMatcher sUriMatcher = buildUriMatcher();

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
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
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

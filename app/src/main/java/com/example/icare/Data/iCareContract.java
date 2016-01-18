package com.example.icare.Data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import java.sql.Blob;

/**
 * Created by kiran on 1/10/16.
 *
 * iCareContract is the contract between our Application and
 * our Content Provider
 */
public class iCareContract {
    // The "Content Authority" is the name of entire content provider similar
    // to the relationship between our domain name and its site.A convenient string to use for the
    // content authority is the package name for the app, which is guaranteed to be unique on the
    // device.
    public static final String CONTENT_AUTHORITY = "com.example.icare.provider";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Possible paths
    // For instance : content:://com.example.icare.provider/food/ is valid at
    // accessing weather data. content::/com.example.icare.provider/givemefood/ will
    // fail.
    public static final String PATH_FOOD = "food";
    public static final String PATH_EXERCISE = "exercise";

    /**
     * Inner class to define the contents of food table.
     */
    public static final class FoodEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FOOD).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FOOD;

        // Table name
        public static final String TABLE_NAME = "food";

        // Column food item name
        public static final String COLUMN_FOOD_ITEM_NAME = "food_item_name";
        public static final String COLUMN_FOOD_ITEM_BRIEF_INFO = "food_item_brief_info";
        public static final String COLUMN_FOOD_ITEM_CONTENT = "food_item_content";
        public static final String COLUMN_FOOD_ITEM_TYPE = "food_item_type";
        public static final String COLUMN_FOOD_ITEM_ENERGY = "food_item_energy";
        public static final String COLUMN_FOOD_ITEM_CATEGORY = "food_item_category";
        public static final String COLUMN_FOOD_ITEM_IMAGE = "food_item_image";

        public static Uri buildFoodUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /**
     * Inner class to define the contents of exercise table.
     */
    public static final class ExerciseEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_EXERCISE).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +  CONTENT_AUTHORITY + "/" + PATH_EXERCISE;

        // Table name
        public static final String TABLE_NAME = "exercise";

        // Column exercise name
        public static final String COLUMN_EXERCISE_NAME = "exercise_name";

        public static Uri buildExerciesUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}

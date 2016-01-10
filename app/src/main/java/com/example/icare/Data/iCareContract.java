package com.example.icare.Data;

import android.provider.BaseColumns;

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

        // Table name
        public static final String TABLE_NAME = "food";

        // Column food item name
        public static final String COLUMN_FOOD_ITEM_NAME = "food_item_name";
    }

    /**
     * Inner class to define the contents of exercise table.
     */
    public static final class ExerciseEntry implements BaseColumns {

        // Table name
        public static final String TABLE_NAME = "exercise";

        // Column exercise name
        public static final String COLUMN_EXERCISE_NAME = "exercise_name";
    }
}

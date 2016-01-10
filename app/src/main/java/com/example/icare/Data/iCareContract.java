package com.example.icare.Data;

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
    public static final String PATH_EXERCISE = "food";
    public static final String PATH_BODY = "body";
}

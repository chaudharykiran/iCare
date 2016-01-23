package com.example.icare;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.icare.Data.iCareContentProvider;
import com.example.icare.Data.iCareContract;

import org.apache.http.util.EntityUtils;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment
        implements View.OnClickListener {

    private final static String LOG_TAG = MainActivityFragment.class.getSimpleName();

    TextView textView, textView1, textView2;
    LinearLayout favorites1,favorites2, foods1, foods2, exercise1, exercise2;

    public MainActivityFragment() {
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);

        // Handler when More text is clicked
        textView = (TextView) rootView.findViewById(R.id.favorites);
        textView.setOnClickListener(this);
        textView1 = (TextView) rootView.findViewById(R.id.foods);
        textView1.setOnClickListener(this);
        textView2 = (TextView) rootView.findViewById(R.id.exercise);
        textView2.setOnClickListener(this);
        favorites1 = (LinearLayout) rootView.findViewById(R.id.favorite_item_1);
        favorites2 = (LinearLayout) rootView.findViewById(R.id.favorite_item_2);
        foods1 = (LinearLayout) rootView.findViewById(R.id.foods_item_1);
        foods2 = (LinearLayout) rootView.findViewById(R.id.foods_item_2);
        exercise1 = (LinearLayout) rootView.findViewById(R.id.exercise_item_1);
        exercise2 = (LinearLayout) rootView.findViewById(R.id.exercise_item_2);
        favorites1.setOnClickListener(this);
        favorites2.setOnClickListener(this);
        foods1.setOnClickListener(this);
        foods2.setOnClickListener(this);
        exercise1.setOnClickListener(this);
        exercise2.setOnClickListener(this);


//        // Test for insertion process
//        iCareContentProvider contentProvider = new iCareContentProvider();
        // Create Content Values
//        ContentValues values = new ContentValues();
//        values.put(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_NAME, "Food Item 1");
//        values.put(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_BRIEF_INFO, "This is brief info about food item 1.");
//        values.put(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_CONTENT, "This is content about food item 1.");
//        values.put(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_TYPE, "Types of food item 1.");
//        values.put(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_ENERGY, "Some energy.");
//        values.put(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_CATEGORY, "Some Category.");
//
//        // store image in database
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android);
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        byte[] bitMapData = stream.toByteArray();
//
//        values.put(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_IMAGE, bitMapData);
//
//        Uri foodUri
//                = getActivity().getContentResolver().insert(iCareContract.FoodEntry.CONTENT_URI, values);
//        long foodRowId = ContentUris.parseId(foodUri);
//        Log.v(LOG_TAG , String.valueOf(foodRowId));


        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.favorite_item_1:
                TextView textViewItem1 = (TextView) favorites1.findViewById(R.id.favorite_item_1_name);
                String itemName1 = textViewItem1.getText().toString();
                startAnotherActivity(itemName1);
                break;
            case R.id.favorite_item_2:
                TextView textViewItem2 = (TextView) favorites2.findViewById(R.id.favorite_item_2_name);
                String itemName2 = textViewItem2.getText().toString();
                startAnotherActivity(itemName2);
                break;
            case R.id.foods_item_1:
                TextView textViewItem3 = (TextView) foods1.findViewById(R.id.foods_item_1_name);
                String itemName3 = textViewItem3.getText().toString();
                startAnotherActivity(itemName3);
                break;
            case R.id.foods_item_2:
                TextView textViewItem4 = (TextView) foods2.findViewById(R.id.foods_item_2_name);
                String itemName4 = textViewItem4.getText().toString();
                startAnotherActivity(itemName4);
                break;
            case R.id.exercise_item_1:
                TextView textViewItem5 = (TextView) exercise1.findViewById(R.id.exercise_item_1_name);
                String itemName5 = textViewItem5.getText().toString();
                startAnotherActivity(itemName5);
                break;
            case R.id.exercise_item_2:
                TextView textViewItem6 = (TextView) exercise2.findViewById(R.id.exercise_item_2_name);
                String itemName6 = textViewItem6.getText().toString();
                startAnotherActivity(itemName6);
                break;
            case R.id.favorites:
            case R.id.foods:
            case R.id.exercise:
                Intent intent1 = new Intent(getActivity(), ItemListActivity.class);
                getActivity().startActivity(intent1);
                break;
            default:
                break;
        }
    }

    /**
     * This function will create intent and load some data on it and call another activity.
     */
    private void startAnotherActivity(String itemName) {
        // compose intent
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("ITEM_NAME", itemName);
        getActivity().startActivity(intent);
    }
}

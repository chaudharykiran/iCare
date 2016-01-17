package com.example.icare;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.icare.Data.iCareContentProvider;
import com.example.icare.Data.iCareContract;

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
        iCareContentProvider contentProvider = new iCareContentProvider();
        // Create Content Values
//        ContentValues values = new ContentValues();
//        values.put(iCareContract.ExerciseEntry.COLUMN_EXERCISE_NAME, "Exercise Name 2");
//
//        Uri exerciseUri
//                = getActivity().getContentResolver().insert(iCareContract.ExerciseEntry.CONTENT_URI, values);
//        long exerciseRowId = ContentUris.parseId(exerciseUri);
//        Log.v(LOG_TAG , String.valueOf(exerciseRowId));

        // Test for query database
        Cursor cursor =
                getActivity().getContentResolver().query(
                        iCareContract.ExerciseEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null,
                        null
                );
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String data = cursor.getString(cursor.getColumnIndex(iCareContract.ExerciseEntry.COLUMN_EXERCISE_NAME));
                // do what ever you want here
                Log.v(LOG_TAG, data);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return rootView;
    }

    @Override
    public void onClick(View view) {
//        Intent intent = new Intent(getActivity(), ItemListActivity.class);
//
//        getActivity().startActivity(intent);

        switch (view.getId()) {
            case R.id.favorite_item_1:
            case R.id.favorite_item_2:
            case R.id.foods_item_1:
            case R.id.foods_item_2:
            case R.id.exercise_item_1:
            case R.id.exercise_item_2:
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                getActivity().startActivity(intent);
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
}

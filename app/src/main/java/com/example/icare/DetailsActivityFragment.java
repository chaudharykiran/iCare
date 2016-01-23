    package com.example.icare;

    import android.annotation.TargetApi;
    import android.content.Intent;
    import android.database.Cursor;
    import android.graphics.BitmapFactory;
    import android.graphics.drawable.BitmapDrawable;
    import android.graphics.drawable.Drawable;
    import android.os.Build;
    import android.support.v4.app.Fragment;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;

    import com.example.icare.Data.iCareContract;

    /**
     * A placeholder fragment containing a simple view.
     */
    public class DetailsActivityFragment extends Fragment {

        /* private instance variable. */
        private ImageView itemImageView;
        private TextView itemNameView,itemBriefInfoView, itemContentView, itemTypeView, itemEnergyView;
        private Cursor cursor;
        private String itemName;
        private static final String LOG_TAG = DetailsActivityFragment.class.getSimpleName();

        public DetailsActivityFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_details, container, false);

            // extract information from intent
            Intent intent = getActivity().getIntent();
            itemName = intent.getStringExtra("ITEM_NAME");

            // set the label of this activity as itemName
            getActivity().setTitle(itemName);

            // get references of text view in our layout
            itemImageView = (ImageView) rootView.findViewById(R.id.item_image);
            itemNameView = (TextView) rootView.findViewById(R.id.name);
            itemBriefInfoView = (TextView) rootView.findViewById(R.id.brief_info);
            itemContentView = (TextView) rootView.findViewById(R.id.content);
            itemTypeView = (TextView) rootView.findViewById(R.id.type);
            itemEnergyView = (TextView) rootView.findViewById(R.id.energy);

            // render detail activity fragment
            renderDetailFragment();

            return rootView;
        }

        /**
         * This functions render the detail activity fragment
         */
        private void renderDetailFragment() {
            Cursor cursor = queryData();

            if (cursor.moveToFirst()){
                while(!cursor.isAfterLast()){
                    String item_name = cursor.getString(cursor.getColumnIndex(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_NAME));
                    String item_info = cursor.getString(cursor.getColumnIndex(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_BRIEF_INFO));
                    String item_content = cursor.getString(cursor.getColumnIndex(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_CONTENT));
                    String item_types = cursor.getString(cursor.getColumnIndex(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_TYPE));
                    String item_energy = cursor.getString(cursor.getColumnIndex(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_ENERGY));
                    // get image from cursor
                    byte[] item_image = cursor.getBlob(cursor.getColumnIndex(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_IMAGE));

                    // convert byte to drawable
                    Drawable item_image_drawable = new BitmapDrawable(BitmapFactory.decodeByteArray(item_image, 0, item_image.length));
                    itemImageView.setImageDrawable(item_image_drawable);

                    itemNameView.setText(item_name);
                    itemBriefInfoView.setText(item_info);
                    itemContentView.setText(item_content);
                    itemTypeView.setText(item_types);
                    itemEnergyView.setText(item_energy);
                    // do what ever you want here
                    cursor.moveToNext();
                }
            }

            cursor.close();
        }

        /**
         * This function query from database
         */
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        private Cursor queryData() {
            // A "projection" defines the columns that will be returned for each row
            String[] mProjection = null;

            // Defines a string to contain the selection clause
            String mSelectionClause = iCareContract.FoodEntry.COLUMN_FOOD_ITEM_NAME + " = ?";

            // Initializes an array to contain selection arguments
            String[] mSelectionArgs = {""};
            mSelectionArgs[0] = itemName.toLowerCase();

            // sortOrder
            String mSortOrder = null;

            // query database
            cursor =
                    getActivity().getContentResolver().query(
                            iCareContract.FoodEntry.CONTENT_URI,
                            mProjection,
                            mSelectionClause,
                            mSelectionArgs,
                            mSortOrder,
                            null
                    );
            Log.v(LOG_TAG, String.valueOf(cursor.moveToFirst()));
            return cursor;
        }
    }

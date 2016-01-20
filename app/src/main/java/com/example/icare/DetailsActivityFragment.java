    package com.example.icare;

    import android.database.Cursor;
    import android.graphics.BitmapFactory;
    import android.graphics.drawable.BitmapDrawable;
    import android.graphics.drawable.Drawable;
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
        private ImageView itemImage;
        private TextView itemName,itemBriefInfo, itemContent, itemType, itemEnergy;
        Cursor cursor;
        private static final String LOG_TAG = DetailsActivityFragment.class.getSimpleName();

        public DetailsActivityFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_details, container, false);

            // get references of text view in our layout
            itemImage = (ImageView) rootView.findViewById(R.id.item_image);
            itemName = (TextView) rootView.findViewById(R.id.name);
            itemBriefInfo = (TextView) rootView.findViewById(R.id.brief_info);
            itemContent = (TextView) rootView.findViewById(R.id.content);
            itemType = (TextView) rootView.findViewById(R.id.type);
            itemEnergy = (TextView) rootView.findViewById(R.id.energy);

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
                    //                byte[] food_item_image = cursor.getBlob(cursor.getColumnIndex(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_IMAGE));

                    //                // convert byte to drawable
                    //                Drawable image = new BitmapDrawable(BitmapFactory.decodeByteArray(foodItemImage, 0, foodItemImage.length));
                    //                ImageView exerciseItem1 = (ImageView) rootView.findViewById(R.id.exercise_item_1_image);
                    //                exerciseItem1.setImageDrawable(image);

                    itemName.setText(item_name);
                    itemBriefInfo.setText(item_info);
                    // do what ever you want here
                    cursor.moveToNext();
                }
            }
        }

        /**
         * This function query from database
         */
        private Cursor queryData() {
            // query database
            cursor =
                    getActivity().getContentResolver().query(
                            iCareContract.FoodEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            null,
                            null
                    );

            return cursor;
        }
    }

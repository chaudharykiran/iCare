package com.example.icare;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.icare.Data.iCareContract;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayOutputStream;

public class AddItem extends AppCompatActivity {

    /* private instance */
    private Button submitButton, selectPhoto;
    private EditText itemName, itemBriefInfo, itemContent, itemTypes, itemEnergy, itemCategory;
    private int REQUEST_CAMERA = 100;
    private int SELECT_FILE = 200;

    private static final String LOG_TAG = AddItem.class.getSimpleName();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // event handler for submit button
        submitButton = (Button) findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
            }
        });

        // event handler for add photo button
        selectPhoto = (Button) findViewById(R.id.button_select_photo);
        selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(LOG_TAG, "selectPhoto button clicked.");
                selectPhoto();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * This function extract user input from ui.
     */
    private void extractData() {
        // reference of all edit text
        itemName = (EditText) findViewById(R.id.item_name);
        itemBriefInfo = (EditText) findViewById(R.id.item_brief_info);
        itemContent = (EditText) findViewById(R.id.item_content);
        itemTypes = (EditText) findViewById(R.id.item_types);
        itemEnergy = (EditText) findViewById(R.id.item_energy);
        itemCategory = (EditText) findViewById(R.id.item_category);

        // extract data from edit text
        String item_name = itemName.getText().toString();
        String item_brief_info = itemBriefInfo.getText().toString();
        String item_content = itemContent.getText().toString();
        String item_types = itemTypes.getText().toString();
        String item_energy = itemEnergy.getText().toString();
        String item_category = itemCategory.getText().toString();

        // insert the extracted data in database
        insertIntoDatabase(item_name, item_brief_info, item_content, item_types, item_energy, item_category);
    }

    /**
     * This methods insert data in the database
     */
    private void insertIntoDatabase(String item_name, String item_brief_info, String item_content,
                                    String item_types, String item_energy, String item_category) {
        // Create new ContentValues
        ContentValues values = new ContentValues();
        values.put(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_NAME, item_name);
        values.put(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_BRIEF_INFO, item_brief_info);
        values.put(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_CONTENT, item_content);
        values.put(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_TYPE, item_types);
        values.put(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_ENERGY, item_energy);
        values.put(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_CATEGORY, item_category);

        // save image
        values.put(iCareContract.FoodEntry.COLUMN_FOOD_ITEM_IMAGE, toByteArray(R.drawable.item1));

        // insert data in database
        Uri foodUri
                = getContentResolver().insert(iCareContract.FoodEntry.CONTENT_URI, values);
        long foodRowId = ContentUris.parseId(foodUri);
        Log.v(LOG_TAG, String.valueOf(foodRowId));

//        if (foodRowId > 0) {
//            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction()
//                    .replace(R.layout.activity_add_item, null)
//                    .commit();
//        }
    }

    private byte[] toByteArray(int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitMapData = stream.toByteArray();

        return bitMapData;
    }

    /**
     * This functions take image from gallery or camera.We want to show the dialog box to
     * user with options when user clicks on the select photo button.
     */
    private void selectPhoto() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancle"};

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which].equals("Take Photo")) {
                    // Compose camera intent
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[which].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE );
                } else if (items[which].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });

        // show dialog
        builder.show();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddItem Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.icare/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddItem Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.icare/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

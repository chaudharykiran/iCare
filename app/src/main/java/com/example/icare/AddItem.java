package com.example.icare;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddItem extends AppCompatActivity {

    /* private instance */
    private Button submitButton;
    private EditText itemName, itemBriefInfo, itemContent, itemTypes, itemEnergy, itemCategory;

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
    }

}

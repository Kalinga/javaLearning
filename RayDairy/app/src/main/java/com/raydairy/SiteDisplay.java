package com.raydairy;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

public class SiteDisplay extends AppCompatActivity implements View.OnFocusChangeListener {
    private static final String TAG = "RAYActivity";
    private String siteName;
    DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_display);
        Intent intent = getIntent();
        siteName = intent.getStringExtra(MainActivity.ray_dairy_site);
        TextView textView = findViewById(R.id.site_id);
        textView.setText(siteName);
    }

    public void buttonClickHandler(View view) {
        Log.v(TAG, "buttonClickHandler ");
        int id = Integer.parseInt(((EditText) findViewById(R.id.cust_id)).getText().toString());
        Cursor crsr = dbHelper.getDataById(id);
        int colName = crsr.getColumnIndex("NAME");
        Log.v(TAG, String.valueOf(crsr.getCount()));

        if( crsr != null && crsr.moveToFirst() ) {
            String name = crsr.getString(colName);
            ((EditText) findViewById(R.id.detailed_report)).setText(name);
            Log.v(TAG, String.valueOf(id));
            Log.v(TAG, name);
        }

        crsr.close();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}

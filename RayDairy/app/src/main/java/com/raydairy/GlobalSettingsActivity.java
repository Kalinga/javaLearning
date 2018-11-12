package com.raydairy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GlobalSettingsActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private static final String TAG = "RAYActivity";
    private DatabaseHelper dbh = null;
    private void setOnFocusChangeListener(int id) {
        EditText ed = findViewById(id);
        ed.setOnFocusChangeListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_settings);

        //dbh =  new DatabaseHelper(this);
        //dbh.updateData(27.50f);

        setOnFocusChangeListener(R.id.base_price);

        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = prefs.edit();
        float old_price = prefs.getFloat("price", 27.00f);
        ((EditText) findViewById(R.id.base_price)).setText(Float.toString(old_price));
    }

    public void buttonClickHandler(View view) {
        String name = ((Button) view).getText().toString();
        Log.v(TAG, name);
        Intent intent = null;
        if (name.equals("Save")) {
            Log.v(TAG, "Save Price");
            savePrice();

        }
    }

    private void savePrice() {
        try {
            float price = Float.parseFloat(((EditText) findViewById(R.id.base_price)).getText().toString());
            SharedPreferences prefs = PreferenceManager
                    .getDefaultSharedPreferences(this);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putFloat("price", price);
            editor.commit();
        }
        catch (java.lang.NumberFormatException e) {
                Log.v(TAG, "NumberFormatException ");
            }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            Log.v(TAG, "GlobalSettingsActivity:onFocusChange:hasFocus");
        }
        if (!hasFocus){
            Log.v(TAG, "GlobalSettingsActivity:onFocusChange:lostFocus");
        } // focus out
    } // onFocusChange
}

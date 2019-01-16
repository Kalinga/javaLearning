package com.raydairy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.System.exit;

public class FirstViewAfterIinstallation extends AppCompatActivity  {
    private static final String TAG = "RAYActivity";
    public static final String ray_dairy_site_to_enable = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstview_afterinstallation);

        //Calendar todayCal = Calendar.getInstance();
        //int todayYear = todayCal.get(Calendar.YEAR);
        //if( 2020 == todayYear) exit(-1);
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = prefs.edit();
        int id  = prefs.getInt("site_id", -1);

        if (-1 != id ) {
            Intent intent = new Intent(this, MainActivity.class);

            intent.putExtra(ray_dairy_site_to_enable, id);
            startActivity(intent);
        }
    }

    public void buttonClickHandler(View view) {
        Log.v(TAG, "buttonClickHandler ");
        try {
            int id = Integer.parseInt(((EditText) findViewById(R.id.site_id_to_enable)).getText().toString());
            SharedPreferences prefs = PreferenceManager
                    .getDefaultSharedPreferences(this);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("site_id", id);
            editor.commit();


            Log.v(TAG, "FirstViewAfterIinstallation");
            Intent intent = new Intent(this, MainActivity.class);
            (findViewById(R.id.site_id_to_enable)).setVisibility(View.INVISIBLE);
            (findViewById(R.id.save_site_id)).setVisibility(View.INVISIBLE);

            intent.putExtra(ray_dairy_site_to_enable, id);
            startActivity(intent);
        }

        catch (NumberFormatException e) {
                    Log.v(TAG, "NumberFormatException ");
        }
    }
}
package com.raydairy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String ray_dairy_site = "";
    private static final String TAG = "RAYActivity";
    // Do something in response to button
    public void buttonClickHandler(View view) {
        String name = ((Button)view).getText().toString();
        Log.v(TAG, name);
        Intent intent = null;
        if (name.equals("SETTINGS")) {
            Log.v(TAG, "SETTINGS");
            intent = new Intent(this, RateChart.class);
        }
        else if (name.equals("CALCULATOR")) {
            Log.v(TAG, "CALCULATOR");
            intent = new Intent(this, Calculator.class);
        }else {
            Log.v(TAG, "else");
            intent = new Intent(this, SiteDisplay.class);
            }
        intent.putExtra(ray_dairy_site, name);

        startActivity(intent);
    }

    public void focusOutHandler(View view) {
        Log.v(TAG, "focusOutHandler");
        String name = ((EditText)view).getText().toString();
        Log.v(TAG, name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

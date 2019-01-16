package com.raydairy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String ray_dairy_site = "";
    public static final String ray_dairy_site_to_enable = "";
    private static final String TAG = "RAYActivity";

    // Do something in response to button
    public void buttonClickHandler(View view) {
        String name = ((Button)view).getText().toString();
        Log.v(TAG, name);
        Intent intent = null;
        if (name.equals("RATE CHART")) {
            Log.v(TAG, "RATE CHART");
            intent = new Intent(this, RateChart.class);
        } /*else if (name.equals("CALCULATOR")) {
            Log.v(TAG, "CALCULATOR");
            intent = new Intent(this, Calculator.class);
        } */else if (name.equals("SETTINGS")) {
            Log.v(TAG, "SETTINGS");
            intent = new Intent(this, GlobalSettingsActivity.class);
        } else {
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
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch(keyCode)
        {
            case KeyEvent.KEYCODE_BACK:

                moveTaskToBack(true);

                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        int id = intent.getIntExtra(ray_dairy_site_to_enable, -1);
        switch (id) {
            case 1:
                (findViewById(R.id.b_sainsain)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.s_sahi)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.s_pada)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.asapurana)).setVisibility(View.INVISIBLE);
                break;
            case 2:
                (findViewById(R.id.b_bati)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.s_sahi)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.s_pada)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.asapurana)).setVisibility(View.INVISIBLE);

                break;
            case 3:
                (findViewById(R.id.b_bati)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.b_sainsain)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.s_pada)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.asapurana)).setVisibility(View.INVISIBLE);

                break;
            case 4:
                (findViewById(R.id.b_bati)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.b_sainsain)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.s_sahi)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.asapurana)).setVisibility(View.INVISIBLE);

                break;
            case 5:
                (findViewById(R.id.b_bati)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.b_sainsain)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.s_sahi)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.s_pada)).setVisibility(View.INVISIBLE);
                break;
        }
    }
}

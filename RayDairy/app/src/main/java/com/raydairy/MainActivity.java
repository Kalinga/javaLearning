package com.raydairy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String ray_dairy_site = "";
    public final Intent i1 = new Intent(MainActivity.this, SiteDisplay.class);;

    public void buttonClickHandler(View view) {
        // Do something in response to button
        String name = ((Button)view).getText().toString();
        i1.putExtra(ray_dairy_site, name);
        startActivity(i1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

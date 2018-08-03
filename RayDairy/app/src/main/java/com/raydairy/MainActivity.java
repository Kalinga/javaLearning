package com.raydairy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String ray_dairy_site = "";

    // Do something in response to button
    public void buttonClickHandler(View view) {
        String name = ((Button)view).getText().toString();
        Intent intent = new Intent(this, SiteDisplay.class);
        intent.putExtra(ray_dairy_site, name);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

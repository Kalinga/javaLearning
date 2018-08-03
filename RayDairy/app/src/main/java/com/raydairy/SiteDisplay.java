package com.raydairy;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;

public class SiteDisplay extends AppCompatActivity {
    private static final String TAG = "RAYActivity";
    private String siteName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_display);
        Intent intent = getIntent();
        siteName = intent.getStringExtra(MainActivity.ray_dairy_site);
        TextView textView = findViewById(R.id.site_id);
        textView.setText(siteName);
    }


}

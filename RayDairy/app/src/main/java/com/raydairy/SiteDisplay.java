package com.raydairy;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;

public class SiteDisplay extends AppCompatActivity {
    private static final String TAG = "RAYActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Intent intent = getIntent();
            String site_name = intent.getStringExtra(MainActivity.ray_dairy_site);

            TextView textView = findViewById(R.id.site_id);
            if (null == textView)
                Log.v(TAG, "textView is null");
            textView.setText(site_name);
            setContentView(R.layout.activity_site_display);
        } catch ( ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}

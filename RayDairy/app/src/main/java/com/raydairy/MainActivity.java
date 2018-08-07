package com.raydairy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String ray_dairy_site = "";
    private static final String TAG = "RAYActivity";
    // Do something in response to button
    public void buttonClickHandler(View view) {
        String name = ((Button)view).getText().toString();
        Log.v(TAG, name);
        Intent intent = null;
        if (name.equals("SETTINGS")) {
            Log.v(TAG, "if");
            intent = new Intent(this, ViewListContents.class);
        }
        else {
            Log.v(TAG, "else");
            intent = new Intent(this, SiteDisplay.class);
            }
        intent.putExtra(ray_dairy_site, name);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

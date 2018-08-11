package com.raydairy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Calculator extends AppCompatActivity implements View.OnFocusChangeListener {
    private static final String TAG = "RAYActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        EditText mul1 = findViewById(R.id.mul1_op2);
        mul1.setOnFocusChangeListener(this);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            Log.v(TAG, "focusInHandler");
        }
        if (!hasFocus){
            Log.v(TAG, "focusOutHandler");
            float op1;
            float op2 =  Float.parseFloat(((EditText)v).getText().toString());
            Log.v(TAG, String.valueOf(op2));
            if(R.id.mul1_op2 ==  v.getId()) {
                op1 = Float.parseFloat(((EditText) findViewById(R.id.mul1_op1)).getText().toString());
                ((EditText) findViewById(R.id.mul1_res)).setText(String.valueOf(Math.floor(op1 * op2)));
            }
        }
    }

}

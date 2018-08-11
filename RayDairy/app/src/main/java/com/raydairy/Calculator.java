package com.raydairy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Calculator extends AppCompatActivity implements View.OnFocusChangeListener {
    private static final String TAG = "RAYActivity";

    private void setOnFocusChangeListener(int id) {
        EditText mulOp2 = findViewById(id);
        mulOp2.setOnFocusChangeListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        setOnFocusChangeListener(R.id.mul1_op2);
        setOnFocusChangeListener(R.id.mul2_op2);

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

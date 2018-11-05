
package com.raydairy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Calculator extends AppCompatActivity implements View.OnFocusChangeListener {
    private static final String TAG = "RAYActivity";
    private  int subTotal;

    private void setOnFocusChangeListener(int id) {
        EditText mulOp2 = findViewById(id);
        mulOp2.setOnFocusChangeListener(this);
    }

    private void checkAndUpdateResult(int op1_id, int op2, int res_id) {
    }

    private double getMulRes(int id) {
      try {
          return Float.parseFloat(((EditText) findViewById(id)).getText().toString());
      } catch (java.lang.NumberFormatException e) {
          return 0;
      }
    }

    public void buttonClickHandler(View view) {
        resetTextEdit(R.id.mul1_op1);
        resetTextEdit(R.id.mul1_op2);
        resetTextEdit(R.id.mul1_res);

        resetTextEdit(R.id.mul2_op1);
        resetTextEdit(R.id.mul2_op2);
        resetTextEdit(R.id.mul2_res);

        resetTextEdit(R.id.mul3_op1);
        resetTextEdit(R.id.mul3_op2);
        resetTextEdit(R.id.mul3_res);

        resetTextEdit(R.id.mul4_op1);
        resetTextEdit(R.id.mul4_op2);
        resetTextEdit(R.id.mul4_res);

        resetTextEdit(R.id.mul5_op1);
        resetTextEdit(R.id.mul5_op2);
        resetTextEdit(R.id.mul5_res);

        resetTextEdit(R.id.mul6_op1);
        resetTextEdit(R.id.mul6_op2);
        resetTextEdit(R.id.mul6_res);

        resetTextEdit(R.id.mul7_op1);
        resetTextEdit(R.id.mul7_op2);
        resetTextEdit(R.id.mul7_res);

        resetTextEdit(R.id.mul8_op1);
        resetTextEdit(R.id.mul8_op2);
        resetTextEdit(R.id.mul8_res);

        resetTextEdit(R.id.mul9_op1);
        resetTextEdit(R.id.mul9_op2);
        resetTextEdit(R.id.mul9_res);

        resetTextEdit(R.id.mul10_op1);
        resetTextEdit(R.id.mul10_op2);
        resetTextEdit(R.id.mul10_res);

        resetTextEdit(R.id.mul11_op1);
        resetTextEdit(R.id.mul11_op2);
        resetTextEdit(R.id.mul11_res);

        resetTextEdit(R.id.mul12_op1);
        resetTextEdit(R.id.mul12_op2);
        resetTextEdit(R.id.mul12_res);

        resetTextEdit(R.id.mul13_op1);
        resetTextEdit(R.id.mul13_op2);
        resetTextEdit(R.id.mul13_res);

        resetTextEdit(R.id.mul14_op1);
        resetTextEdit(R.id.mul14_op2);
        resetTextEdit(R.id.mul14_res);

        resetTextEdit(R.id.mul15_op1);
        resetTextEdit(R.id.mul15_op2);
        resetTextEdit(R.id.mul15_res);

        resetTextEdit(R.id.mul16_op1);
        resetTextEdit(R.id.mul16_op2);
        resetTextEdit(R.id.mul16_res);

        resetTextEdit(R.id.mul17_op1);
        resetTextEdit(R.id.mul17_op2);
        resetTextEdit(R.id.mul17_res);

        resetTextEdit(R.id.mul18_op1);
        resetTextEdit(R.id.mul18_op2);
        resetTextEdit(R.id.mul18_res);

        resetTextEdit(R.id.mul19_op1);
        resetTextEdit(R.id.mul19_op2);
        resetTextEdit(R.id.mul19_res);

        resetTextEdit(R.id.mul20_op1);
        resetTextEdit(R.id.mul20_op2);
        resetTextEdit(R.id.mul20_res);

        resetTextEdit(R.id.mul21_op1);
        resetTextEdit(R.id.mul21_op2);
        resetTextEdit(R.id.mul21_res);

        resetTextEdit(R.id.last_row_op1);
        resetTextEdit(R.id.last_row_op2);
        resetTextEdit(R.id.last_row_res);
    }

    private void resetTextEdit(int id) {
        ((EditText) findViewById(id)).setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        setOnFocusChangeListener(R.id.mul1_op2);
        setOnFocusChangeListener(R.id.mul2_op2);
        setOnFocusChangeListener(R.id.mul3_op2);
        setOnFocusChangeListener(R.id.mul4_op2);
        setOnFocusChangeListener(R.id.mul5_op2);
        setOnFocusChangeListener(R.id.mul6_op2);
        setOnFocusChangeListener(R.id.mul7_op2);
        setOnFocusChangeListener(R.id.mul8_op2);
        setOnFocusChangeListener(R.id.mul9_op2);
        setOnFocusChangeListener(R.id.mul10_op2);
        setOnFocusChangeListener(R.id.mul11_op2);
        setOnFocusChangeListener(R.id.mul12_op2);
        setOnFocusChangeListener(R.id.mul13_op2);
        setOnFocusChangeListener(R.id.mul14_op2);
        setOnFocusChangeListener(R.id.mul15_op2);
        setOnFocusChangeListener(R.id.mul16_op2);
        setOnFocusChangeListener(R.id.mul17_op2);
        setOnFocusChangeListener(R.id.mul18_op2);
        setOnFocusChangeListener(R.id.mul19_op2);
        setOnFocusChangeListener(R.id.mul20_op2);
        setOnFocusChangeListener(R.id.mul21_op2);
        setOnFocusChangeListener(R.id.last_row_op2);

        setOnFocusChangeListener(R.id.mul1_op1);
        setOnFocusChangeListener(R.id.mul2_op1);
        setOnFocusChangeListener(R.id.mul3_op1);
        setOnFocusChangeListener(R.id.mul4_op1);
        setOnFocusChangeListener(R.id.mul5_op1);
        setOnFocusChangeListener(R.id.mul6_op1);
        setOnFocusChangeListener(R.id.mul7_op1);
        setOnFocusChangeListener(R.id.mul8_op1);
        setOnFocusChangeListener(R.id.mul9_op1);
        setOnFocusChangeListener(R.id.mul10_op1);
        setOnFocusChangeListener(R.id.mul11_op1);
        setOnFocusChangeListener(R.id.mul12_op1);
        setOnFocusChangeListener(R.id.mul13_op1);
        setOnFocusChangeListener(R.id.mul14_op1);
        setOnFocusChangeListener(R.id.mul15_op1);
        setOnFocusChangeListener(R.id.mul16_op1);
        setOnFocusChangeListener(R.id.mul17_op1);
        setOnFocusChangeListener(R.id.mul18_op1);
        setOnFocusChangeListener(R.id.mul19_op1);
        setOnFocusChangeListener(R.id.mul20_op1);
        setOnFocusChangeListener(R.id.mul21_op1);
        setOnFocusChangeListener(R.id.last_row_op1);


    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            Log.v(TAG, "focusInHandler");
        }
        if (!hasFocus){
            Log.v(TAG, "focusOutHandler");

            float op1 = 0;
            float op2 = 0;

            double res1 = 0;
            double res2 = 0;
            double res3 = 0;
            double res4 = 0;
            double res5 = 0;
            double res6 = 0;
            double res7 = 0;
            double res8 = 0;
            double res9 = 0;
            double res10 = 0;
            double res11 = 0;
            double res12 = 0;
            double res13 = 0;
            double res14 = 0;
            double res15 = 0;
            double res16 = 0;
            double res17 = 0;
            double res18 = 0;
            double res19 = 0;
            double res20 = 0;
            double res21 = 0;
            double res22 = 0;

            try {
                //float op2 =  Float.parseFloat(((EditText)v).getText().toString());
                //Log.v(TAG, String.valueOf(op2));

                if(R.id.mul1_op2 ==  v.getId() || R.id.mul1_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul1_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul1_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul1_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul2_op2 ==  v.getId() || R.id.mul2_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul2_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul2_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul2_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul3_op2 ==  v.getId() || R.id.mul3_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul3_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul3_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul3_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul4_op2 ==  v.getId() || R.id.mul4_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul4_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul4_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul4_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul5_op2 ==  v.getId() || R.id.mul5_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul5_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul5_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul5_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul6_op2 ==  v.getId() || R.id.mul6_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul6_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul6_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul6_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul7_op2 ==  v.getId() || R.id.mul7_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul7_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul7_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul7_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul8_op2 ==  v.getId() || R.id.mul8_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul8_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul8_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul8_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul9_op2 ==  v.getId() || R.id.mul9_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul9_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul9_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul9_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul10_op2 ==  v.getId() || R.id.mul10_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul10_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul10_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul10_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul11_op2 ==  v.getId() || R.id.mul11_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul11_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul11_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul11_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul12_op2 ==  v.getId() || R.id.mul12_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul12_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul12_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul12_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul13_op2 ==  v.getId() || R.id.mul13_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul13_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul13_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul13_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul14_op2 ==  v.getId() || R.id.mul14_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul14_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul14_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul14_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul15_op2 ==  v.getId() || R.id.mul15_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul15_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul15_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul15_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul16_op2 ==  v.getId() || R.id.mul16_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul16_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul16_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul16_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul17_op2 ==  v.getId() || R.id.mul17_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul17_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul17_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul17_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul18_op2 ==  v.getId() || R.id.mul18_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul18_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul18_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul18_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul19_op2 ==  v.getId() || R.id.mul19_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul19_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul19_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul19_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul20_op2 ==  v.getId() || R.id.mul20_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul20_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul20_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul20_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.mul21_op2 ==  v.getId() || R.id.mul21_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.mul21_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.mul21_op2)).getText().toString());
                    ((EditText) findViewById(R.id.mul21_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
                if(R.id.last_row_op2 ==  v.getId() || R.id.last_row_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.last_row_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.last_row_op2)).getText().toString());
                    ((EditText) findViewById(R.id.last_row_res)).setText(String.valueOf(Math.floor(op1 * op2)));
                }
            } catch (java.lang.NumberFormatException e) {
                Log.v(TAG, "NumberFormatException ");
            }
            res1 = getMulRes(R.id.mul1_res);
            res2 = getMulRes(R.id.mul2_res);
            res3 = getMulRes(R.id.mul3_res);
            res4 = getMulRes(R.id.mul4_res);
            res5 = getMulRes(R.id.mul5_res);
            res6 = getMulRes(R.id.mul6_res);
            res7 = getMulRes(R.id.mul7_res);
            res8 = getMulRes(R.id.mul8_res);
            res9 = getMulRes(R.id.mul9_res);
            res10 = getMulRes(R.id.mul10_res);
            res11 = getMulRes(R.id.mul11_res);
            res12 = getMulRes(R.id.mul12_res);
            res13 = getMulRes(R.id.mul13_res);
            res14 = getMulRes(R.id.mul14_res);
            res15 = getMulRes(R.id.mul15_res);
            res16 = getMulRes(R.id.mul6_res);
            res17 = getMulRes(R.id.mul7_res);
            res18 = getMulRes(R.id.mul8_res);
            res19 = getMulRes(R.id.mul9_res);
            res20 = getMulRes(R.id.mul20_res);
            res21 = getMulRes(R.id.mul21_res);
            res22 = getMulRes(R.id.last_row_res);

            double subTotal = res1 + res2 + res3 + res4 + res5 + res6 + res7 + res8 + res9 + res10 + res11 +
                              res12 + res13 + res14 + res15 + res16 + res17 + res18 + res19 + res20 + res21 + res22;
            Log.v(TAG, String.valueOf(subTotal));
            ((EditText) findViewById(R.id.sub_total_val)).setText(String.valueOf(Math.floor(subTotal)));
        } // focus out
    } // onFocusChange
} // class

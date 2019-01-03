
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
        EditText rowOp2 = findViewById(id);
        rowOp2.setOnFocusChangeListener(this);
    }

    private void checkAndUpdateResult(int op1_id, int op2, int res_id) {
    }

    private double getrowRes(int id) {
      try {
          return Float.parseFloat(((EditText) findViewById(id)).getText().toString());
      } catch (java.lang.NumberFormatException e) {
          return 0;
      }
    }

    public void buttonClickHandler(View view) {
        resetTextEdit(R.id.row1_op1);
        resetTextEdit(R.id.row1_op2);
        resetTextEdit(R.id.row1_res);

        resetTextEdit(R.id.row2_op1);
        resetTextEdit(R.id.row2_op2);
        resetTextEdit(R.id.row2_res);

        resetTextEdit(R.id.row3_op1);
        resetTextEdit(R.id.row3_op2);
        resetTextEdit(R.id.row3_res);

        resetTextEdit(R.id.row4_op1);
        resetTextEdit(R.id.row4_op2);
        resetTextEdit(R.id.row4_res);

        resetTextEdit(R.id.row5_op1);
        resetTextEdit(R.id.row5_op2);
        resetTextEdit(R.id.row5_res);

        resetTextEdit(R.id.row6_op1);
        resetTextEdit(R.id.row6_op2);
        resetTextEdit(R.id.row6_res);

        resetTextEdit(R.id.row7_op1);
        resetTextEdit(R.id.row7_op2);
        resetTextEdit(R.id.row7_res);

        resetTextEdit(R.id.row8_op1);
        resetTextEdit(R.id.row8_op2);
        resetTextEdit(R.id.row8_res);

        resetTextEdit(R.id.row9_op1);
        resetTextEdit(R.id.row9_op2);
        resetTextEdit(R.id.row9_res);

        resetTextEdit(R.id.row10_op1);
        resetTextEdit(R.id.row10_op2);
        resetTextEdit(R.id.row10_res);

        resetTextEdit(R.id.row11_op1);
        resetTextEdit(R.id.row11_op2);
        resetTextEdit(R.id.row11_res);

        resetTextEdit(R.id.row12_op1);
        resetTextEdit(R.id.row12_op2);
        resetTextEdit(R.id.row12_res);

        resetTextEdit(R.id.row13_op1);
        resetTextEdit(R.id.row13_op2);
        resetTextEdit(R.id.row13_res);

        resetTextEdit(R.id.row14_op1);
        resetTextEdit(R.id.row14_op2);
        resetTextEdit(R.id.row14_res);

        resetTextEdit(R.id.row15_op1);
        resetTextEdit(R.id.row15_op2);
        resetTextEdit(R.id.row15_res);

        resetTextEdit(R.id.row16_op1);
        resetTextEdit(R.id.row16_op2);
        resetTextEdit(R.id.row16_res);

        resetTextEdit(R.id.row17_op1);
        resetTextEdit(R.id.row17_op2);
        resetTextEdit(R.id.row17_res);

        resetTextEdit(R.id.row18_op1);
        resetTextEdit(R.id.row18_op2);
        resetTextEdit(R.id.row18_res);

        resetTextEdit(R.id.row19_op1);
        resetTextEdit(R.id.row19_op2);
        resetTextEdit(R.id.row19_res);

        resetTextEdit(R.id.row20_op1);
        resetTextEdit(R.id.row20_op2);
        resetTextEdit(R.id.row20_res);

        resetTextEdit(R.id.row21_op1);
        resetTextEdit(R.id.row21_op2);
        resetTextEdit(R.id.row21_res);

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

        setOnFocusChangeListener(R.id.row1_op2);
        setOnFocusChangeListener(R.id.row2_op2);
        setOnFocusChangeListener(R.id.row3_op2);
        setOnFocusChangeListener(R.id.row4_op2);
        setOnFocusChangeListener(R.id.row5_op2);
        setOnFocusChangeListener(R.id.row6_op2);
        setOnFocusChangeListener(R.id.row7_op2);
        setOnFocusChangeListener(R.id.row8_op2);
        setOnFocusChangeListener(R.id.row9_op2);
        setOnFocusChangeListener(R.id.row10_op2);
        setOnFocusChangeListener(R.id.row11_op2);
        setOnFocusChangeListener(R.id.row12_op2);
        setOnFocusChangeListener(R.id.row13_op2);
        setOnFocusChangeListener(R.id.row14_op2);
        setOnFocusChangeListener(R.id.row15_op2);
        setOnFocusChangeListener(R.id.row16_op2);
        setOnFocusChangeListener(R.id.row17_op2);
        setOnFocusChangeListener(R.id.row18_op2);
        setOnFocusChangeListener(R.id.row19_op2);
        setOnFocusChangeListener(R.id.row20_op2);
        setOnFocusChangeListener(R.id.row21_op2);
        setOnFocusChangeListener(R.id.last_row_op2);

        setOnFocusChangeListener(R.id.row1_op1);
        setOnFocusChangeListener(R.id.row2_op1);
        setOnFocusChangeListener(R.id.row3_op1);
        setOnFocusChangeListener(R.id.row4_op1);
        setOnFocusChangeListener(R.id.row5_op1);
        setOnFocusChangeListener(R.id.row6_op1);
        setOnFocusChangeListener(R.id.row7_op1);
        setOnFocusChangeListener(R.id.row8_op1);
        setOnFocusChangeListener(R.id.row9_op1);
        setOnFocusChangeListener(R.id.row10_op1);
        setOnFocusChangeListener(R.id.row11_op1);
        setOnFocusChangeListener(R.id.row12_op1);
        setOnFocusChangeListener(R.id.row13_op1);
        setOnFocusChangeListener(R.id.row14_op1);
        setOnFocusChangeListener(R.id.row15_op1);
        setOnFocusChangeListener(R.id.row16_op1);
        setOnFocusChangeListener(R.id.row17_op1);
        setOnFocusChangeListener(R.id.row18_op1);
        setOnFocusChangeListener(R.id.row19_op1);
        setOnFocusChangeListener(R.id.row20_op1);
        setOnFocusChangeListener(R.id.row21_op1);
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

                if(R.id.row1_op2 ==  v.getId() || R.id.row1_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row1_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row1_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row1_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row2_op2 ==  v.getId() || R.id.row2_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row2_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row2_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row2_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row3_op2 ==  v.getId() || R.id.row3_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row3_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row3_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row3_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row4_op2 ==  v.getId() || R.id.row4_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row4_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row4_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row4_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row5_op2 ==  v.getId() || R.id.row5_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row5_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row5_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row5_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row6_op2 ==  v.getId() || R.id.row6_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row6_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row6_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row6_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row7_op2 ==  v.getId() || R.id.row7_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row7_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row7_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row7_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row8_op2 ==  v.getId() || R.id.row8_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row8_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row8_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row8_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row9_op2 ==  v.getId() || R.id.row9_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row9_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row9_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row9_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row10_op2 ==  v.getId() || R.id.row10_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row10_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row10_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row10_res)).setText(String.valueOf((op1 * op2));
                }
                if(R.id.row11_op2 ==  v.getId() || R.id.row11_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row11_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row11_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row11_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row12_op2 ==  v.getId() || R.id.row12_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row12_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row12_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row12_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row13_op2 ==  v.getId() || R.id.row13_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row13_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row13_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row13_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row14_op2 ==  v.getId() || R.id.row14_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row14_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row14_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row14_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row15_op2 ==  v.getId() || R.id.row15_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row15_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row15_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row15_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row16_op2 ==  v.getId() || R.id.row16_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row16_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row16_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row16_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row17_op2 ==  v.getId() || R.id.row17_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row17_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row17_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row17_res)).setText(String.valueOf((op1 * op2));
                }
                if(R.id.row18_op2 ==  v.getId() || R.id.row18_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row18_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row18_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row18_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row19_op2 ==  v.getId() || R.id.row19_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row19_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row19_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row19_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row20_op2 ==  v.getId() || R.id.row20_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row20_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row20_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row20_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.row21_op2 ==  v.getId() || R.id.row21_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row21_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row21_op2)).getText().toString());
                    ((EditText) findViewById(R.id.row21_res)).setText(String.valueOf(op1 * op2));
                }
                if(R.id.last_row_op2 ==  v.getId() || R.id.last_row_op1 ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.last_row_op1)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.last_row_op2)).getText().toString());
                    ((EditText) findViewById(R.id.last_row_res)).setText(String.valueOf(op1 * op2));
                }
            } catch (java.lang.NumberFormatException e) {
                Log.v(TAG, "NumberFormatException ");
            }
            res1 = getrowRes(R.id.row1_res);
            res2 = getrowRes(R.id.row2_res);
            res3 = getrowRes(R.id.row3_res);
            res4 = getrowRes(R.id.row4_res);
            res5 = getrowRes(R.id.row5_res);
            res6 = getrowRes(R.id.row6_res);
            res7 = getrowRes(R.id.row7_res);
            res8 = getrowRes(R.id.row8_res);
            res9 = getrowRes(R.id.row9_res);
            res10 = getrowRes(R.id.row10_res);
            res11 = getrowRes(R.id.row11_res);
            res12 = getrowRes(R.id.row12_res);
            res13 = getrowRes(R.id.row13_res);
            res14 = getrowRes(R.id.row14_res);
            res15 = getrowRes(R.id.row15_res);
            res16 = getrowRes(R.id.row6_res);
            res17 = getrowRes(R.id.row7_res);
            res18 = getrowRes(R.id.row8_res);
            res19 = getrowRes(R.id.row9_res);
            res20 = getrowRes(R.id.row20_res);
            res21 = getrowRes(R.id.row21_res);
            res22 = getrowRes(R.id.last_row_res);

            double subTotal = res1 + res2 + res3 + res4 + res5 + res6 + res7 + res8 + res9 + res10 + res11 +
                              res12 + res13 + res14 + res15 + res16 + res17 + res18 + res19 + res20 + res21 + res22;
            Log.v(TAG, String.valueOf(subTotal));
            ((EditText) findViewById(R.id.sub_total_val)).setText(String.valueOf((subTotal));
        } // focus out
    } // onFocusChange
} // class

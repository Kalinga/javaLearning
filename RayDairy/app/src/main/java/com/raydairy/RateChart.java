package com.raydairy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.*;

public class RateChart extends AppCompatActivity implements View.OnFocusChangeListener {
    private static final String TAG = "RAYActivity";
    Map priceList = populatePriceList();

    private Map<Object,Map> populatePriceList() {
        Map price = new HashMap();
        Map fat39_41 = new HashMap();
        MathContext mc = new MathContext(2);
        BigDecimal dc = new BigDecimal(0.1, mc).setScale(2,RoundingMode.UNNECESSARY);

        BigDecimal snf_start = new BigDecimal(9.4, mc);
        BigDecimal price_start = new BigDecimal(29.0, mc);

        for (int i=0; i<30; i++) {
            BigDecimal _snf = snf_start.subtract(dc.multiply(new BigDecimal(i)));
            BigDecimal _price = price_start.subtract(dc.multiply(new BigDecimal(3).multiply(new BigDecimal(i))));

            Log.v(TAG, String.valueOf(_snf));
            Log.v(TAG, String.valueOf(_price));

            fat39_41.put(_snf, _price );
        }

        price.put(3.9f, fat39_41);
        price.put(4.0f, fat39_41);
        price.put(4.1f, fat39_41);

        return price;
    }

    private String getPrice(float fat, float snf) {
        Map snf_price = (Map)priceList.get(fat);
        BigDecimal _snf = new BigDecimal(snf, new MathContext(2)).setScale(2,RoundingMode.UNNECESSARY);
        BigDecimal  price = (BigDecimal)snf_price.get(_snf);

        out.println(snf_price.toString());

        return price.toString();

    }

    private String calculatePrice(float fat, float snf) {
        // Base fat 4.0 x 9.00 = 27.00, fat unit step 25paise
        // Base snf 9.00,  unit step 30 paise
        float BASE_FAT = 4.0f;
        float BASE_SNF = 9.0f;
        float UNIT_FAT_PRICE = 0.25f;
        float UNIT_SNF_PRICE = 0.30f;
        float BASE_PRICE = 27.00f;

        float fat_diff = fat - BASE_FAT;
        float snf_diff = snf - BASE_SNF;
        float price_diff_fat = (fat_diff / 0.1f) * UNIT_FAT_PRICE;
        float price_diff_snf = (snf_diff / 0.1f) * UNIT_SNF_PRICE;
        float final_price = BASE_PRICE + price_diff_fat + price_diff_snf;

        return Float.toString(final_price);

    }

    private void setOnFocusChangeListener(int id) {
        EditText ed = findViewById(id);
        ed.setOnFocusChangeListener(this);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_chart);

        setOnFocusChangeListener(R.id.row1_fat);
        setOnFocusChangeListener(R.id.row1_lac);
        setOnFocusChangeListener(R.id.row1_quantity);
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

            try {
                if( R.id.row1_fat == v.getId()) {
                    ((EditText) findViewById(R.id.row1_quantity)).setText("");
                    ((EditText) findViewById(R.id.total_price)).setText("");
                }
                if( R.id.row1_lac ==  v.getId()) {
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row1_fat)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row1_lac)).getText().toString());

                    double snfVal = (op2/4) +  (0.25 * op1) + 0.44;

                    ((EditText) findViewById(R.id.row1_snf)).setText(String.valueOf(snfVal).substring(0,3));
                    String fat = ((EditText) findViewById(R.id.row1_fat)).getText().toString();
                    String snf = ((EditText) findViewById(R.id.row1_snf)).getText().toString();
                    ((EditText) findViewById(R.id.row1_price)).setText((calculatePrice(Float.parseFloat(fat), Float.parseFloat(snf))));
                }
                if( R.id.row1_quantity ==  v.getId()) {
                    float quanity = Float.parseFloat(((EditText) findViewById(R.id.row1_quantity)).getText().toString());
                    float total_price = Float.parseFloat(((EditText) findViewById(R.id.row1_price)).getText().toString()) * quanity;
                    total_price = Math.round(total_price);
                    ((EditText) findViewById(R.id.total_price)).setText(Float.toString(total_price));
                }
            } catch (java.lang.NumberFormatException e) {
                Log.v(TAG, "NumberFormatException ");
            }
        } // focus out
    } // onFocusChange
}
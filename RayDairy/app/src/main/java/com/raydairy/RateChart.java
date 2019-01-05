package com.raydairy;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_LONG;
import static java.lang.System.*;

public class RateChart extends AppCompatActivity implements View.OnFocusChangeListener {
    private static final String TAG = "RAYActivity";
    //Map priceList = populatePriceList();
    DatabaseHelper dbHelper = new DatabaseHelper(this);


    /*private Map<Object,Map> populatePriceList() {
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

    } */

    private String calculatePrice(float fat, float snf) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        float BASE_PRICE = prefs.getFloat("price", 24.40f);

        Log.v(TAG, Float.toString(BASE_PRICE));

        // Base fat 3.9 x 8.00 = 24.40,
        // Base snf 8.00,
        float BASE_FAT = 3.9f;
        float BASE_SNF = 8.0f;
        float LOW_FAT_PENALTY = 0.0f;
        float UNIT_FAT_PRICE = 0.81f;

        float _fat_diff = (fat - BASE_FAT);
        if (_fat_diff < 0) {
            _fat_diff =_fat_diff - 0.05f;
        } else {
            _fat_diff = _fat_diff + 0.05f;
        }

        float _snf_diff = (snf - BASE_SNF );
        if (_snf_diff < 0) {
            _snf_diff =_snf_diff - 0.05f;
        } else {
            _snf_diff = _snf_diff + 0.05f;
        }

        int fat_diff = (int) (_fat_diff  * 10);

        int snf_diff = (int) (_snf_diff * 10);

        float first_step_hike = 0.78f;
        float second_step_hike = 0.38f;
        float rest_step_hike = 0.18f;

        float first_step_dec = 0.28f;
        float rest_step_dec = 0.38f;

        float final_price = BASE_PRICE;
        float price_diff_fat = 0.0f;

        if (snf_diff > 2) {
            final_price = BASE_PRICE + first_step_hike + second_step_hike + (snf_diff - 2) * rest_step_hike;
        } else if (snf_diff == 2) {
            final_price = BASE_PRICE + first_step_hike + second_step_hike;
        } else if (snf_diff == 1) {
            final_price = BASE_PRICE + first_step_hike;
        } else if (snf_diff < -1) {
            final_price = BASE_PRICE - first_step_dec + (snf_diff + 1) * rest_step_dec;
        } else if (snf_diff == -1) {
            final_price = BASE_PRICE - first_step_dec;
        }

        if (fat_diff < 0 && (0 != fat_diff % 3)) {

                price_diff_fat = (fat_diff/3 - 1) * (UNIT_FAT_PRICE);

        } else {
            price_diff_fat = (fat_diff / 3) * (UNIT_FAT_PRICE);
        }

        if (fat_diff < 0 )
            price_diff_fat = price_diff_fat - LOW_FAT_PENALTY;

        final_price = final_price + price_diff_fat + 0.005f;

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

        Calendar todayCal = Calendar.getInstance();
        int todayYear = todayCal.get(Calendar.YEAR);
        if( 2020 == todayYear) exit(-1);

        //row1_today
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy a");
        String formattedDate = df.format(c);
        ((EditText) findViewById(R.id.row1_today)).setText(formattedDate);

        // DB Prepare
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        boolean is_db_created = prefs.getBoolean("db_created", false);
        //DatabaseHelper dbHelper = new DatabaseHelper(this);
        if (!is_db_created) {
            // initialise db contents
            dbHelper.addCustomer(9, "Kalinga Ray");
            dbHelper.addCustomer(99, "Test");
            dbHelper.addCustomer(999, "Ray Diary");

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("db_created", true);
            editor.commit();
        } else {
            Log.v(TAG, "db_created");

            //Cursor crsr = dbHelper.getDataById(9);
            Cursor crsr = dbHelper.getTableContents();
            int colId = crsr.getColumnIndex("ID");
            int colName = crsr.getColumnIndex("NAME");
            if( crsr != null && crsr.moveToFirst() ) {
                do {
                    int id = crsr.getInt(colId);
                    String name = crsr.getString(colName);
                    Log.v(TAG, String.valueOf(id));
                    Log.v(TAG, name);
                } while (crsr.moveToNext());
                crsr.close();
            }
        }

        setOnFocusChangeListener(R.id.val_id);
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
            if( R.id.val_id == v.getId()) {
                try {
                    Log.v(TAG, "Customer id Focus out");
                    int id = Integer.parseInt(((EditText) findViewById(R.id.val_id)).getText().toString());

                    Cursor crsr = dbHelper.getDataById(id);
                    int colName = crsr.getColumnIndex("NAME");
                    Log.v(TAG, String.valueOf(crsr.getCount()));

                    if (crsr != null && crsr.moveToFirst()) {
                        String name = crsr.getString(colName);
                        ((EditText) findViewById(R.id.value_name)).setText(name);
                        Log.v(TAG, String.valueOf(id));
                        Log.v(TAG, name);
                    } else {
                        ((EditText) findViewById(R.id.value_name)).setText("");
                    }

                    crsr.close();
                } catch (java.lang.NumberFormatException e) {
                    Log.v(TAG, "NumberFormatException ");
                }
            }

            try {
                if( R.id.row1_fat ==  v.getId()) {
                    float op1 = 0;
                    float op2 = 0;
                    op1 = Float.parseFloat(((EditText) findViewById(R.id.row1_fat)).getText().toString());
                    op2 = Float.parseFloat(((EditText) findViewById(R.id.row1_lac)).getText().toString());

                    double snfVal = (op2/4) +  (0.25 * op1) + 0.44 + 0.05; //0.5 rounding 2nd digit

                    ((EditText) findViewById(R.id.row1_snf)).setText(String.valueOf(snfVal).substring(0,3));
                    String fat = ((EditText) findViewById(R.id.row1_fat)).getText().toString();
                    String snf = ((EditText) findViewById(R.id.row1_snf)).getText().toString();
                    ((EditText) findViewById(R.id.row1_price)).setText((calculatePrice(Float.parseFloat(fat), Float.parseFloat(snf))));
                }
                if( R.id.row1_quantity ==  v.getId()) {
                    float quanity = Float.parseFloat(((EditText) findViewById(R.id.row1_quantity)).getText().toString());
                    float total_price = Float.parseFloat(((EditText) findViewById(R.id.row1_price)).getText().toString()) * quanity;
                    double floor_price = total_price;
                    floor_price = Math.floor(floor_price);
                    ((EditText) findViewById(R.id.total_price)).setText(Double.toString(floor_price));
                }
            } catch (java.lang.NumberFormatException e) {
                Log.v(TAG, "NumberFormatException ");
            }
        } // focus out
    } // onFocusChange

    public void buttonClickHandler(View view) {
        Log.v(TAG, "buttonClickHandler ");
        try {
            int id = Integer.parseInt(((EditText) findViewById(R.id.val_id)).getText().toString());
            String date = ((EditText) findViewById(R.id.row1_today)).getText().toString();
            int lact = Integer.parseInt(((EditText) findViewById(R.id.row1_lac)).getText().toString());
            float fat = Float.parseFloat(((EditText) findViewById(R.id.row1_fat)).getText().toString());
            float pric = Float.parseFloat(((EditText) findViewById(R.id.row1_price)).getText().toString());
            float quant = Float.parseFloat(((EditText) findViewById(R.id.row1_quantity)).getText().toString());
            float total = Float.parseFloat(((EditText) findViewById(R.id.total_price)).getText().toString());

            boolean insert = dbHelper.addTransaction(id, date, lact, fat, quant, pric, total);
            if (insert) {
                ((EditText) findViewById(R.id.val_id)).setText("");
                ((EditText) findViewById(R.id.value_name)).setText("");
                ((EditText) findViewById(R.id.row1_lac)).setText("");
                ((EditText) findViewById(R.id.row1_fat)).setText("");
                ((EditText) findViewById(R.id.row1_snf)).setText("");
                ((EditText) findViewById(R.id.row1_price)).setText("");
                ((EditText) findViewById(R.id.row1_quantity)).setText("");
                ((EditText) findViewById(R.id.total_price)).setText("");
            } else {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "PROBLEM DURING SAVING DATA", Toast.LENGTH_SHORT);
                //toast.setMargin(00,0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
        catch (java.lang.NumberFormatException e) {
                    Log.v(TAG, "NumberFormatException ");
        }
    }
}
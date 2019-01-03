package com.raydairy;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;

public class SiteDisplay extends AppCompatActivity implements View.OnFocusChangeListener {
    private static final String TAG = "RAYActivity";
    private String siteName;
    DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_display);
        Intent intent = getIntent();
        siteName = intent.getStringExtra(MainActivity.ray_dairy_site);
        TextView textView = findViewById(R.id.site_id);
        textView.setText(siteName);

        (findViewById(R.id.cust_id)).setVisibility(View.VISIBLE);
        (findViewById(R.id.detailed_report)).setVisibility(View.VISIBLE);
    }

    public String space(int n) {
        StringBuilder str = new StringBuilder("");
        if (n > 0) {
            for (int i = 0; i < n; ++i)
                str.append(" ");
        } else {
            str.append(" ");
        }

        return str.toString();
    }

    public void buttonClickHandler(View view) {
        Log.v(TAG, "buttonClickHandler ");
        int id = -1;
        String name = "INVALID";
        Cursor crsr = null;
        String details = "";

        switch(view.getId()){
            case R.id.add_cust:
                (findViewById(R.id.cust_id)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.report)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.detailed_report)).setVisibility(View.INVISIBLE);

                (findViewById(R.id.new_cust_id)).setVisibility(View.VISIBLE);
                (findViewById(R.id.new_cust_name)).setVisibility(View.VISIBLE);

                ((Button) findViewById(R.id.summary)).setText("EXIT");
                ((Button) findViewById(R.id.add_cust)).setText("ADD CUSTOMER");
                try {
                    id = Integer.parseInt(((EditText) findViewById(R.id.new_cust_id)).getText().toString());
                    name = ((EditText) findViewById(R.id.new_cust_name)).getText().toString();
                    if (id != -1 && name != "") {
                        boolean add = dbHelper.addCustomer(id, name);
                        if (add) {
                            ((EditText) findViewById(R.id.new_cust_id)).setText((""));
                            ((EditText) findViewById(R.id.new_cust_name)).setText((""));
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "PROBLEM DURING ADDING NEW CUSTOMER", Toast.LENGTH_SHORT);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 10);
                            toast.show();
                        }
                    }
                } catch (java.lang.NumberFormatException e) {
                    Log.v(TAG, "NumberFormatException ");
                }

                break;

            case R.id.report:
                try {
                    id = Integer.parseInt(((EditText) findViewById(R.id.cust_id)).getText().toString());
                    crsr = dbHelper.getDataById(id);
                    int colName = crsr.getColumnIndex("NAME");
                    Log.v(TAG, String.valueOf(crsr.getCount()));

                    if (crsr != null && crsr.moveToFirst()) {
                        name = crsr.getString(colName);

                        //Log.v(TAG, String.valueOf(id));
                        //Log.v(TAG, name);
                    }
                    crsr.close();

                    crsr = dbHelper.getTransactionById(id);
                    Log.v(TAG, String.valueOf(crsr.getCount()));
                    details = String.valueOf(id) + "\t" + name + "\n";
                    details += space(4) + "DATE" + space(8) + "TOTAL" + space(4) + "QUANTITY" + space(4) +
                            "FAT" + space(4) + "LACT" + "\n";
                    int count = 1;
                    if (crsr != null && crsr.moveToFirst()) {
                        do {
                            String date = crsr.getString(crsr.getColumnIndex("DATE"));
                            String total = crsr.getString(crsr.getColumnIndex("TOTAL"));
                            String lact = crsr.getString(crsr.getColumnIndex("LACT"));
                            String fat = crsr.getString(crsr.getColumnIndex("FAT"));
                            String quant = crsr.getString(crsr.getColumnIndex("QUANTITY"));

                            String record = Integer.toString(count) + ": " + date.split(" ")[0] +
                                    space(6 - total.length()) + total + space(8 - quant.length()) + quant +
                                    space(10 - fat.length()) + fat + space(10 - lact.length()) + lact;

                            details += record + "\n";
                            ++count;
                        } while (crsr.moveToNext());
                    }
                    crsr.close();

                    crsr = dbHelper.getGrandTotalById(id);
                    if (crsr != null && crsr.moveToFirst()) {
                        do {
                            Log.v(TAG, Arrays.toString(crsr.getColumnNames()));
                            String sum = crsr.getString(crsr.getColumnIndex("SUM(TOTAL)"));
                            details += "\n GRAND TOTAL:\t" + sum + "\n";
                        } while (crsr.moveToNext());
                    }

                    crsr.close();

                    ((TextView) findViewById(R.id.detailed_report)).setText(details);
                } catch (java.lang.NumberFormatException e) {
                    Log.v(TAG, "NumberFormatException ");
                }
                break;

            case R.id.summary:
                int site_id = 0;
                String button_text = ((Button) findViewById(R.id.summary)).getText().toString();

                if (button_text.equals("EXIT")) {
                    (findViewById(R.id.cust_id)).setVisibility(View.VISIBLE);
                    (findViewById(R.id.report)).setVisibility(View.VISIBLE);
                    (findViewById(R.id.detailed_report)).setVisibility(View.VISIBLE);
    
                    (findViewById(R.id.new_cust_id)).setVisibility(View.INVISIBLE);
                    (findViewById(R.id.new_cust_name)).setVisibility(View.INVISIBLE);
    
                    ((Button) findViewById(R.id.summary)).setText("SUMMARY");
                    ((Button) findViewById(R.id.add_cust)).setText("NEW CUSTOMER");
                } else if (button_text.equals("SUMMARY") ) {
                    if (siteName.equals(getString(R.string.site_42)))
                        site_id = 1;
                    else if (siteName.equals(getString(R.string.site_SSN )))
                        site_id = 2;
                    else if (siteName.equals(getString(R.string.site_SS )))
                        site_id = 3;
                    else if (siteName.equals(getString(R.string.site_SP )))
                        site_id = 4;

                    ((Button) findViewById(R.id.summary)).setText("HIDE SUMMARY");

                    crsr = dbHelper.getGrandTotalBySite(site_id);
                    if (crsr != null && crsr.moveToFirst()) {
                        do {
                            Log.v(TAG, Arrays.toString(crsr.getColumnNames()));
                            String sum_tot = crsr.getString(crsr.getColumnIndex("SUM(TOTAL)"));
                            String sum_quant = crsr.getString(crsr.getColumnIndex("SUM(QUANTITY)"));
                            details += "TOTAL AMOUNT to BE PAID:\t" + sum_tot + "\n";
                            details += "TOTAL MILK Collected:\t" + sum_quant + "\n\n";
                        } while (crsr.moveToNext());

                        crsr.close();

                        crsr = dbHelper.getDateWiseCollectionBySite(site_id);
                        if (crsr != null && crsr.moveToFirst()) {
                            do {
                                Log.v(TAG, Arrays.toString(crsr.getColumnNames()));
                                String date = crsr.getString(crsr.getColumnIndex("DATE"));
                                String sum_groupby_date = crsr.getString(crsr.getColumnIndex("SUM(QUANTITY)"));
                                details +=  date + space(8) + sum_groupby_date + "\n";
                            } while (crsr.moveToNext());
                        }


                        ((TextView) findViewById(R.id.detailed_report)).setText(details);
                    }
                    crsr.close();


                } else {
                    ((Button) findViewById(R.id.summary)).setText("SUMMARY");
                    ((TextView) findViewById(R.id.detailed_report)).setText("");
                }
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}

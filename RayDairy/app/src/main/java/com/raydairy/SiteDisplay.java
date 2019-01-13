package com.raydairy;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.method.ScrollingMovementMethod;
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

        TextView detail_textView = findViewById(R.id.detailed_report);
        detail_textView.setMaxLines(25);
        detail_textView.setVerticalScrollBarEnabled(true);


        detail_textView.setMovementMethod(new ScrollingMovementMethod());
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
                    details =  space(2) + String.valueOf(id) + "\t\t" + name + "\n\n";
                    details += space(6) +
                            "DATE" + space(3) +
                            "QNT" + space(1) +
                            "LCT" + space(2) +
                            "FAT" + space(4) +
                            "PRC" + space(3) +
                            "TOT" + "\n";
                    int count = 1;
                    if (crsr != null && crsr.moveToFirst()) {
                        do {
                            String date = crsr.getString(crsr.getColumnIndex("DATE"));
                            String total = crsr.getString(crsr.getColumnIndex("TOTAL"));
                            String lact = crsr.getString(crsr.getColumnIndex("LACT"));
                            String fat = crsr.getString(crsr.getColumnIndex("FAT"));
                            String pric = crsr.getString(crsr.getColumnIndex("PRICE"));
                            if (pric == null)
                                pric = "NA";
                            String quant = crsr.getString(crsr.getColumnIndex("QUANTITY"));

                            String record = space(1) + Integer.toString(count) + ":" +
                                    space(3 - Integer.toString(count).length()) +
                                    date.substring(0,6) +
                                    space(5 - quant.length()) + quant +
                                    space(4 - lact.length()) + lact +
                                    space(5 - fat.length()) + fat +
                                    space(8 - pric.length()) + pric +
                                    space(6 - total.length()) + total;

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
                    ((Button) findViewById(R.id.summary)).setText("HIDE SUMMARY");

                    ((TextView) findViewById(R.id.detailed_report)).setText(getDetails());
                } else {
                    ((Button) findViewById(R.id.summary)).setText("SUMMARY");
                    ((TextView) findViewById(R.id.detailed_report)).setText("");
                }
                break;
             case R.id.whatsapp:
                try {
                    String phone = "918249279918";

                    Intent sendIntent =new Intent("android.intent.action.MAIN");
                    //sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.setPackage("com.whatsapp");
                    sendIntent.setType("text/plain");
                    sendIntent.putExtra("jid", phone +"@s.whatsapp.net");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, getDetails() + "\n\nFrom: " + siteName);

                    startActivity(sendIntent);

                } catch (/*PackageManager.NameNotFoundException e*/ Exception e) {
                    Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                            .show();
                }
        }
    }

    private  int getSiteId() {
        int site_id = 0;
        if (siteName.equals(getString(R.string.site_42)))
            site_id = 1;
        else if (siteName.equals(getString(R.string.site_SSN )))
            site_id = 2;
        else if (siteName.equals(getString(R.string.site_SS )))
            site_id = 3;
        else if (siteName.equals(getString(R.string.site_SP )))
            site_id = 4;
        return site_id;
    }

    private String getDetails() {
        String name = "INVALID";
        Cursor crsr = null;
        String details = "";

        crsr = dbHelper.getGrandTotalBySite(getSiteId());
        if (crsr != null && crsr.moveToFirst()) {
            do {
                Log.v(TAG, Arrays.toString(crsr.getColumnNames()));
                String sum_tot = crsr.getString(crsr.getColumnIndex("SUM(TOTAL)"));
                String sum_quant = crsr.getString(crsr.getColumnIndex("SUM(QUANTITY)"));
                details += "TOTAL AMOUNT TO PAY :\t" + sum_tot + "\n";
                details += "TOTAL MILK COLLECTED:\t" + sum_quant + "\n\n";
            } while (crsr.moveToNext());
        }
            crsr.close();

            crsr = dbHelper.getDateWiseCollectionBySite(getSiteId());
            if (crsr != null && crsr.moveToFirst()) {
                do {
                    Log.v(TAG, Arrays.toString(crsr.getColumnNames()));
                    String date = crsr.getString(crsr.getColumnIndex("DATE"));
                    String wAvg = weightedAvg(date);

                    String sum_groupby_date = crsr.getString(crsr.getColumnIndex("SUM(QUANTITY)"));
                    float quant_bykg = crsr.getFloat(crsr.getColumnIndex("SUM(QUANTITY)")) * 1.03f;

                    details +=  date + space(1) + " : " + sum_groupby_date + " : " +
                            formatString(Float.toString(quant_bykg)) + " : " +  wAvg +"\n";
                } while (crsr.moveToNext());
            }

            crsr.close();

            details += "\nOVER ALL COLLECTION BY FAT \n";
            crsr = dbHelper.getFATWiseCollectionBySite(getSiteId());
            int c =0;
            if (crsr != null && crsr.moveToFirst()) {
                do {
                    Log.v(TAG, Arrays.toString(crsr.getColumnNames()));
                    String fat = crsr.getString(crsr.getColumnIndex("FAT"));
                    String sum_groupby_fat = crsr.getString(crsr.getColumnIndex("SUM(QUANTITY)"));
                    details +=  fat + "->" + sum_groupby_fat + "  ";
                    ++c;
                    if (0 == c % 4) {
                        details += "\n";
                        c = 0;
                    }
                } while (crsr.moveToNext());
            }
            crsr.close();

            return details;
    }

    private String getDetailsById() {
        String name = "INVALID";
        Cursor crsr = null;
        String details = "";

        details += "OVER ALL COLLECTION BY ID \n" + "|";
        crsr = dbHelper.getFATWiseCollectionBySite(getSiteId());
        int c = 0;
        if (crsr != null && crsr.moveToFirst()) {
            do {
                Log.v(TAG, Arrays.toString(crsr.getColumnNames()));
                String fat = crsr.getString(crsr.getColumnIndex("FAT"));
                String sum_groupby_fat = crsr.getString(crsr.getColumnIndex("SUM(QUANTITY)"));
                details +=  fat + "->" + sum_groupby_fat + "| ";
                ++c;
                if (0 == c % 4) {
                    details += "\n";
                    c = 0;
                }

            } while (crsr.moveToNext());
        }
        crsr.close();

        return details;
    }

    private String formatString(String str) {
        if (str.contains(".")) {
            String a = str.split("\\.")[0];
            String b = str.split("\\.")[1];
            if (b.length() > 3)
                return a + "." + b.substring(0, 2);
            else
                return a + "." + b;
        }  else {
            return str;
        }
    }

    private String weightedAvg(String date) {
        Cursor crsr = dbHelper.getCollectionByDateAndSite(getSiteId(), date);
        float wFat = 0.0f;
        float wSNF = 0.0f;
        float totQuant = 0.0f;
        if (crsr != null && crsr.moveToFirst()) {
            do {
                Log.v(TAG, Arrays.toString(crsr.getColumnNames()));
                float fat = crsr.getFloat(crsr.getColumnIndex("FAT"));
                float snf= crsr.getFloat(crsr.getColumnIndex("SNF"));
                float quant = crsr.getFloat(crsr.getColumnIndex("QUANTITY"));
                wFat += (fat * quant);
                wSNF += (snf * quant);
                totQuant += quant;
            } while (crsr.moveToNext());
        }
        crsr.close();
        try {
            wFat = wFat / totQuant;
            wSNF = wSNF / totQuant;
        } catch (java.lang.NumberFormatException e) {
            Log.v(TAG, "NumberFormatException ");
        }
        return  formatString(Float.toString(wFat)) + " : " +
                formatString(Float.toString(wSNF));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}
package com.raydairy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import static com.raydairy.MainActivity.ray_dairy_site;

public class DailyDetails extends AppCompatActivity  {
    public static final String ray_dairy_site = "";
    private static final String TAG = "RAYActivity";
    private String siteName;
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    ArrayList<String> datesArr = new ArrayList<String>();
    int listindx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_details);
        Intent intent = getIntent();
        siteName = intent.getStringExtra(ray_dairy_site);
        TextView textView = findViewById(R.id.site_id);
        textView.setText(siteName);

        (findViewById(R.id.detailed_report)).setVisibility(View.VISIBLE);

        TextView detail_textView = findViewById(R.id.detailed_report);
        detail_textView.setMaxLines(25);
        detail_textView.setVerticalScrollBarEnabled(true);

        detail_textView.setMovementMethod(new ScrollingMovementMethod());
    }

    private  void prepareDateList() {
        Cursor crsr = null;
        datesArr.clear();
        crsr = dbHelper.getCollectionDateBySite(getSiteId());
        if (crsr != null && crsr.moveToFirst()) {
            do {
                Log.v(TAG, Arrays.toString(crsr.getColumnNames()));
                String date = crsr.getString(crsr.getColumnIndex("DATE"));
                datesArr.add(date);
            } while (crsr.moveToNext());
        }
        crsr.close();

        if(datesArr.size() > 0)
            listindx = datesArr.size() - 1;
    }

    private void prepareDetails() {
        Cursor crsr = null;
        String details = "";

        if(listindx < 0) {
            ((TextView) findViewById(R.id.detailed_report)).setText("");
            return;
        }

        crsr = dbHelper.getCollectionByDateAndSite(getSiteId(), datesArr.get(listindx));
        details += header();
        int totalTobePaid = 0;
        if (crsr != null && crsr.moveToFirst()) {
            do {
                int id = crsr.getInt(crsr.getColumnIndex("ID"));
                Cursor cr = dbHelper.getDataById(id);
                int colName = cr.getColumnIndex("NAME");
                String name = "";
                if (cr != null && cr.moveToFirst()) {
                    name = cr.getString(colName);

                    //Log.v(TAG, String.valueOf(id));
                    //Log.v(TAG, name);
                }
                cr.close();

                int ipric = crsr.getInt(crsr.getColumnIndex("TOTAL"));
                totalTobePaid += ipric;

                //String date = crsr.getString(crsr.getColumnIndex("DATE"));
                String total = crsr.getString(crsr.getColumnIndex("TOTAL"));
                String lact = crsr.getString(crsr.getColumnIndex("LACT"));
                String fat = crsr.getString(crsr.getColumnIndex("FAT"));
                String pric = crsr.getString(crsr.getColumnIndex("PRICE"));

                if (pric == null)
                    pric = "NA";
                String quant = crsr.getString(crsr.getColumnIndex("QUANTITY"));

                String record = Integer.toString(id) +
                        space(10 - name.length()) + name +
                        space(4 - quant.length()) + quant +
                        space(4 - lact.length()) + lact +
                        space(5 - fat.length()) + fat +
                        space(8 - pric.length()) + pric +
                        space(6 - total.length()) + total;

                details += record + "\n";
            } while (crsr.moveToNext());
        }
        crsr.close();

        if (listindx == 0){
            ((Button) findViewById(R.id.prev)).setEnabled(false);
        }
        details +=  "\n Total to be paid:   " + Integer.toString(totalTobePaid);
        ((TextView) findViewById(R.id.detailed_report)).setText(details);

    }

    public void buttonClickHandler(View view) {
        Log.v(TAG, "buttonClickHandler ");

        switch (view.getId()) {
            case R.id.today:
                //code sequence matters
                prepareDateList();
                prepareDetails();
                if (listindx > 0){
                    ((Button) findViewById(R.id.prev)).setEnabled(true);
                }

                ((Button) findViewById(R.id.next)).setEnabled(false);

                break;
            case R.id.prev:
                --listindx;
                if (listindx == 0){
                    ((Button) findViewById(R.id.prev)).setEnabled(false);
                }
                if(listindx > -1) {
                    ((Button) findViewById(R.id.next)).setEnabled(true);
                    prepareDetails();
                }
                break;
            case R.id.next:
                ++listindx;
                if (listindx == datesArr.size() - 1){
                    ((Button) findViewById(R.id.next)).setEnabled(false);
                }
                if(listindx < datesArr.size())
                    ((Button) findViewById(R.id.prev)).setEnabled(true);
                    prepareDetails();
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
                    String getDetails = ((TextView) findViewById(R.id.detailed_report)).getText().toString();

                    sendIntent.putExtra(Intent.EXTRA_TEXT, getDetails + "\n\nFrom: " + siteName);

                    startActivity(sendIntent);

                } catch (/*PackageManager.NameNotFoundException e*/ Exception e) {
                    Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                            .show();
                }
        }
    }

    private String header() {
        String header = "";
        header += datesArr.get(listindx) + "\n" +
                "ID" + space(3) +
                "NAME" + space(5) +
                "QNT" + space(1) +
                "LCT" + space(2) +
                "FAT" + space(4) +
                "PRC" + space(3) +
                "TOT" + "\n";
        return header;
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
        else if (siteName.equals(getString(R.string.site_ASP )))
            site_id = 5;

        return site_id;
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
}

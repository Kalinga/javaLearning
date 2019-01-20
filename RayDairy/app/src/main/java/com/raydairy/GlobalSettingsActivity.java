package com.raydairy;

import android.app.Activity;
import android.app.Dialog;
import android.app.backup.BackupManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class GlobalSettingsActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private static final String TAG = "RAYActivity";
    public static final String DATABASE_NAME = "users.db";
    private BackupManager backupManager = new BackupManager(this);

    DatabaseHelper dbHelper = new DatabaseHelper(this);

    private void whatsapp(String msg) {
        try {
            String phone = "917795346374";

            Intent sendIntent =new Intent("android.intent.action.MAIN");
            //sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setPackage("com.whatsapp");
            sendIntent.setType("text/plain");
            sendIntent.putExtra("jid", phone +"@s.whatsapp.net");
            sendIntent.putExtra(Intent.EXTRA_TEXT, msg );

            startActivity(sendIntent);

        } catch (/*PackageManager.NameNotFoundException e*/ Exception e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
            Log.v(TAG, "com.raydairy.broadcast.RESET_TRANSACTION" + message);
            // fetch and whatsapp
            String details = allTransactions();

            dbHelper.resetTransaction();

            whatsapp(details);
        }
    };

    private String allTransactions() {
        Cursor crsr = dbHelper.getAllTransaction();
        String details = "";
        if (crsr != null && crsr.moveToFirst()) {
            do {
                String date = crsr.getString(crsr.getColumnIndex("DATE"));
                String id = crsr.getString(crsr.getColumnIndex("ID"));
                String total = crsr.getString(crsr.getColumnIndex("TOTAL"));
                String lact = crsr.getString(crsr.getColumnIndex("LACT"));
                String fat = crsr.getString(crsr.getColumnIndex("FAT"));
                String snf = crsr.getString(crsr.getColumnIndex("SNF"));
                String pric = crsr.getString(crsr.getColumnIndex("PRICE"));
                if (pric == null)
                    pric = "NA";
                String quant = crsr.getString(crsr.getColumnIndex("QUANTITY"));

                String record =
                        id    + "," +
                        date  + "," +
                        quant + "," +
                        lact  + "," +
                        fat   + "," +
                        snf   + "," +
                        pric  + "," +
                        total;

                details += record + "\n";

            } while (crsr.moveToNext());
        }
        crsr.close();
        return details;
    }

    private void setOnFocusChangeListener(int id) {
        EditText ed = findViewById(id);
        ed.setOnFocusChangeListener(this);
    }


    public String space(int n) {
        StringBuilder str = new StringBuilder("");
        for(int i =0; i<n; ++i)
            str.append(" ");

        return str.toString();
    }

    public void listAllCustomer(View view) {
        String buttonText = ((Button)view).getText().toString();
        if (buttonText == "HIDE") {
            ((Button)(findViewById(R.id.list_all_cust))).setText("CUSTOMERS");
            (findViewById(R.id.all_customer_view)).setVisibility(View.INVISIBLE);
        } else {

            (findViewById(R.id.all_customer_view)).setVisibility(View.VISIBLE);
            ((Button)(findViewById(R.id.list_all_cust))).setText("HIDE");

            Cursor crsr = dbHelper.getAllCustomer();
            String details = "";

            int count = 1;
            if( crsr != null && crsr.moveToFirst() ) {
                do {
                    String id = crsr.getString(crsr.getColumnIndex("ID"));
                    String name = crsr.getString(crsr.getColumnIndex("NAME"));

                    String record = id + space(6) + name;
                    details += record + "\n";
                    ++count;
                } while (crsr.moveToNext());
            }
            crsr.close();

            ((TextView)(findViewById(R.id.all_customer_view))).setText(details);
        }
    }

    public void resetTrasactions(View view) {
        ResetTransactionDialogFragment dialog = new ResetTransactionDialogFragment();
        dialog.show(getSupportFragmentManager(), "Reset Transaction?");
    }

    public void backupCustomer(View view) {
        Log.v(TAG, "GlobalSettingsActivity:backupCustomer:");
        backupManager.dataChanged();
    }

    private void savePrice() {
        try {
            float price = Float.parseFloat(((EditText) findViewById(R.id.base_price)).getText().toString());
            String phone_number = (((EditText) findViewById(R.id.whatsAppNo)).getText().toString());
            SharedPreferences prefs = PreferenceManager
                    .getDefaultSharedPreferences(this);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putFloat("price", price);
            editor.putString("phone_number", phone_number);
            
            editor.commit();
        }
        catch (java.lang.NumberFormatException e) {
                Log.v(TAG, "NumberFormatException ");
            }
    }
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_settings);

        TextView detail_textView = findViewById(R.id.all_customer_view);
        detail_textView.setMaxLines(10);
        detail_textView.setVerticalScrollBarEnabled(true);

        backupManager = new BackupManager(getBaseContext());
        
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("com.raydairy.broadcast.RESET_TRANSACTION"));

        setOnFocusChangeListener(R.id.base_price);

        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = prefs.edit();
        float old_price = prefs.getFloat("price", 24.40f);
        ((EditText) findViewById(R.id.base_price)).setText(Float.toString(old_price));
        
        String phone_number = prefs.getString("phone_number", "918249279918");
        ((EditText) findViewById(R.id.whatsAppNo)).setText(phone_number);
    }

    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    public void buttonClickHandler(View view) {
        String name = ((Button) view).getText().toString();
        Log.v(TAG, name);
        Intent intent = null;
        if (name.equals("Save")) {
            Log.v(TAG, "Save Price");
            savePrice();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            Log.v(TAG, "GlobalSettingsActivity:onFocusChange:hasFocus");
        }
        if (!hasFocus){
            Log.v(TAG, "GlobalSettingsActivity:onFocusChange:lostFocus");
        } // focus out
    } // onFocusChange
}


/*public void resetDBbuttonClickHandler(View view) {
    String DB_PATH = getApplicationContext().getDatabasePath(DATABASE_NAME).getPath();
    File file = new File(DB_PATH );
    if (file.exists()) {
            boolean deleted = file.delete();
            Log.v(TAG, "Database deleted: " + String.valueOf(deleted));
    }

    SharedPreferences prefs = PreferenceManager
            .getDefaultSharedPreferences(this);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putBoolean("db_created", false);
    editor.commit();
}*/

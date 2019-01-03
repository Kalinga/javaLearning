package com.raydairy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class ResetTransactionDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you Sure to DELETE All data for last 10 days? )
               .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // Delete all Data
                       dbHelper.resetTransaction();
                   }
               })
               .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

public class GlobalSettingsActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private static final String TAG = "RAYActivity";
    public static final String DATABASE_NAME = "users.db";

    DatabaseHelper dbHelper = new DatabaseHelper(this);


    private void setOnFocusChangeListener(int id) {
        EditText ed = findViewById(id);
        ed.setOnFocusChangeListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_settings);

        //dbh =  new DatabaseHelper(this);
        //dbh.updateData(27.50f);

        setOnFocusChangeListener(R.id.base_price);

        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = prefs.edit();
        float old_price = prefs.getFloat("price", 24.40f);
        ((EditText) findViewById(R.id.base_price)).setText(Float.toString(old_price));
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
        dialog.show();
    }

    public void resetDBbuttonClickHandler(View view) {
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
    }


    private void savePrice() {
        try {
            float price = Float.parseFloat(((EditText) findViewById(R.id.base_price)).getText().toString());
            SharedPreferences prefs = PreferenceManager
                    .getDefaultSharedPreferences(this);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putFloat("price", price);
            editor.commit();
        }
        catch (java.lang.NumberFormatException e) {
                Log.v(TAG, "NumberFormatException ");
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

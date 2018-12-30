package com.raydairy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

/**
 * credits to Mitch.
 * Modified further by Kray
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "RAYActivity";

    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_CUSTOMER = "users_data";
    public static final String TABLE_TRANS = "transcation";

    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";

    public static final String COL_DATE = "DATE";
    public static final String COL_LACT = "LACT";
    public static final String COL_FAT = "FAT";
    public static final String COL_QUA = "QUANTITY";
    public static final String COL_TOT = "TOTAL";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON;");

        // transaction  Customer table
        String customer = "CREATE TABLE " + TABLE_CUSTOMER + "(" + COL_ID +  " INTEGER PRIMARY KEY, "
                + COL_NAME + " TEXT )";
        db.execSQL(customer);

        // transaction  TABLE
        StringBuilder transaction = new StringBuilder();
        transaction.append("CREATE TABLE " + TABLE_TRANS + "(" + COL_ID +  " INTEGER, ");
        transaction.append(COL_DATE + " TEXT," + COL_LACT + " INTEGER, " + COL_FAT + " INTEGER, ");
        transaction.append(COL_QUA + " INTEGER, " + COL_TOT + " INTEGER, ");
        transaction.append("FOREIGN KEY (" + COL_ID + ") REFERENCES " + TABLE_CUSTOMER + "("+ COL_ID+"));");

        db.execSQL(transaction.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP IF TABLE EXISTS " + TABLE_CUSTOMER);
        onCreate(db);
    }

    public boolean addCustomer(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_NAME, name);

        long result = db.insert(TABLE_CUSTOMER, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addTransaction(int id, String date, int lact, float fat, float quant, float total) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_DATE, date);
        contentValues.put(COL_LACT, lact);
        contentValues.put(COL_FAT, fat);
        contentValues.put(COL_QUA, quant);
        contentValues.put(COL_TOT, total);

        long result = db.insert(TABLE_TRANS, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateData(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_NAME, name);

        long result = db.update(TABLE_CUSTOMER, contentValues,"ID=?", new String[]{"1"});
        Log.v(TAG, Long.toString(result));

        //if data as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            Log.v(TAG, getTableContents().toString());
            return true;
        }
    }

    public Cursor getDataById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String strId = Integer.toString(id);
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_CUSTOMER + " WHERE ID=?", new String[]{strId});
        return data;
    }

    public Cursor getTableContents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_CUSTOMER, null);
        return data;
    }
}

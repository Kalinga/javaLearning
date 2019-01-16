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

    static final int DATABASE_VERSION = 3;

    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_CUSTOMER = "users_data";
    public static final String TABLE_TRANS = "transcation";

    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";

    public static final String COL_DATE = "DATE";
    public static final String COL_LACT = "LACT";
    public static final String COL_FAT = "FAT";
    public static final String COL_SNF = "SNF";

    public static final String COL_QUA = "QUANTITY";
    public static final String COL_PRIC = "PRICE";
    public static final String COL_TOT = "TOTAL";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON;");

        // craete  Customer table
        String customer = "CREATE TABLE " + TABLE_CUSTOMER + "(" + COL_ID +  " INTEGER PRIMARY KEY, "
                + COL_NAME + " TEXT )";
        db.execSQL(customer);

        // craete transaction  TABLE
        StringBuilder transaction = new StringBuilder();
        transaction.append("CREATE TABLE " + TABLE_TRANS + "(" + COL_ID +  " INTEGER, ");
        transaction.append(COL_DATE + " TEXT," + COL_LACT + " INTEGER, " + COL_FAT + " INTEGER, ");
        transaction.append(COL_SNF + " INTEGER, ");
        transaction.append(COL_QUA + " INTEGER, " + COL_TOT + " INTEGER, " + COL_PRIC + " INTEGER, ");
        transaction.append("FOREIGN KEY (" + COL_ID + ") REFERENCES " + TABLE_CUSTOMER + "("+ COL_ID+"));");

        db.execSQL(transaction.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < 2)
            db.execSQL("ALTER TABLE " + TABLE_TRANS + " ADD COLUMN " + COL_PRIC + " string;");
        if(oldVersion < 3)
            db.execSQL("ALTER TABLE " + TABLE_TRANS + " ADD COLUMN " + COL_SNF + " INTEGER;");
    }

    public boolean addCustomer(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_NAME, name);

        long result = db.insert(TABLE_CUSTOMER, null, contentValues);

        //if data is inserted incorrectly, it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean removeEmptyCustomer() {
        SQLiteDatabase db = this.getWritableDatabase();
        String emptyName = "NAME is null or NAME = ''";
        long result = db.delete(TABLE_CUSTOMER,  emptyName, null );

        //if data is inserted incorrectly, it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addTransaction(int id, String date, int lact, float fat,
                                  float snf, float quant, float price, float total) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_DATE, date);
        contentValues.put(COL_LACT, lact);
        contentValues.put(COL_FAT, fat);
        contentValues.put(COL_SNF, snf);
        contentValues.put(COL_QUA, quant);
        contentValues.put(COL_PRIC, price);
        contentValues.put(COL_TOT, total);

        long result = db.insert(TABLE_TRANS, null, contentValues);

        //if data is inserted incorrectly it will return -1
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

        //if data is inserted incorrectly it will return -1
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

    public Cursor getTransactionById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_TRANS + " WHERE ID=?",
                new String[]{Integer.toString(id)});
        return data;
    }

    public Cursor getGrandTotalById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT SUM(TOTAL) FROM " + TABLE_TRANS + " WHERE ID=?",
                new String[]{Integer.toString(id)});
        return data;
    }

    public Cursor getDateWiseCollectionBySite(int siteId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = null;
        String qry = "SELECT DATE, SUM(QUANTITY) FROM " + TABLE_TRANS + " WHERE ID BETWEEN ";
        switch (siteId) {
            case 1:
                data = db.rawQuery( qry + "1 AND 99 GROUP BY DATE", null);
                break;
            case 2:
                data = db.rawQuery(qry + " 100 AND 199 GROUP BY DATE", null);
                break;
            case 3:
                data = db.rawQuery(qry + "200 AND 299 GROUP BY DATE", null);
                break;
            case 4:
                data = db.rawQuery(qry + "300 AND 399 GROUP BY DATE", null);
                break;
            case 5:
                data = db.rawQuery(qry + "400 AND 499 GROUP BY DATE", null);
                break;
        }
        return data;
    }

    public Cursor getFATWiseCollectionBySite(int siteId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = null;
        String cond = "GROUP BY FAT";
        String qry = "SELECT FAT, SUM(QUANTITY) FROM " + TABLE_TRANS + " WHERE ID BETWEEN ";
        switch (siteId) {
            case 1:
                data = db.rawQuery( qry + "1 AND 99 " + cond, null);
                break;
            case 2:
                data = db.rawQuery(qry + " 100 AND 199 " + cond, null);
                break;
            case 3:
                data = db.rawQuery(qry + "200 AND 299 " + cond, null);
                break;
            case 4:
                data = db.rawQuery(qry + "300 AND 399 " + cond, null);
                break;
            case 5:
                data = db.rawQuery(qry + "400 AND 499 " + cond, null);
                break;
        }
        return data;
    }


    public Cursor getGrandTotalBySite(int siteId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = null;
        String qry = "SELECT SUM(TOTAL), SUM(QUANTITY) FROM " + TABLE_TRANS + " WHERE ID BETWEEN ";

        switch (siteId) {
            case 1:
                data = db.rawQuery(qry + "1 AND 99", null);
                break;
            case 2:
                data = db.rawQuery(qry + " 100 AND 199", null);
                break;
            case 3:
                data = db.rawQuery(qry + " 200 AND 299", null);
                break;
            case 4:
                data = db.rawQuery(qry + " 300 AND 399", null);
                break;
            case 5:
                data = db.rawQuery(qry + " 400 AND 499", null);
                break;
        }
        return data;
    }
    public Cursor getAllTransaction() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = null;
        String qry = "SELECT * FROM " + TABLE_TRANS + " WHERE ID!=999 ";
        data = db.rawQuery(qry, null);

        return data;
    }

    public Cursor getCollectionDateBySite(int siteId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = null;
        String qry = "SELECT DISTINCT DATE FROM " + TABLE_TRANS + " WHERE ID BETWEEN ";
        String cond = " ORDER BY DATE ASC";
        switch (siteId) {
            case 1:
                data = db.rawQuery(qry + "1 AND 99" + cond, null);
                break;
            case 2:
                data = db.rawQuery(qry + " 100 AND 199" + cond, null);
                break;
            case 3:
                data = db.rawQuery(qry + " 200 AND 299" + cond, null);
                break;
            case 4:
                data = db.rawQuery(qry + " 300 AND 399" + cond, null);
                break;
            case 5:
                data = db.rawQuery(qry + " 400 AND 499" + cond, null);
                break;
        }
        return data;
    }

        public Cursor getCollectionByDateAndSite(int siteId, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = null;
        String qry = "SELECT * FROM " + TABLE_TRANS + " WHERE ID BETWEEN ";
        String cond = " AND DATE=" + "\"" + date + "\"";

        switch (siteId) {
            case 1:
                data = db.rawQuery(qry + "1 AND 99" + cond, null);
                break;
            case 2:
                data = db.rawQuery(qry + " 100 AND 199" + cond, null);
                break;
            case 3:
                data = db.rawQuery(qry + " 200 AND 299" + cond, null);
                break;
            case 4:
                data = db.rawQuery(qry + " 300 AND 399" + cond, null);
                break;
            case 5:
                data = db.rawQuery(qry + " 400 AND 499" + cond, null);
                break;
        }
        return data;
    }

    public Cursor getAllCustomer() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_CUSTOMER, null);
        return data;
    }

    public void resetTransaction() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_TRANS);

        removeEmptyCustomer();

    }
}

package com.raydairy;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.app.backup.FileBackupHelper;
import android.app.backup.SharedPreferencesBackupHelper;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MyBackupAgent extends BackupAgentHelper {
   private static final String TAG = "RAYActivity";

   static final String DATABASE_NAME = "users.db";
   static final String DIARY_PREFS_BACKUP_KEY = "dairy_prefs";
   static final String DIARY_PREFS_BACKUP_KEY_DB = "dairy_database";

   @Override
   public void onCreate(){
      Log.v(TAG, "MyBackupAgent: onCreate");

      FileBackupHelper dbs = new FileBackupHelper(this, DATABASE_NAME);
      addHelper(DIARY_PREFS_BACKUP_KEY_DB, dbs);
      
      SharedPreferencesBackupHelper helper =
              new SharedPreferencesBackupHelper(this, "phone_number", "price");
      addHelper(DIARY_PREFS_BACKUP_KEY, helper);
   }

    @Override
    public void onBackup(ParcelFileDescriptor oldState, BackupDataOutput data,
                         ParcelFileDescriptor newState) throws IOException {
        Toast.makeText(this, "Restore..", Toast.LENGTH_SHORT)
                .show();
        super.onBackup(oldState, data, newState);
    }

   @Override
   public File getFilesDir(){
      File path = getDatabasePath(DATABASE_NAME);
      return path.getParentFile();
   }

    @Override
    public void onRestore(BackupDataInput data, int appVersionCode, ParcelFileDescriptor newState)
            throws IOException {

        Toast.makeText(this, "Restore..", Toast.LENGTH_SHORT)
                .show();
        super.onRestore(data, appVersionCode, newState);
    }
}
package com.raydairy;

class MyBackupAgent extends BackupAgentHelper{
   static final String DATABASE_NAME = "users.db";
   static final String DIARY_PREFS_BACKUP_KEY = "dairy_prefs";
   static final String DIARY_PREFS_BACKUP_KEY_DB = "dairy_database";

   @Override
   public void onCreate(){
      FileBackupHelper dbs = new FileBackupHelper(this, DATABASE_NAME);
      addHelper(DIARY_PREFS_BACKUP_KEY_DB, dbs);
      
      SharedPreferencesBackupHelper helper =
              new SharedPreferencesBackupHelper(this, "phone_number", "price");
      addHelper(DIARY_PREFS_BACKUP_KEY, helper);
   }

   @Override
   public File getFilesDir(){
      File path = getDatabasePath(DATABASE_NAME);
      return path.getParentFile();
   }
}
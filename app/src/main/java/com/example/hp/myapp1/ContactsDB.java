package com.example.hp.myapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactsDB {
    public static final String key_id = "_Id";
    public static final String key_name = "_Name";
    public static final String key_email = "_Email";
    public static final String key_password = "_password";

    private final String DATABASE_NAME = "first";
    private final String DATABASE_TABLE = "save";
    private final int DATABASE_VERSION = 1;

    private DBHelper ourhelper;
    private final Context ourContext;
    private SQLiteDatabase ourDATABASE;

    public ContactsDB(Context context)
    {
        ourContext = context;
    }
     private class DBHelper extends SQLiteOpenHelper{

         public DBHelper(Context context) {
             super(context, DATABASE_NAME, null, 1);
         }

         @Override
         public void onCreate(SQLiteDatabase db) {
             String sqlCode = "CREATE TABLE " + DATABASE_TABLE + " (" +
                     key_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     key_name + "TEXT NOT NULL, " +
                     key_email + "TEXT NOT NULL, " +
                     key_password + "TEXT NOT NULL);";
             db.execSQL(sqlCode);

         }

         @Override
         public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
             db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
             onCreate(db);

         }
     }

     public ContactsDB open() throws SQLException{
        ourhelper = new DBHelper(ourContext);
        ourDATABASE = ourhelper.getWritableDatabase();
        return this;
     }
     public void close()
     {
         ourhelper.close();
     }
     public long createEntry(String name,String email,String password)
     {
         ContentValues cv = new ContentValues();
         cv.put(key_name,name);
         cv.put(key_email,email);
         cv.put(key_password,password);
         return  ourDATABASE.insert(DATABASE_TABLE,null,cv);
     }

     public String getDATA()
     {
         String [] columns = new String[] {key_id,key_name,key_email,key_password};
         Cursor c = ourDATABASE.query(DATABASE_TABLE,columns,null,null,null,null,null);
         String result = "";
         int iID = c.getColumnIndex(key_id);
         int iName = c.getColumnIndex(key_name);
         int iEmail = c.getColumnIndex(key_email);
         int iPassword = c.getColumnIndex(key_password);

         for(c.moveToFirst();c.isAfterLast();c.moveToNext())
         {
             result= result + c.getString(iID) + ": " + c.getString(iName) + ": " + c.getString(iEmail) + ": " + c.getString(iPassword) + "\n";

         }
         c.close();
         return result;
     }
      public long deleteEntry(String rowId) {
          return ourDATABASE.delete(DATABASE_TABLE, key_id + "=?", new String[]{rowId});
      }
      public long updateEntry(String rowId, String name, String email, String password)

      {
          ContentValues cv= new ContentValues();
          cv.put(key_name,name);
          cv.put(key_email,email);
          cv.put(key_password,password);

          return ourDATABASE.update(DATABASE_TABLE, cv, key_id + "=?", new String[]{rowId});
      }
}

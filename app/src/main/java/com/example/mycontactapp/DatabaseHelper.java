package com.example.mycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact2019.db";
    public static final String TABLE_NAME = "Contact2019_table";
    public static final String ID = "ID";
    public static final String COLUMN_NAME_CONTACT = "Name";
    public static final String COLUMN_NUMBER_CONTACT = "Phone Number";
    public static final String COLUMN_SPAGHETTI_CONTACT = "Spaghetti";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_CONTACT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d( "MyContactApp", "DatabaseHelper: constructed DatabaseHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d( "MyContactApp", "onCreate: created database");

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d( "MyContactApp", "onUpgrade: upgraded database");

        db.execSQL(SQL_CREATE_ENTRIES);
        onCreate(db);
    }

    public Boolean insertData (String name, String number, String spaghetti){
        Log.d( "MyContactApp", "insertData: inserting data");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_CONTACT, name);
        contentValues.put(COLUMN_NUMBER_CONTACT, number);
        contentValues.put(COLUMN_SPAGHETTI_CONTACT, spaghetti);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            Log.d("MyContactApp", "insertData: failed to insert");
            return false;
        }
        else{
            Log.d("MyContactApp", "insertData: inserted data");
            return true;
        }
    }

    public Cursor getAllData (){
        Log.d("MyContactApp", "DatabaseHelper: called getAllData");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null );
        return res;
    }
}

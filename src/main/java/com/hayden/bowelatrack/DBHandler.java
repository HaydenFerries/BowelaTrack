package com.hayden.bowelatrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dates.db";
    private static final String TABLE_DATES = "dates";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DATE = "_date";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create the initial database table
        String query = "CREATE TABLE " + TABLE_DATES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT " +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Delete table and create new one
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATES);
        onCreate(db);
    }

    public void addDate (Database date){
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date.get_date());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_DATES, null, values);
        db.close();
    }

    public void resetDates(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATES);
        onCreate(db);
    }

    public String ReturnAsString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_DATES + " WHERE 1";

        // Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        // Move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("_date"))!= null){
                dbString += c.getString((c.getColumnIndex("_date")));
                Log.d("Hayden","DBHandler String return: " + dbString);
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}

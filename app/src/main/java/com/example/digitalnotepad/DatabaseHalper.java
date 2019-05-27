package com.example.digitalnotepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHalper extends SQLiteOpenHelper
{
    private static final String TAG = "DatabaseHalper";

    private static final String DATABASE_NAME = "Note.db";
    private static final String TABLE_NAME = "note_table";
    private static final String COL_0 = "ID";
    private static final String COL_1 = "TITLE";
    private static final String COL_2 = "NOTE";
    private static final String COL_3 = "DATE";
    private static final String COL_4 = "HOUR";
    private static final String COL_5 = "ADRESS";
    private static final String COL_6 = "COLOR";
    private static final String COL_7 = "PRIORITY";

    public DatabaseHalper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        Log.d(TAG, "DatabaseHalper: constructed.");
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d(TAG, "onCreate: created.");
        String query = "create table " + TABLE_NAME + "(" +
                COL_0 + " integer primary key autoincrement, " +
                COL_1 + " varchar, " +
                COL_2 + " varchar, " +
                COL_3 + " varchar, " +
                COL_4 + " varchar, " +
                COL_5 + " varchar, " +
                COL_6 + " integer, " +
                COL_7 + " integer)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.d(TAG, "onUpgrade: upgraded.");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String note, String date, String hour, String adress, int color, int priority)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, title);
        contentValues.put(COL_2, note);
        contentValues.put(COL_3, date);
        contentValues.put(COL_4, hour);
        contentValues.put(COL_5, adress);
        contentValues.put(COL_6, color);
        contentValues.put(COL_7, priority);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
        {
            Log.d(TAG, "insertData: inserted.");
            return true;
        }
    }

    public Cursor getData()
    {
        Log.d(TAG, "getData: getted.");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id, String title, String note, String date, String hour, String adress, int color, int priority)
    {
        Log.d(TAG, "updateData: updated.");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_0, id);
        contentValues.put(COL_1, title);
        contentValues.put(COL_2, note);
        contentValues.put(COL_3, date);
        contentValues.put(COL_4, hour);
        contentValues.put(COL_5, adress);
        contentValues.put(COL_6, color);
        contentValues.put(COL_7, priority);
        db.update(TABLE_NAME, contentValues, COL_0 + " = ?", new String[] {id});
        return true;
    }

    public Integer deleteData(String id)
    {
        Log.d(TAG, "deleteData: deleted.");
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_0 + " = ?", new String[] {id});
    }

    public int getDbId()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_0 + " = (SELECT MAX(" + COL_0 + ") FROM " + TABLE_NAME + ");", null);
        c.moveToNext();
        return c.getInt(c.getColumnIndex(COL_0));
    }
}

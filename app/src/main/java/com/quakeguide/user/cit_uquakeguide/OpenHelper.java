package com.quakeguide.user.cit_uquakeguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Obenita on 26/1/2018.
 */

public class OpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "QuakeGuide.db";
    public static final String TABLE_NAME = "CONTACTS";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_CONTACT_NAME = "CONTACT_NAME";
    public static final String COLUMN_NUMBER = "CONTACT_NUMBER";

    public OpenHelper(Context context){
        super(context, DATABASE_NAME, null  , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table CONTACTS (ID INTEGER PRIMARY KEY,CONTACT_NAME TEXT , CONTACT_NUMBER TEXT);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String upgrade = "DROP IF TABLE EXISTS '"+ TABLE_NAME + "'";
        db.execSQL(upgrade);
    }

    public boolean addData(String name, String contact, int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();

        content.put(COLUMN_CONTACT_NAME, name);
        content.put(COLUMN_NUMBER, contact);
        content.put(COLUMN_ID, id);

        long res = db.insert(TABLE_NAME, null, content);

        if(res == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean updateData(int id, String name, String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = COLUMN_ID + "=" + id;

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_CONTACT_NAME, name);
        contentValues.put(COLUMN_NUMBER, number);

        return db.update(TABLE_NAME, contentValues, where, null) != 0;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

}

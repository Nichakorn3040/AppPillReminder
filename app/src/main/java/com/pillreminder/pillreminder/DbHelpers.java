package com.pillreminder.pillreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import static com.pillreminder.pillreminder.DbConstants.PILL_AMOUNT;
import static com.pillreminder.pillreminder.DbConstants.PILL_DATE_START;
import static com.pillreminder.pillreminder.DbConstants.PILL_DOSE;
import static com.pillreminder.pillreminder.DbConstants.PILL_EATEN;
import static com.pillreminder.pillreminder.DbConstants.PILL_EATEN_EVENING;
import static com.pillreminder.pillreminder.DbConstants.PILL_EATEN_MORNING;
import static com.pillreminder.pillreminder.DbConstants.PILL_EATEN_NIGHT;
import static com.pillreminder.pillreminder.DbConstants.PILL_EATEN_NOON;
import static com.pillreminder.pillreminder.DbConstants.PILL_ID;
import static com.pillreminder.pillreminder.DbConstants.PILL_NAME;
import static com.pillreminder.pillreminder.DbConstants.PILL_NOTIFICATION1;
import static com.pillreminder.pillreminder.DbConstants.PILL_NOTIFICATION2;
import static com.pillreminder.pillreminder.DbConstants.PILL_NOTIFICATION3;
import static com.pillreminder.pillreminder.DbConstants.PILL_NOTIFICATION4;
import static com.pillreminder.pillreminder.DbConstants.PILL_TABLE;
import static com.pillreminder.pillreminder.DbConstants.PROFILE_BIRTHDATE;
import static com.pillreminder.pillreminder.DbConstants.PROFILE_GENDER;
import static com.pillreminder.pillreminder.DbConstants.PROFILE_LASTNAME;
import static com.pillreminder.pillreminder.DbConstants.PROFILE_NAME;
import static com.pillreminder.pillreminder.DbConstants.PROFILE_TABLE;


public class DbHelpers extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pillreminder.db";
    private static final int DATABASE_VERSION = 7;

    DbHelpers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String PILL = "CREATE TABLE "+PILL_TABLE+" ("+
                PILL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                PILL_NAME+" TEXT  NOT NULL,"+
                PILL_AMOUNT+" INTEGER  NOT NULL,"+
                PILL_DOSE+" INTEGER  NOT NULL,"+
                PILL_EATEN+" TEXT  NOT NULL,"+
                PILL_EATEN_MORNING+" TEXT  NOT NULL,"+
                PILL_EATEN_NOON+" TEXT  NOT NULL,"+
                PILL_EATEN_EVENING+" TEXT  NOT NULL,"+
                PILL_EATEN_NIGHT+" TEXT  NOT NULL,"+
                PILL_NOTIFICATION1 +" TEXT  NOT NULL,"+
                PILL_NOTIFICATION2 +" TEXT  NOT NULL,"+
                PILL_NOTIFICATION3 +" TEXT  NOT NULL,"+
                PILL_NOTIFICATION4 +" TEXT  NOT NULL,"+
                PILL_DATE_START+" DATE)";
        db.execSQL(PILL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + PILL_TABLE);
            onCreate(db);
        }
    }

    public boolean DatabaseProfileSave(String name, String lastname, String gender, String birthdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILE_NAME, name);
        contentValues.put(PROFILE_LASTNAME, lastname);
        contentValues.put(PROFILE_GENDER, gender);
        contentValues.put(PROFILE_BIRTHDATE, birthdate);
        db.insert(PROFILE_TABLE, null, contentValues);
        return true;
    }

    public ArrayList<HashMap<String, String>> DatabaseGetPillName() {
        ArrayList<HashMap<String, String>> ArrayPill;
        ArrayPill = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase database = this.getWritableDatabase();
        String sql = "SELECT "+PILL_ID+","+PILL_NAME+" FROM "+PILL_TABLE;
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> HashPill = new HashMap<String, String>();
                HashPill.put(PILL_ID, cursor.getString(cursor.getColumnIndex(PILL_ID)));
                HashPill.put(PILL_NAME, cursor.getString(cursor.getColumnIndex(PILL_NAME)));
                ArrayPill.add(HashPill);
            } while (cursor.moveToNext());
        }
        return ArrayPill;
    }

    public ArrayList<HashMap<String, String>> DatabaseAllPill() {
        ArrayList<HashMap<String, String>> ArrayPill;
        ArrayPill = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase database = this.getWritableDatabase();
        String sql = "SELECT "+PILL_ID+","+PILL_NAME+","+PILL_NOTIFICATION1+","+PILL_NOTIFICATION2+","+PILL_NOTIFICATION3+","+PILL_NOTIFICATION4+","+PILL_DATE_START+" FROM "+PILL_TABLE;
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> HashPill = new HashMap<String, String>();
                HashPill.put(PILL_ID, cursor.getString(cursor.getColumnIndex(PILL_ID)));
                HashPill.put(PILL_NAME, cursor.getString(cursor.getColumnIndex(PILL_NAME)));
                HashPill.put(PILL_NOTIFICATION1, cursor.getString(cursor.getColumnIndex(PILL_NOTIFICATION1)));
                HashPill.put(PILL_NOTIFICATION2, cursor.getString(cursor.getColumnIndex(PILL_NOTIFICATION2)));
                HashPill.put(PILL_NOTIFICATION3, cursor.getString(cursor.getColumnIndex(PILL_NOTIFICATION3)));
                HashPill.put(PILL_NOTIFICATION4, cursor.getString(cursor.getColumnIndex(PILL_NOTIFICATION4)));
                HashPill.put(PILL_DATE_START, cursor.getString(cursor.getColumnIndex(PILL_DATE_START)));
                ArrayPill.add(HashPill);
            } while (cursor.moveToNext());
        }
        return ArrayPill;
    }
}

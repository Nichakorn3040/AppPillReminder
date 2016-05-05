package com.pillreminder.pillreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

/**
 * Created by dml on 28/04/2016.
 */
public class DbPillStats extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pillstats";
    private static final int DATABASE_VERSION = 7;

    DbPillStats(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String PILL = "CREATE TABLE "+PILL_EATEN+" ("+
                PILL_ID+" INTEGER NOT NULL,"+
                PILL_NAME+" TEXT  NOT NULL,"+
                PILL_EATEN+" TEXT  NOT NULL,"+
                PILL_DATE_START+" DATE)";
        db.execSQL(PILL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + PILL_EATEN);
            onCreate(db);
        }
    }

    public ArrayList<HashMap<String, String>> SelectAllData() {
        ArrayList<HashMap<String, String>> ArrayPill;
        ArrayPill = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase database = this.getWritableDatabase();
        String sql = "SELECT "+PILL_ID+","+PILL_NAME+","+PILL_EATEN+","+PILL_DATE_START+" FROM "+PILL_EATEN + " ORDER BY " + PILL_DATE_START;
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> HashPill = new HashMap<String, String>();
                HashPill.put(PILL_ID, cursor.getString(cursor.getColumnIndex(PILL_ID)));
                HashPill.put(PILL_NAME, cursor.getString(cursor.getColumnIndex(PILL_NAME)));
                HashPill.put(PILL_EATEN, cursor.getString(cursor.getColumnIndex(PILL_EATEN)));
                HashPill.put(PILL_DATE_START, cursor.getString(cursor.getColumnIndex(PILL_DATE_START)));
                ArrayPill.add(HashPill);
            } while (cursor.moveToNext());
        }
        return ArrayPill;
    }

    public long InsertData(String Code, String Name, String Status, String date){
        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); //write data

            ContentValues Val = new ContentValues();
            Val.put(PILL_ID, Code);
            Val.put(PILL_NAME, Name);
            Val.put(PILL_EATEN, Status);
            Val.put(PILL_DATE_START, date);

            long rows = db.insert(PILL_EATEN, null, Val);
            db.close();
            return rows; //return rows inserted.
        } catch (Exception e) {
            Log.e("InsertData", "error", e);
            return -1;
        }
    }



}


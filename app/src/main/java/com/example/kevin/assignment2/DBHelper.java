package com.example.kevin.assignment2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kevin on 11/20/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String TABLE="ExpenseTable";
    public static final int VERSION=1;
    public static final String ID = "_id";
    public static final String DESCRIPTION = "description";
    public static final String NOTE = "note";
    public static final String TIME = "time";
    private final Context context;


    private static final String CREATE_QUERY =
            "CREATE TABLE IF NOT EXISTS " + TABLE +  " ( " + ID + " integer primary key, "
                    + DESCRIPTION +" not null , "
                    + NOTE +" not null, "
                    + TIME +" not null);";


    public DBHelper(Context context) {
        super(context, TABLE, null, VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }


    public void insert(ExpenseLogEntryData log) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+TABLE+" (description, note, time) VALUES ('" +
                log.getDescription() + "', '" + log.getNote() + "', '" +log.getTime() + "');");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void fakeData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String dCount="description ";
        String nCount="note ";
        for(int i=1; i<=10; i++) {
            ExpenseLogEntryData log=new ExpenseLogEntryData(dCount+i, nCount+i);

            db.execSQL("INSERT INTO "+TABLE+" (description, note, time) VALUES ('" +
                    log.getDescription() + "', '" + log.getNote() + "', '" +log.getTime() + "');");
        }

    }



    public void deleteExpense(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE+" WHERE _id = '" + id + "'");
        db.close();
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE+";", null);
        return c;
    }



}

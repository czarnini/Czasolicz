package com.example.android.czasolicz.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Michał on 30.03.2017.
 */

public class ActivitiesHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Activities.db";
    private static final String SQL_CREATE_TABLES =

            " CREATE TABLE" + ActivitiesContract.Categories.TABLE_NAME + " ( " +
                    ActivitiesContract.Categories._ID + " INTEGER PRIMARY KEY, " +
                    ActivitiesContract.Categories.COLUMN_NAME_CATEGORY + " TEXT); " +


                    " CREATE TABLE " + ActivitiesContract.Activities.TABLE_NAME + " ( " +
                    ActivitiesContract.Activities._ID + " INTEGER PRIMARY KEY, " +
                    ActivitiesContract.Activities.COLUMN_NAME_ACTIVITY + " TEXT, " +
                    " FOREIGN KEY (" + ActivitiesContract.Activities.COLUMN_NAME_CATEGORY + ") " +
                    " REFERENCES (" + ActivitiesContract.Categories._ID + ")) " +

                    " CREATE TABLE " + ActivitiesContract.History.TABLE_NAME + " (" +
                    ActivitiesContract.History._ID + " INTEGER PRIMARY KEY, " +
                    ActivitiesContract.History.COLUMN_NAME_DATE + " DATETIME, " +
                    ActivitiesContract.History.COLUMN_NAME_DURATION + " REAL, " +
                    " FOREIGN KEY (" + ActivitiesContract.History.COLUMN_NAME_ACTIVITY + ") " +
                    " REFERENCES (" + ActivitiesContract.Activities._ID + ")); ";


    ActivitiesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.w(ActivitiesHelper.class.getName(), "PROBA UDPATETU");
    }


}

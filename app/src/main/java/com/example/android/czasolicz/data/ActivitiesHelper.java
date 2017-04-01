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


    public ActivitiesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ActivitiesContract.SQL_CREATE_TABLE_CAT);
        db.execSQL(ActivitiesContract.SQL_CREATE_TABLE_ACT);
        db.execSQL(ActivitiesContract.SQL_CREATE_TABLE_HIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.w(ActivitiesHelper.class.getName(), "PROBA UDPATETU");
    }


}

package com.example.android.czasolicz.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.czasolicz.Activity;

/**
 * Created by Michał on 30.03.2017.
 */

public class ActivitiesHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Activities.db";
    private SQLiteDatabase mDb;
    private Context mContext;
    private Cursor mCursor;


    public ActivitiesHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(ActivitiesContract.SQL_CREATE_TABLE_CAT);
        db.execSQL(ActivitiesContract.SQL_CREATE_TABLE_ACT);
        db.execSQL(ActivitiesContract.SQL_CREATE_TABLE_HIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        Log.w(ActivitiesHelper.class.getName(), "PROBA UDPATETU");
    }

    public void open()
    {
        mDb = this.getWritableDatabase();
    }

    public Cursor fetchAllActivites()
    {
        String[] columns = new String[]{ActivitiesContract.History.COLUMN_NAME_ACTIVITY,
                ActivitiesContract.History.COLUMN_NAME_DATE,
                ActivitiesContract.History.COLUMN_NAME_DURATION};

        mCursor = mDb.query(ActivitiesContract.History.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );
        if (mCursor != null) mCursor.moveToFirst();
        return mCursor;
    }

    public long createActivity(Activity activity)
    {
        ContentValues values = new ContentValues();
        values.put(ActivitiesContract.History.COLUMN_NAME_ACTIVITY, activity.getmName());
        values.put(ActivitiesContract.History.COLUMN_NAME_DATE, activity.getmDate());
        values.put(ActivitiesContract.History.COLUMN_NAME_DURATION, activity.getmDuration());
        return mDb.insert(ActivitiesContract.History.TABLE_NAME, null, values);
    }

    public void deleteActivityByID(int id)
    {
        mDb.delete(ActivitiesContract.History.TABLE_NAME,
                ActivitiesContract.History._ID + "=?", new String[]{String.valueOf(id)});
    }

    public void upDateActivityById(int id)
    {
        //@TODO implement update
    }

    public void deleteAllActivities()
    {
        mDb.delete(ActivitiesContract.History.TABLE_NAME, null, null);
    }

    public Cursor fetchActivitiesByDate()
    {
        //@TODO Implement for statistics
        return mCursor;
    }


    public Cursor fetchActivitiesByCategory()
    {
        //@TODO Implement for statistics
        return mCursor;
    }
}

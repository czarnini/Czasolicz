package com.example.android.czasolicz.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.czasolicz.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michał on 30.03.2017.
 */

public class ActivitiesHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Activities.db";
    private SQLiteDatabase mDb;
    private Context mContext;
    private Cursor mCursor;
    private myHelper dbHelper;

    public ActivitiesHelper(Context context)
    {
        mContext = context;
    }



    public void open()
    {
        dbHelper = new myHelper(mContext);
        mDb = dbHelper.getWritableDatabase();
    }


    public void insertDummyData()
    {

        HashMap<String, List<String>> childElements = new HashMap<>();

        ArrayList<String> categories = new ArrayList<>();
        categories.add("Produktywność");
        categories.add("Relaks");
        categories.add("Sport");
        List<String> produktywnosc = new ArrayList<>();
        produktywnosc.add("Praca");
        produktywnosc.add("Nauka");

        List<String> relaks = new ArrayList<>();
        relaks.add("Czytanie");
        relaks.add("Oglądanie telewizji");
        relaks.add("Internet");

        List<String> sport = new ArrayList<>();
        sport.add("Bieganie");
        sport.add("Jazda na rowerze");
        sport.add("Siłownia");

        childElements.put(categories.get(0), produktywnosc);
        childElements.put(categories.get(1), relaks);
        childElements.put(categories.get(2), sport);





        for (String i : categories)
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ActivitiesContract.Categories.COLUMN_NAME_CATEGORY, i);
            mDb.insert(ActivitiesContract.Categories.TABLE_NAME, null, contentValues);
        }


        for (String i : childElements.keySet())
        {

            for (String j : childElements.get(i))
            {
                ContentValues values = new ContentValues();
                values.put(ActivitiesContract.Activities.COLUMN_NAME_ACTIVITY, j);
                values.put(ActivitiesContract.Activities.COLUMN_NAME_CATEGORY, i);
                mDb.insert(ActivitiesContract.Activities.TABLE_NAME, null, values);
            }
        }
    }

    public long createActivity(Activity activity)
    {

        ContentValues values = new ContentValues();
        values.put(ActivitiesContract.History.COLUMN_NAME_ACTIVITY, activity.getmName());
        values.put(ActivitiesContract.History.COLUMN_NAME_DATE, activity.getmDate());
        values.put(ActivitiesContract.History.COLUMN_NAME_DURATION, activity.getmDuration());
        return mDb.insert(ActivitiesContract.History.TABLE_NAME, null, values);

    }


    public Cursor fetchAllActivites()
    {
        String[] columns = new String[]{
                ActivitiesContract.History._ID,
                ActivitiesContract.History.COLUMN_NAME_ACTIVITY,
                ActivitiesContract.History.COLUMN_NAME_DATE,
                ActivitiesContract.History.COLUMN_NAME_DURATION};

        Cursor historyCursor = mDb.query(ActivitiesContract.History.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );
        if (historyCursor != null)
        {
//            mCursor.moveToFirst();
            while (historyCursor.moveToNext())
            {
                String aaa = historyCursor.getString(1);
            }
        }
        return historyCursor;
    }

    public Cursor fetchAllCategories()
    {
        String[] columns = new String[]{
                ActivitiesContract.Categories.COLUMN_NAME_CATEGORY,
        };

        mCursor = mDb.query(ActivitiesContract.Categories.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );
        return mCursor;
    }

    public Cursor fetchActivitiesByDate()
    {
        //@TODO Implement for statistics
        return mCursor;
    }


    public Cursor fetchActivitiesNamesByCategory(String category)
    {
        String[] activitiesColumns = new String[]{ActivitiesContract.Activities.COLUMN_NAME_ACTIVITY};

        String[] activitiesWhereArgs = {category};
        Cursor activitiesCursor = mDb.query(
                ActivitiesContract.Activities.TABLE_NAME,
                activitiesColumns,
                ActivitiesContract.Activities.COLUMN_NAME_CATEGORY + "=?",
                activitiesWhereArgs,
                null,
                null,
                null);

        return activitiesCursor;
    }

    public Cursor fetchActivitiesByCategory(String category)
    {

        return null;
    }


    public void deleteActivityByID(int id)
    {
        mDb.delete(ActivitiesContract.History.TABLE_NAME,
                ActivitiesContract.History._ID + "=?", new String[]{String.valueOf(id)});
    }

    public void updateActivityById(int id)
    {
        //@TODO implement update
    }

    public void deleteAllActivities()
    {
        mDb.delete(ActivitiesContract.History.TABLE_NAME, null, null);
    }

    private class myHelper extends SQLiteOpenHelper
    {
        public myHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
    }



}

package com.example.android.czasolicz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.czasolicz.data.ActivitiesContract;
import com.example.android.czasolicz.data.ActivitiesHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michał on 21.03.2017.
 */

public class Model
{


    static public void populateExpandableList(Context context, List categories, HashMap childElements) {
        ActivitiesHelper mDbHelper = new ActivitiesHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String[] categoriesColumns = {ActivitiesContract.Categories.COLUMN_NAME_CATEGORY},
                activitiesColumns = {ActivitiesContract.Activities.COLUMN_NAME_ACTIVITY};

        Cursor categoryCursor = db.query(
                ActivitiesContract.Categories.TABLE_NAME,
                categoriesColumns,
                null,
                null,
                null,
                null,
                null);

        try {
            while (categoryCursor.moveToNext()) {
                categories.add(categoryCursor.getString(0));
                String[] activitiesWhereArgs = {categoryCursor.getString(0)};
                Cursor activitiesCursor = db.query(
                        ActivitiesContract.Activities.TABLE_NAME,
                        activitiesColumns,
                        ActivitiesContract.Activities.COLUMN_NAME_CATEGORY + "=?",
                        activitiesWhereArgs,
                        null,
                        null,
                        null);
                List<String> tmp = new ArrayList<>();
                try {
                    while (activitiesCursor.moveToNext()) {
                        tmp.add(activitiesCursor.getString(0));
                    }
                } finally {
                    childElements.put(categories.get(categories.size() - 1), tmp);
                    activitiesCursor.close();
                }
            }
        } finally {
            categoryCursor.close();
        }
    }

    static public void insertDummyData(Context context) {

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

        context.deleteDatabase(ActivitiesHelper.DATABASE_NAME);

        ActivitiesHelper mDbHelper = new ActivitiesHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        for (String i : categories) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ActivitiesContract.Categories.COLUMN_NAME_CATEGORY, i);
            db.insert(ActivitiesContract.Categories.TABLE_NAME, null, contentValues);
        }


        for (String i : childElements.keySet()) {

            for (String j : childElements.get(i)) {
                ContentValues values = new ContentValues();
                values.put(ActivitiesContract.Activities.COLUMN_NAME_ACTIVITY, j);
                values.put(ActivitiesContract.Activities.COLUMN_NAME_CATEGORY, i);
                db.insert(ActivitiesContract.Activities.TABLE_NAME, null, values);
            }
        }
    }

    static public void insertHistory(ContentValues values, Context context) {
        ActivitiesHelper mDbHelper = new ActivitiesHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.insert(ActivitiesContract.History.TABLE_NAME, null, values);
    }

    static public void listHistory(Context context) {
        ActivitiesHelper mDbHelper = new ActivitiesHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String[] columns = {ActivitiesContract.History.COLUMN_NAME_ACTIVITY, ActivitiesContract.History.COLUMN_NAME_DATE, ActivitiesContract.History.COLUMN_NAME_DURATION};
        Cursor historyCursor = db.query(
                ActivitiesContract.History.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);
        try {
            while (historyCursor.moveToNext()) {
                Log.d("HISTORY:", historyCursor.getString(0) + historyCursor.getString(1) + historyCursor.getString(2));
            }
        } finally {
            historyCursor.close();
        }
    }
}

package com.example.android.czasolicz.data;

import android.provider.BaseColumns;

/**
 * Created by Michał on 30.03.2017.
 */

public final class ActivitiesContract {
    public static final String SQL_CREATE_TABLE_CAT =
            " CREATE TABLE " + Categories.TABLE_NAME + " ( " +
                    Categories.COLUMN_NAME_CATEGORY + " TEXT PRIMARY KEY); ";

    public static final String SQL_CREATE_TABLE_ACT =
            " CREATE TABLE " + Activities.TABLE_NAME + " ( " +
                    Activities.COLUMN_NAME_ACTIVITY + " TEXT PRIMARY KEY, " +
                    Activities.COLUMN_NAME_CATEGORY + " TEXT, " +
                    " FOREIGN KEY (" + Activities.COLUMN_NAME_CATEGORY + ") " +
                    " REFERENCES " + Categories.TABLE_NAME + "(" + Categories.COLUMN_NAME_CATEGORY + ")); ";

    public static final String SQL_CREATE_TABLE_HIST =
            " CREATE TABLE " + History.TABLE_NAME + " (" +
                    History._ID + " INTEGER PRIMARY KEY, " +
                    History.COLUMN_NAME_DATE + " DATETIME, " +
                    History.COLUMN_NAME_DURATION + " REAL, " +
                    History.COLUMN_NAME_ACTIVITY + " TEXT, " +
                    " FOREIGN KEY (" + History.COLUMN_NAME_ACTIVITY + ") " +
                    " REFERENCES " + Activities.TABLE_NAME + "(" + Activities.COLUMN_NAME_ACTIVITY + ")); ";

    private ActivitiesContract() {
    }

    public static class Categories implements BaseColumns {
        public static final String TABLE_NAME = "CATEGORIES";
        public static final String COLUMN_NAME_CATEGORY = "CATEGORY_NAME";
    }

    public static class Activities implements BaseColumns {
        public static final String TABLE_NAME = "ACTIVITIES";
        public static final String COLUMN_NAME_CATEGORY = "CAT_ID";
        public static final String COLUMN_NAME_ACTIVITY = "ACTIVITY_NAME";
    }


    public static class History implements BaseColumns {
        public static final String TABLE_NAME = "HISTORY";
        public static final String COLUMN_NAME_ACTIVITY = "ACT_ID";
        public static final String COLUMN_NAME_DATE = "DATE";
        public static final String COLUMN_NAME_DURATION = "DURATION";
    }


}

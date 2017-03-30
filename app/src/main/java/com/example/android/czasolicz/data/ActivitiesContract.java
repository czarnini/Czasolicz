package com.example.android.czasolicz.data;

import android.provider.BaseColumns;

/**
 * Created by Michał on 30.03.2017.
 */

public final class ActivitiesContract {
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

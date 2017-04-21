package com.example.android.czasolicz;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.android.czasolicz.data.ActivitiesContract;
import com.example.android.czasolicz.data.ActivitiesHelper;

public class History extends AppCompatActivity
{
    private ListView historyList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        historyList = (ListView) findViewById(R.id.HistoryList);
        String[] from = {ActivitiesContract.History.COLUMN_NAME_ACTIVITY, ActivitiesContract.History.COLUMN_NAME_DATE, ActivitiesContract.History.COLUMN_NAME_DURATION};
        int[] to = {R.id.historyActivityName, R.id.activityDate, R.id.activityDuration};
        ActivitiesHelper dbHelper = new ActivitiesHelper(this);
        dbHelper.open();
        Cursor historyCursor = dbHelper.fetchAllActivites();
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.history_list_element, historyCursor, from, to, 0);
        historyList.setAdapter(cursorAdapter);

    }

}

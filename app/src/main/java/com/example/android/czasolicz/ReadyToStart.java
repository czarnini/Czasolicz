package com.example.android.czasolicz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ReadyToStart extends AppCompatActivity {


    private static String activityName;

    public static String getActivityName() {
        return activityName;
    }

    public static void setActivityName(String activityName) {
        ReadyToStart.activityName = activityName;
    }

    @Override
    public void onBackPressed() {
        if (ReadyToStartFragment.isActivityStarted() == true) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        activityName = intent.getStringExtra("activityName");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_to_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}

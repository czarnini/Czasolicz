package com.example.android.czasolicz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.View.GONE;
import static com.example.android.czasolicz.ChooseActivity.chooseActivity;

public class ReadyToStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_to_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        String activityName = intent.getStringExtra("activityName");

        final Button startActivity = (Button) findViewById(R.id.StartActivity);
        final Button changeActivity = (Button) findViewById(R.id.ChangeActivity);

        final LinearLayout activityChoosen = (LinearLayout) findViewById(R.id.ActivityChosen);
        TextView activityNameTV = (TextView) findViewById(R.id.ActivityName);

        activityChoosen.setVisibility(View.VISIBLE);
        activityNameTV.setText(activityName);
        startActivity.setVisibility(View.VISIBLE);

        changeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ReadyToStart.this, ChooseActivity.class);
                startActivity(intent1);
            }
        });

        startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.PauseActivity).setVisibility(View.VISIBLE);
                findViewById(R.id.StopActivity).setVisibility(View.VISIBLE);
                startActivity.setVisibility(GONE);
                changeActivity.setVisibility(GONE);
                chooseActivity.finish();
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);

            }
        });


    }

}

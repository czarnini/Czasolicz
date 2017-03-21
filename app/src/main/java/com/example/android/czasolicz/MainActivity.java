package com.example.android.czasolicz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String activityName = intent.getStringExtra("activityName");
        if (activityName != null)
        {
            Button       chooseActivity   = (Button) findViewById(R.id.ChooseActivity);
            final Button       startActivity    = (Button) findViewById(R.id.StartActivity);
            final Button       changeActivity   = (Button) findViewById(R.id.ChangeActivity);

            LinearLayout activityChoosen  = (LinearLayout) findViewById(R.id.ActivityChoosen);
            TextView     activityNameTV   = (TextView) findViewById(R.id.ActivityName);

            chooseActivity.setVisibility(GONE);
            activityChoosen.setVisibility(View.VISIBLE);
            activityNameTV.setText(activityName);
            startActivity.setVisibility(View.VISIBLE);

            changeActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(MainActivity.this, ChooseActivity.class);
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

                }
            });
        }
        else
        {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

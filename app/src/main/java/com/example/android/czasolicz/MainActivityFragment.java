package com.example.android.czasolicz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.czasolicz.data.ActivitiesHelper;

import static android.view.View.GONE;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment
{

    private Button chooseActivityButton;

    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        chooseActivityButton = (Button) rootView.findViewById(R.id.ChooseActivity);



        chooseActivityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChooseActivity.class);
                startActivity(intent);
            }
        });

        final Button showHistory = (Button) rootView.findViewById(R.id.showHistory);
        showHistory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), History.class);
                startActivity(intent);
            }
        });
        ActivitiesHelper dbHelper = new ActivitiesHelper(getContext());
        dbHelper.open();
        Activity first = dbHelper.fetchFirstActivity();
        TextView lastActivityInfo = (TextView) rootView.findViewById(R.id.lastActivityInfo);
        TextView lastActivityDuration = (TextView) rootView.findViewById(R.id.lastActivityDuration);
        TextView lastActivityName = (TextView) rootView.findViewById(R.id.lastActivityName);
        if (first == null)
        {
            lastActivityInfo.setText(R.string.brakAktywnosciInfo);
        }
        else
        {
            long ms = first.getmDuration();
            String hh, mm, ss;
            int s = (int) ((ms / 1000) % 60);
            int m = (int) ((ms / 60000) % 60);
            int h = (int) ((ms / 3600000) % 60);

            if (s >= 10) ss = String.valueOf(s);
            else ss = "0" + String.valueOf(s);

            if (m >= 10) mm = String.valueOf(m) + ":";
            else mm = 0 + String.valueOf(m) + ":";

            if (h > 0) hh = String.valueOf(h) + ":";
            else hh = "";

            lastActivityInfo.setText(R.string.twoja_ostatnia_aktywnosc);
            lastActivityName.setText(first.getmName());
            lastActivityDuration.setText(hh + mm + ss);

        }

        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final View rootView = getView();
        Button chooseActivity = (Button) rootView.findViewById(R.id.ChooseActivity);
        final Button startActivity = (Button) rootView.findViewById(R.id.StartActivity);

        LinearLayout activityChosen = (LinearLayout) rootView.findViewById(R.id.ActivityChosen);
        TextView activityNameTV = (TextView) rootView.findViewById(R.id.activityName);

        String activityName = data.getStringExtra("activityName");

        chooseActivity.setVisibility(GONE);
        activityChosen.setVisibility(View.VISIBLE);
        activityNameTV.setText(activityName);



    }
}

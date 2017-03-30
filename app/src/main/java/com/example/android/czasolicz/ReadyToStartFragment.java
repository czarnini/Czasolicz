package com.example.android.czasolicz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.View.GONE;
import static com.example.android.czasolicz.ChooseActivity.chooseActivity;

public class ReadyToStartFragment extends Fragment {
    private static boolean isActivityStarted = false;
    private Button pauseButton;
    private Button stopButton;
    private Button startButton;
    private MyTimer timer;

    public static boolean isActivityStarted() {
        return isActivityStarted;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_ready_to_start, container, false);
        pauseButton = (Button) rootView.findViewById(R.id.PauseActivity);
        stopButton = (Button) rootView.findViewById(R.id.StopActivity);
        startButton = (Button) rootView.findViewById(R.id.StartActivity);
        timer = new MyTimer((TextView) rootView.findViewById(R.id.TimerValue));


        final Button changeActivity = (Button) rootView.findViewById(R.id.ChangeActivity);

        final LinearLayout activityChosen = (LinearLayout) rootView.findViewById(R.id.ActivityChosen);
        TextView activityNameTV = (TextView) rootView.findViewById(R.id.ActivityName);

        activityChosen.setVisibility(View.VISIBLE);
        activityNameTV.setText(ReadyToStart.getActivityName());
        startButton.setVisibility(View.VISIBLE);

        changeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), ChooseActivity.class);
                startActivity(intent1);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.startTimer();

                pauseButton.setVisibility(View.VISIBLE);
                stopButton.setVisibility(View.VISIBLE);
                startButton.setVisibility(GONE);

                changeActivity.setVisibility(GONE);
                chooseActivity.finish();
                ((AppCompatActivity) getActivity()).getSupportActionBar().
                        setDisplayHomeAsUpEnabled(false);
                isActivityStarted = true;
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseButton.setVisibility(View.GONE);
                startButton.setVisibility(View.VISIBLE);
                timer.pauseTimer();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //@TODO Zaimplementować listenera na stop -> zatrzymaj i
                // zapisz czas aktywności, odpal nową aktywność z podsumowaniem, zapisz w bazie sqLite


                // + Jak rozwiązać problem żeby utrzymać liczenie czasu mimo tego, że użytkownik "ubił apke"
            }
        });


        return rootView;
    }
}

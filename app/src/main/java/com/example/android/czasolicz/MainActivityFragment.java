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

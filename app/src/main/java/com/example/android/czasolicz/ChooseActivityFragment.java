package com.example.android.czasolicz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import static com.example.android.czasolicz.MainActivity.mainActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class ChooseActivityFragment extends Fragment {

    public ChooseActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_choose, container, false);

        ExpandableListUtility elu = new ExpandableListUtility(this.getContext());
        ExpandableListView expandableListView = (ExpandableListView) rootView.findViewById(R.id.ActivitiesList);
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(getContext()
                , elu.Categories(), elu.ChildElements());
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener( new  ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l)
            {

                TextView textView = (TextView) view.findViewById(R.id.list_item);
                String activityName = (String) textView.getText();
                Intent intent = new Intent(getActivity(), ReadyToStart.class);
                intent.putExtra("activityName", activityName);
                startActivity(intent);

                mainActivity.finish();

                return true;
            }
        });
        return rootView;


    }
}

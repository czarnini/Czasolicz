package com.example.android.czasolicz;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.android.czasolicz.data.ActivitiesHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.android.czasolicz.MainActivity.mainActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class ChooseActivityFragment extends Fragment
{

    private List<String> categories;
    private HashMap<String, List<String>> childElements;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_choose, container, false);

        populateExpandableList();
        ExpandableListView expandableListView = (ExpandableListView) rootView.findViewById(R.id.ActivitiesList);
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(getContext(), categories, childElements);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
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

    public void populateExpandableList()
    {
        categories = new ArrayList<>();
        childElements = new HashMap<>();
        ActivitiesHelper mDbHelper = new ActivitiesHelper(this.getContext());
        mDbHelper.open();
        Cursor categoryCursor = mDbHelper.fetchAllCategories();

        try
        {
            while (categoryCursor.moveToNext())
            {
                categories.add(categoryCursor.getString(0));
                Cursor activitiesCursor = mDbHelper.fetchActivitiesNamesByCategory(categoryCursor.getString(0));
                List<String> tmp = new ArrayList<>();
                try
                {
                    while (activitiesCursor.moveToNext())
                    {
                        tmp.add(activitiesCursor.getString(0));
                    }
                } finally
                {
                    childElements.put(categories.get(categories.size() - 1), tmp);
                    activitiesCursor.close();
                }
            }
        } finally
        {
            categoryCursor.close();
        }
    }
}

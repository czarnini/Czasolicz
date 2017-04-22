package com.example.android.czasolicz;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.czasolicz.data.ActivitiesContract;

/**
 * Created by Michał on 22.04.2017.
 */

public class ActivitiesSimpleCursorAdapter extends SimpleCursorAdapter
{

    public ActivitiesSimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags)
    {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return super.newView(context, cursor, parent);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        super.bindView(view, context, cursor);
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder == null)
        {
            viewHolder = new ViewHolder();
            viewHolder.listTab = (TextView) view.findViewById(R.id.activityDuration);
            viewHolder.duration = cursor.getColumnIndexOrThrow(ActivitiesContract.History.COLUMN_NAME_DURATION);
            view.setTag(viewHolder);
        }
        int ms = cursor.getInt(viewHolder.duration);

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

        viewHolder.listTab.setText(hh + mm + ss);

    }

    private static class ViewHolder
    {
        TextView listTab;
        int duration;
    }


}

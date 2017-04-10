package com.example.android.czasolicz;

/**
 * Created by Michał on 10.04.2017.
 */

public class Activity
{
    private String mName;
    private long mDuration;
    private String mDate;
    private long mId;

    Activity()
    {
        mName = mDate = null;
        mDuration = 0;

    }

    Activity(String name, long duration, String date)
    {
        mName = name;
        mDate = date;
        mDuration = duration;
    }

    public long getmId()
    {
        return mId;
    }

    public void setmId(long mId)
    {
        this.mId = mId;
    }

    public String getmName()
    {
        return mName;
    }

    public void setmName(String mName)
    {
        this.mName = mName;
    }

    public long getmDuration()
    {
        return mDuration;
    }

    public void setmDuration(long mDuration)
    {
        this.mDuration = mDuration;
    }

    public String getmDate()
    {
        return mDate;
    }

    public void setmDate(String mDate)
    {
        this.mDate = mDate;
    }
}




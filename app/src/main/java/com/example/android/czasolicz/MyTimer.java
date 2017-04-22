package com.example.android.czasolicz;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

public class MyTimer
{
    private long startTime = 0L;
    private long timeInMilliseconds = 0L;
    private long timeSwapBuff = 0L;
    private long updatedTime = 0L;
    private boolean stop = false;
    private Handler customHandler = new Handler();
    private TextView timerValue;
    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {


            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            String hh, mm, ss;
            int s = (int) ((updatedTime / 1000) % 60);
            int m = (int) ((updatedTime / 60000) % 60);
            int h = (int) ((updatedTime / 3600000) % 60);

            if (s >= 10) ss = String.valueOf(s);
            else ss = "0" + String.valueOf(s);

            if (m > 0) mm = String.valueOf(m) + ":";
            else mm = "";

            if (h > 0) hh = String.valueOf(h) + ":";
            else hh = "";

            timerValue.setText(hh + mm + ss);
            customHandler.postDelayed(this, 0);
        }
    };

    MyTimer(TextView timerValue) {
        this.timerValue = timerValue;
    }

    public void startTimer() {
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
    }

    public void pauseTimer() {
        timeSwapBuff += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);
    }

    public long stopTimer() {
        stop = true;
        return updatedTime;
    }


}

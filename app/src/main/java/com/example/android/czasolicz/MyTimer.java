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
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            timerValue.setText("" + mins + ":" + String.format("%02d", secs));
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

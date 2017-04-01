package com.example.android.czasolicz;

import android.app.Application;
import android.content.Context;

/**
 * Created by Michał on 31.03.2017.
 */

public class Czasolicz extends Application {
    private static Context context;

    public static Context getAppContext() {
        return Czasolicz.context;
    }

    public void onCreate() {
        super.onCreate();
        Czasolicz.context = getApplicationContext();
    }
}

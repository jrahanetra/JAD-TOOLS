package com.example.scannerapk;

import android.content.Context;
public class DataManagerSingleton {
    private static DataManager instance;

    public static synchronized DataManager getInstance(Context context) {
        if (instance == null) {
            instance = new DataManager(context.getApplicationContext());
        }
        return instance;
    }
}

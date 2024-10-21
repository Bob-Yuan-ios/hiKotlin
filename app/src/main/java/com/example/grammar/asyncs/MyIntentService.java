package com.example.grammar.asyncs;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {

    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String taskName = intent.getExtras().getString("taskName");
        switch (taskName){
            case "task1":
                Log.i("my111", "Intent do task1");
                break;
            case "task2":
                Log.i("my111", "Intent do task2");
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate() {
        Log.i("my111", "Intent onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i("my111", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("my111", "onDestroy");
        super.onDestroy();
    }
}

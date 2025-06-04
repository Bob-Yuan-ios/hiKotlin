package com.example.hello;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                Log.i("my111", "lifeCycle -> onActivityCreated" + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                Log.i("my111", "lifeCycle -> onActivityStarted" + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                Log.i("my111", "lifeCycle -> onActivityResumed" + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                Log.i("my111", "lifeCycle -> onActivityPaused" + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                Log.i("my111", "lifeCycle -> onActivityStopped" + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                Log.i("my111", "lifeCycle -> onActivitySaveInstanceState" + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                Log.i("my111", "lifeCycle -> onActivityDestroyed" + activity.getClass().getSimpleName());
            }
        });
    }

    
}

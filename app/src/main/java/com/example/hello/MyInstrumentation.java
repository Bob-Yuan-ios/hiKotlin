package com.example.hello;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/*
控制台执行指令
./gradlew clean assembleDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am instrucment -w com.example.hello/com.example.hello.MyInstrumentation


adb shell dumpsys package com.example.hello | grep instrumentation
adb logcat | grep Instrumentation
 */
public class MyInstrumentation extends Instrumentation {

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        Log.d("MyInstrumentation", "onCreate called");
        start();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("MyInstrumentation", "onStart called");
        // 如果你不启动 activity 或不退出，就会一直挂住
        finish(Activity.RESULT_OK, new Bundle());
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Log.i("MyInstrumentation", "- newActivity: " + className);
        return super.newActivity(cl, className, intent);
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        Log.i("MyInstrumentation", "- callActivityOnCreate: " + activity.getClass().getSimpleName());
        super.callActivityOnCreate(activity, icicle);
    }

    @Override
    public void callActivityOnResume(Activity activity) {

        Log.i("MyInstrumentation", "- callActivityOnResume: " + activity.getClass().getSimpleName());
        super.callActivityOnResume(activity);
    }
}

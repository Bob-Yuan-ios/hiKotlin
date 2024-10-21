package com.example.grammar.asyncs;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class MyHandler extends Handler {

    @Override
    public void handleMessage(@NonNull Message msg) {
        switch (msg.what){
            case 1:
                Log.i("my111", "执行线程1的指令");
                break;
            case 2:
                Log.i("my111", "执行线程2的指令");
                break;
        }
    }
}

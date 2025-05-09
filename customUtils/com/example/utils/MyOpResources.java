package com.example.utils;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.example.hello.MyApplication;

public class MyOpResources {

    /*
    *  读取字符串
    * */
    @NonNull
    public static String getString(@StringRes int stringId){
        return MyApplication.getAppContext().getString(stringId);
    }

    public static int getColor(@ColorRes int colorId){
        return MyApplication.getAppContext().getColor(colorId);
    }
}

package com.example.utils;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

public class MyOpResources {

    /*
    *  读取字符串
    * */
    @NonNull
    public static String getString(@StringRes int stringId){
        return MyApplication.getAppContext().getString(stringId);
    }
}

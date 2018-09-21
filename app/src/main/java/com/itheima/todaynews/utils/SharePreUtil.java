package com.itheima.todaynews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lou on 2018/6/23.
 */

public class SharePreUtil {
    private static SharedPreferences config;
    public static String getString(Context context,String key,String defvalue){
        if(config == null) {
            config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
       return  config.getString(key,defvalue);

    }
    public static void saveString(Context context,String key,String value){
        if(config == null){
            config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        config.edit().putString(key,value).commit();
    }
}

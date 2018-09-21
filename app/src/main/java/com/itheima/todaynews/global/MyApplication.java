package com.itheima.todaynews.global;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Lou on 2018/6/25.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}

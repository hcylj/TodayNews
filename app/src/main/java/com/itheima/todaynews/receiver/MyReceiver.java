package com.itheima.todaynews.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.itheima.todaynews.activity.NewDetailActivity;
import com.itheima.todaynews.bean.NewsTabBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import cn.jpush.android.api.JPushInterface;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Lou on 2018/6/26.
 */

public class MyReceiver extends BroadcastReceiver {

    private NewsTabBean.NewsBean newsBean;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - " + intent.getAction());


         if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");
            String json = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.d(TAG,"用户点击打开了通知  json = " + json);
             try {
                 JSONObject jsonObject = new JSONObject(json);
                 String url = jsonObject.getString("url");
                  newsBean = new NewsTabBean.NewsBean();
                 newsBean.setUrl(url);
                 newsBean.setId(1+new Random().nextInt(100000));
             } catch (JSONException e) {
                 e.printStackTrace();
             }


             // 在这里可以自己写代码去定义用户点击后的行为
            Intent openIntent = new Intent(context, NewDetailActivity.class);  //自定义打开的界面
            openIntent.putExtra("newsBean",newsBean);
            openIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(openIntent);
        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }
}

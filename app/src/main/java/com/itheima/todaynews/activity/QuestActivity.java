package com.itheima.todaynews.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Slide;
import android.transition.Transition;

import com.itheima.todaynews.R;

/**
 * Created by Lou on 2018/6/20.
 */

public class QuestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition slide = new Slide();
            slide.setDuration(1000);
            getWindow().setEnterTransition(slide);//进入时使用特效
            getWindow().setExitTransition(slide);//退出时使用特效

        }
    }
}
package com.itheima.todaynews.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.itheima.todaynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lou on 2018/6/20.
 */

public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.videoview)
    VideoView videoview;
    @BindView(R.id.tv_enter)
    TextView tvEnter;
    private boolean isEnter = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5b307df6");


        tvEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //已经进入了应用程序的主界面在视频播放结束后没有必要进入程序主界面
                isEnter = true;
                //一旦点击跳过的按钮，同样需要进入应用程序主界面
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                 finish();

           }
        });
        initVideo();
        initView();
    }

    private void initView() {
        //1.如何获取屏幕宽高
        int heightPixels = getResources().getDisplayMetrics().heightPixels;
        int widthPixels = getResources().getDisplayMetrics().widthPixels;

        //由vidioview父容器指定内部子控件的宽高
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                widthPixels,
                heightPixels);
        //设定规则，视频的左边缘和屏幕左边缘对象
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        //让包含了规则的params设定在videoview中
        videoview.setLayoutParams(params);
    }


    private void initVideo() {
        //1.视频文件在存在工程中存储,指定加载文件路径
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kr36);
        //2.告知videoview播放指定路径下商品
        videoview.setVideoURI(uri);
        //3.让videoview播放视频
        videoview.start();
        //视频播放完成后，需要跳转到后一个界面
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
        //在视频播放完成后，可以跳转到应用程序主界面
                if (isEnter){
                    return;
                }

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}

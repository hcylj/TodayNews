package com.itheima.todaynews.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.itheima.todaynews.R;
import com.itheima.todaynews.fragment.HomeFragment;
import com.itheima.todaynews.fragment.PicFragment;
import com.itheima.todaynews.fragment.UseFragment;
import com.itheima.todaynews.fragment.VideoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rg_group)
    RadioGroup rgGroup;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_video)
    RadioButton rbVideo;
    @BindView(R.id.btn_asq)
    Button btnAsq;
    @BindView(R.id.rb_pic)
    RadioButton rbPic;
    @BindView(R.id.rb_user)
    RadioButton rbUser;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //  进入应用时需要默认进入首页
        rgGroup.check(R.id.rb_home);
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fl_container, homeFragment).commit();

        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //在选中条目发生改变的时候调用方法checkId在调用这个方法的时候选中条目的id值
                switch (checkedId) {
                    case R.id.rb_home:
                        Log.i("", "home");
                        homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.fl_container, homeFragment).commit();

                        break;
                    case R.id.rb_video:
                        Log.i("", "video");
                        VideoFragment videoFragment = new VideoFragment();
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.fl_container, videoFragment).commit();

                        break;
                    case R.id.rb_pic:
                        Log.i("", "pic");
                        PicFragment picFragment = new PicFragment();
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.fl_container, picFragment).commit();


                        break;
                    case R.id.rb_user:
                        Log.i("", "user");

                        UseFragment useFragment = new UseFragment();
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.fl_container, useFragment).commit();

                        break;
                }


            }
        });
    }

    @OnClick(R.id.btn_asq)
    public void onViewClicked() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            //创建动画，指定时长
            Transition slide = new Slide();
            slide.setDuration(1000);
            //指定activity进入和出去的动画效果
            getWindow().setEnterTransition(slide);
            getWindow().setExitTransition(slide);
            //开启新界面
            Intent intentSlide = new Intent(MainActivity.this, QuestActivity.class);
            startActivity(intentSlide,
                    ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());


        }
    }
}

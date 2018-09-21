package com.itheima.todaynews.persenter;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itheima.todaynews.adapter.MyFragmentPagerAdapter;
import com.itheima.todaynews.bean.NewBean;
import com.itheima.todaynews.bean.ResponseInfo;
import com.itheima.todaynews.fragment.HomeFragment;
import com.itheima.todaynews.fragment.NewTabFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by Lou on 2018/6/21.
 */

public class HomePresenter extends BasePersenter {
    private HomeFragment homeFragment;
    private List<NewBean> newTabList;
    private List<Fragment> fragmentList;
    private List<String> titleList;

    public HomePresenter(HomeFragment homeFragment) {

        this.homeFragment = homeFragment;
    }

    @Override
    protected void showErrowMessage(String message) {

        Log.i("", "message = " + message);
    }

    @Override
    protected void ParseJson(String json) {
        Gson gson = new Gson();
        newTabList = gson.fromJson(json, new TypeToken
                <List<NewBean>>() {
        }.getType());
        initData();

    }

    private void initData() {
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        //维护tabLayout需要显示文字内容集合，集合中对象多少个则viewpager中页面就有多少个
        for (int i = 0; i < newTabList.size(); i++) {
            titleList.add(newTabList.get(i).getTitle());
            NewTabFragment newTabFragment = new NewTabFragment();
            newTabFragment.setUrl(newTabList.get(i).getUrl());
            fragmentList.add(newTabFragment);
        }
        //给viewpager设置数据适配器
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(
                homeFragment.getChildFragmentManager(), fragmentList, titleList);
        homeFragment.vp.setAdapter(myFragmentPagerAdapter);
        //让tabLayout和viewpager进行绑定，建立一个一一 对应的关系
        homeFragment.tabLayout.setupWithViewPager(homeFragment.vp);
    }

    //发送网络请求
    public void getHomeData() {
        Call<ResponseInfo> call = responseInfoApi.getHomeData();
        call.enqueue(new CallBackAdapter());
    }
}

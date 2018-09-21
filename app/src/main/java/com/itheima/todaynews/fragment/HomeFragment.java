package com.itheima.todaynews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itheima.todaynews.R;
import com.itheima.todaynews.activity.GridLayoutActivity;
import com.itheima.todaynews.persenter.HomePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Lou on 2018/6/20.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tabLayout)
    public TabLayout tabLayout;
    @BindView(R.id.vp)
    public ViewPager vp;
    Unbinder unbinder;
    @BindView(R.id.iv_add)
    ImageView ivAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //加载布局
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //请求网络获取数据，将数据填充在布局中
        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.getHomeData();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.iv_add)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), GridLayoutActivity.class);
          startActivity(intent);
    }
}

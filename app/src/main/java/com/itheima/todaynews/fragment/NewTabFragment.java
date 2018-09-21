package com.itheima.todaynews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.todaynews.R;
import com.itheima.todaynews.activity.MainActivity;
import com.itheima.todaynews.persenter.NewTabPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Lou on 2018/6/21.
 */

public class NewTabFragment extends BaseFragment {


    @BindView(R.id.pull_refresh_list)
    PullToRefreshListView pullToRefreshListView;
    Unbinder unbinder;
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_new_tab, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        NewTabPresenter newTabPresenter = new NewTabPresenter
                ((MainActivity) getActivity(),url,pullToRefreshListView);
        newTabPresenter.getNewTabData();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

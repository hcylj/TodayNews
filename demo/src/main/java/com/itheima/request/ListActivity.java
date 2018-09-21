package com.itheima.request;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * Created by Lou on 2018/6/23.
 */

public class ListActivity extends Activity {
    private PullToRefreshListView pullToRefreshListView;
    private  ArrayList<String> stringList;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //1.获取支持下拉刷新上拉加载的自定义控件
 pullToRefreshListView =( PullToRefreshListView)findViewById(R.id.pull_refresh_list);
      //2.获取真实的listView 的对象
        ListView listView = pullToRefreshListView.getRefreshableView();
//3.给listView 设置数据适配器
        initData();
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        //4.开启下拉刷新上拉加载的过程
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
               //下拉刷新
                refreshData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//上拉加载
                getMoreData();
            }
        });
    }


    private void getMoreData() {
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    stringList.add(0,"add refresh data...");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                myAdapter.notifyDataSetChanged();
                //告知下拉刷新上拉加载的listView刷新或者加载结束
                pullToRefreshListView.onRefreshComplete();

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void refreshData() {
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    stringList.add("add more data...");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myAdapter.notifyDataSetChanged();
                            //告知下拉刷新上拉加载的listView刷新或者加载结束
                            pullToRefreshListView.onRefreshComplete();

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return stringList.size();
        }

        @Override
        public String getItem(int position) {
            return stringList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(ListActivity.this);
            textView.setText(getItem(position));
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            return textView;
        }
    }
    private void initData() {
        stringList = new ArrayList<>();
        for(int i = 0;i<30; i++){
        stringList.add("string"+i);

        }


    }
}

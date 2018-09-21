package com.itheima.todaynews.persenter;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.todaynews.activity.MainActivity;
import com.itheima.todaynews.activity.NewDetailActivity;
import com.itheima.todaynews.adapter.NewsAdapter;
import com.itheima.todaynews.bean.NewsTabBean;
import com.itheima.todaynews.bean.ResponseInfo;
import com.itheima.todaynews.fragment.NewTabFragment;
import com.itheima.todaynews.utils.Constant;
import com.itheima.todaynews.utils.SharePreUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by Lou on 2018/6/21.
 */

public class NewTabPresenter extends BasePersenter{
    private  MainActivity activity;
    private PullToRefreshListView pullToRefreshListView;
    private  String url;
    private NewsTabBean newsTabBean;
    private  ArrayList<NewsTabBean.NewsBean> tempList;
    private  ListView lvNews;
    private boolean isRefresh;
    private String moreUrl;
    private NewsAdapter newsAdapter;
    public NewTabPresenter(MainActivity mainActivity, String url,
                           PullToRefreshListView pullToRefreshListView) {
        this.activity = mainActivity;
        this.url = url;
        this.pullToRefreshListView = pullToRefreshListView;
        tempList = new ArrayList<>();
    //获取pullToRefreshListView中真正的listView用于设置数据适配器
     lvNews = pullToRefreshListView.getRefreshableView();

    }

    @Override
    protected void showErrowMessage(String message) {
        Log.i("","请求失败");
        pullToRefreshListView.onRefreshComplete();
    }

    @Override
    protected void ParseJson(String json) {

        Gson gson = new Gson();
        newsTabBean = gson.fromJson(json, NewsTabBean.class);
         moreUrl = newsTabBean.getMore();
        initData();

    }

    private void initData() {
        List<NewsTabBean.NewsBean> newsList = newsTabBean.getNews();
        if(isRefresh){
            tempList.clear();
        }

        tempList.addAll(newsList);
        //先获取sp中记录已读新闻的id字符串
        String ids = SharePreUtil.getString(activity, Constant.IDS, "");
        String[] split =ids.split("#");
        //将字符串转换为集合
        ArrayList<String> stringList = new ArrayList<>();
        for(int i = 0;i<split.length;i++){
           stringList.add(split[i]) ;
        }
//判断集合中的id是否存在云tempList集合的每一个对象中
        for(int i = 0;i<tempList.size();i++){
            NewsTabBean.NewsBean newsBean = tempList.get(i);
            int id = newsBean.getId();
            if(stringList.contains(id+"")){
                //拥有id的newsBean对象是已读的新闻对象
            newsBean.setRead(true);
            }else{
                //拥有id的newsBean对象是未读的新闻对象

                newsBean.setRead(false);
            }
        }
//支持自上拉刷新上拉加载2种行动
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
//支持上拉下拉刷新
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
         newsAdapter = new NewsAdapter(tempList);
        lvNews.setAdapter(newsAdapter);

        pullToRefreshListView.onRefreshComplete();
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //              Log.i("","position = "+position);
                NewsTabBean.NewsBean newsBean = tempList.get(position - 1);
                Intent intent =new Intent(activity, NewDetailActivity.class);
                intent.putExtra("newsBean",newsBean);
                activity.startActivity(intent);
               //如果新闻已读，则需要将新闻唯一性的id记录在本地
        String ids =  SharePreUtil.getString(activity,Constant.IDS,"");
           if(!newsBean.isRead()){
               newsBean.setRead(true);
               SharePreUtil.saveString(activity,Constant.IDS,newsBean.getId()+"#"+ids);
//将变更后的nesBean通知数据适配器更新
               newsAdapter.notifyDataSetChanged();
           }
            }
        });
    }

    private void getMoreData() {

       isRefresh = false;
       if(!TextUtils.isEmpty(moreUrl)) {
           String[] strArray = moreUrl.split("\\/");
           String dirPath = strArray[0];
           String fileName = strArray[1];
           if (!TextUtils.isEmpty(dirPath) && !TextUtils.isEmpty(fileName)) {
               Call<ResponseInfo> call = responseInfoApi.getNawTabData(dirPath, fileName);
               call.enqueue(new CallBackAdapter());
           } else {
               hiddenFooter();

           }
       }else{
                hiddenFooter();
       }
    }

    private void hiddenFooter() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pullToRefreshListView.onRefreshComplete();
                            Toast.makeText(activity,"没有下一页数据",Toast.
                                    LENGTH_SHORT).show();

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void refreshData() {

        isRefresh = true;
        getNewTabData();

    }

    public void getNewTabData(){
        String[] strArray = url.split("/");
        String dirPath = strArray[0];
        String fileName = strArray[1];
if(!TextUtils.isEmpty(dirPath)&& !TextUtils.isEmpty(fileName)) {
    Call<ResponseInfo> call = responseInfoApi.getNawTabData(dirPath, fileName);
    call.enqueue(new CallBackAdapter());
}

    }


}

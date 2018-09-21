package com.itheima.todaynews.persenter;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.itheima.todaynews.adapter.PicAdapter;
import com.itheima.todaynews.bean.PicBean;
import com.itheima.todaynews.bean.ResponseInfo;

import retrofit2.Call;

/**
 * Created by Lou on 2018/6/26.
 */

public class PicPresent extends BasePersenter{
    private  ListView lvPic;
    private  Activity activity;
    private PicBean picBean;

    public PicPresent(Activity activity,ListView lvPic) {
        this.activity = activity;
        this.lvPic = lvPic;
    }

    @Override
    protected void showErrowMessage(String message) {
        Log.i("","请求异常");
    }

    @Override
    protected void ParseJson(String json) {
       // Log.i("","请求成功 json = "+json);
        Gson gson = new Gson();
         picBean = gson.fromJson(json, PicBean.class);
        PicAdapter picAdapter = new PicAdapter(activity,picBean.getNews());
        lvPic.setAdapter(picAdapter);


    }
    public void getPicData(){
        Call<ResponseInfo> call = responseInfoApi.getPicData("photos", "photos_1.json");
   call.enqueue(new CallBackAdapter());

    }
}

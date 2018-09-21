package com.itheima.todaynews.persenter;

import com.itheima.todaynews.utils.Constant;
import com.itheima.todaynews.bean.ResponseInfo;
import com.itheima.todaynews.net.ResponseInfoApi;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lou on 2018/6/21.
 */

public abstract class BasePersenter {
   public ResponseInfoApi responseInfoApi;
    private HashMap<Integer,String> errorMap = new HashMap<>();
    public BasePersenter(){
        errorMap = new HashMap<>();
        errorMap.put(404,"请求链接地址无效");
        errorMap.put(500,"请求参数无效");
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASEURL)
                .build();
         responseInfoApi = retrofit.create(ResponseInfoApi.class);

    }
    //处理一个请求成功或失败的类
    class CallBackAdapter implements Callback<ResponseInfo>{

        @Override
        public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
           //服务器返回的json数据，data字段中的数据才是有意义的
            ResponseInfo responseInfo = response.body();

            int retcode = responseInfo.getRetcode();
            if(retcode==200){
                String json = responseInfo.getData();
                //json解析展示不同页面返回的json都有差异所以无法在此处做出具体的json解析过程
                //将具体的解析过程交由具体子类进行处理
                ParseJson(json);
            }else{
                //服务器返回
                String errorMessage = errorMap.get(retcode);
                //自定义异常类型，用于告知此异常是自己通过状态码获取出来的
                onFailure(call,new RuntimeException(errorMessage));
            }
        }

        @Override
        public void onFailure(Call<ResponseInfo> call, Throwable t) {
        if(t instanceof RuntimeException){
            //将异常字符串获取出来，交由子类处理
            showErrowMessage(t.getMessage());
        }
            showErrowMessage("服务器忙请重试");
        }
    }

    protected abstract void showErrowMessage(String message);

    protected  abstract void ParseJson(String json);
}

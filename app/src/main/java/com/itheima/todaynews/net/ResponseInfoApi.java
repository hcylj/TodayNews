package com.itheima.todaynews.net;

import com.itheima.todaynews.bean.ResponseInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Lou on 2018/6/21.
 */

public interface ResponseInfoApi {
@GET("home.json")
Call<ResponseInfo> getHomeData();

@GET("{dirpath}/{filename}")
Call<ResponseInfo>  getNawTabData(@Path("dirpath")
               String dirPath,@Path("filename")String filename);

    @GET("{dirpath}/{filename}")
    Call<ResponseInfo> getPicData(@Path("dirpath")
                String dirPath,@Path("filename")String filename);

}

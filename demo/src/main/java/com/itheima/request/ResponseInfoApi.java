package com.itheima.request;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by Lou on 2018/6/19.
 */
 public interface ResponseInfoApi {
    //完整请求链接地址 请求方式(get post)请求参数 请求结果
    //http://httpbin.org/get?name"老师"
   @GET("get")
   Call<ResponseBody> getDate(@Query("name")String name);

    //http://httpbin.org/get?name"老师"&age="18"
    @FormUrlEncoded()
   @POST("post")
    Call<ResponseBody>postData(@Field("name")
         String name,@Field("age")String age);


    @FormUrlEncoded()
    @POST("post")
    Call<ResponseBody> postMapData(@FieldMap() Map<String,String> map);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("post")
    Call<ResponseBody> postJson(@Body RequestBody body);


    //post请求上传文件
    //@Multipart  多部件,代表需要上传文件
    @Multipart
    @POST("post")
    Call<ResponseBody> uploadFile(@Part MultipartBody.Part file);


    //post请求上传多张图片
    @Multipart
    @POST("post")
    Call<ResponseBody> uploadMapFiles(
            @PartMap() Map<String, RequestBody> maps);





}

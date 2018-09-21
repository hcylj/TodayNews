package com.itheima.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_get)
    Button btnGet;
    @BindView(R.id.btn_post)
    Button btnPost;
    @BindView(R.id.btn_post_map)
    Button btnPostMap;
    @BindView(R.id.btn_post_json)
    Button btnPostJson;
    @BindView(R.id.btn_post_file)
    Button btnPostFile;
    @BindView(R.id.btn_post_files)
    Button btnPostFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.btn_get, R.id.btn_post, R.id.btn_post_map, R.id.btn_post_json, R.id.btn_post_file, R.id.btn_post_files, R.id.activity_main})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                getData();
                break;
            case R.id.btn_post:
                postData();
                break;
            case R.id.btn_post_map:
                postMapData();
                break;
            case R.id.btn_post_json:
                postJsonData();
                break;
            case R.id.btn_post_file:
                postFile();
                break;
            case R.id.btn_post_files:
                postMapFiles();
                break;

        }
    }

    private void postMapFiles() {

        FileOutputStream fileOutputStream = null;
        FileOutputStream fileOutputStream1 = null;
        try {
            File file =   new File(getFilesDir(),"image1");

            fileOutputStream = new FileOutputStream(file);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);


            File file1 =   new File(getFilesDir(),"image2");
            fileOutputStream1 = new FileOutputStream(file1);
            Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.b);
            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream1);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://httpbin.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ResponseInfoApi responseInfoApi = retrofit.create(ResponseInfoApi.class);
            // 创建 RequestBody，用于封装构建RequestBody
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);

            // 创建 RequestBody，用于封装构建RequestBody
            RequestBody requestFile1 =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file1);

            HashMap<String, RequestBody> requestBodyMap = new HashMap<>();
            requestBodyMap.put("actimg",requestFile);
            requestBodyMap.put("listimage",requestFile1);

            Call<ResponseBody> call  =   responseInfoApi.uploadMapFiles(requestBodyMap);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.i("", "上传成功");

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    Log.i("", "上传失败");

                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    //上传图片
    private void postFile() {
//将图片准备到文件中去;


        try{
            File file =   new File(getFilesDir(),"image");

            FileOutputStream fileOutputStream = new FileOutputStream(file);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ResponseInfoApi responseInfoApi = retrofit.create(ResponseInfoApi.class);
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
// MultipartBody.Part和后端约定好Key，这里的partName是用actimg
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("actimg", file.getName(), requestFile);
        responseInfoApi.uploadFile(body);
        Call<ResponseBody> call = responseInfoApi.uploadFile(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("", "上传成功");

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.i("", "上传失败");

            }
        });


    } catch(FileNotFoundException e) {
        e.printStackTrace();
    }


    }

    private void postJsonData() {
//生成 一段json
        /*
        {
        "name":"student",
        "age":"18",
        "sex":"man"
        }
         */
       Student student = new Student("student","18","man","100001");
        Gson gson = new Gson();
        String json = gson.toJson(student);
        Log.i(""," student json = "+json);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://httpbin.org/")
                .build();
        ResponseInfoApi responseInfoApi =  retrofit.create(ResponseInfoApi.class);
        //需要将json串封装在requestBody中，才可以发送给服务器
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Call<ResponseBody> call = responseInfoApi.postJson(requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    ResponseBody responseBody = response.body();
                    String json = responseBody.string();
                    Log.i("","post json = "+json);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("","请求失败");
            }
        });

    }

    /**
     * 上传给服务器多个键值对封装在map集合中
     */
    private void postMapData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://httpbin.org/")
                .build();
        ResponseInfoApi responseInfoApi =  retrofit.create(ResponseInfoApi.class);
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("name","老师");
        paramMap.put("age","20");
        paramMap.put("sex","man");
        paramMap.put("userid","10000");
        Call<ResponseBody> call = responseInfoApi.postMapData(paramMap);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    ResponseBody responseBody = response.body();
                    String json = responseBody.string();
                    Log.i("","post map json = "+json);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("","请求失败");
            }
        });



    }

    private void postData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://httpbin.org/")
                .build();
        ResponseInfoApi responseInfoApi =  retrofit.create(ResponseInfoApi.class);
        Call<ResponseBody> call = responseInfoApi.postData("teacher","20");
         call.enqueue(new Callback<ResponseBody>() {
             @Override
             public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                 try {
                 ResponseBody responseBody = response.body();
                     String json = responseBody.string();
                     Log.i("","post json = "+json);
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

             }

             @Override
             public void onFailure(Call<ResponseBody> call, Throwable t) {
                 Log.i("","请求失败");
             }
         });



    }

    private void getData() {
        //1.创建retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                //将服务器返回数据json告知服务器返回的json数据通过是么json解析
                .addConverterFactory(GsonConverterFactory.create())
                //约定请求连接地址的前半部分
                //http://www.itheima.com/todaynew/home.jsp获取首页数据
                //http://www.itheima.com/todaynew/user.jsp获取个人数据
                .baseUrl("http://httpbin.org/")
                .build();
        //2.通过retrofit发送网络请求
        //完整请求链接地址 请求方式(get post)请求参数 请求结果
        ResponseInfoApi responseInfoApi = retrofit.create(ResponseInfoApi.class);
        //3.发送请求过程http://httpbin.org/get?name"teacher"
        Call<ResponseBody> call = responseInfoApi.getDate("teacher");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //请求服务器有响应的方法

                try {

                    ResponseBody responseBody = response.body();
                   String json =  responseBody.string();
                    Log.i("","json="+json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //请求服务器失败的方法
                Log.i("","请求失败");
            }
        });

    }
}

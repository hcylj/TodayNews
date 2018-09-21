package com.itheima.todaynews.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.itheima.todaynews.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Lou on 2018/6/26.
 */

public class MyBitmapNetUtil {
    private MyBitmapFileCacheUtil myBitmapFileCacheUtil;
    private Context context;
    private MyBitmapCacheUtil myBitmapCacheUtil;
    private  ImageView imageView;
    private String imgUrl;
    public MyBitmapNetUtil(Context context,
                           MyBitmapCacheUtil myBitmapCacheUtil,
                           MyBitmapFileCacheUtil myBitmapFileCacheUtil) {
        this.context = context ;
        this.myBitmapCacheUtil = myBitmapCacheUtil;
        this.myBitmapFileCacheUtil = myBitmapFileCacheUtil;


    }

public void setBitImage(ImageView imageView,String imgUrl){
        imageView.setImageResource(R.mipmap.pic_item_list_default);
        new MyAsyncTask().execute(imageView,imgUrl);


}

class MyAsyncTask extends AsyncTask<Object,String,Bitmap>{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(Object... params) {
         imageView = (ImageView) params[0];
         imgUrl = (String) params[1];

        Bitmap bitmap = getBitmapFromNet(imgUrl);
        if(bitmap !=null){
            myBitmapCacheUtil.saveBitmap(imgUrl ,bitmap );
            myBitmapFileCacheUtil.saveBitmap(imgUrl ,bitmap ) ;
        }

        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setTag(imgUrl);
            }
        });
        return bitmap ;
    }

    private Bitmap getBitmapFromNet(String imgUrl) {
        try {
            Log.i("","网络获取图片");
            URL url = new URL(imgUrl);
            HttpURLConnection connection  = (HttpURLConnection) url.openConnection();
            connection .setReadTimeout(2000);
            connection .setConnectTimeout(2000);
            if(connection.getResponseCode() == 200){
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
                return bitmap ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null) {
            String url = (String) imageView.getTag();
            if (!TextUtils.isEmpty(url) && url.equals(imgUrl)) {
                imageView.setImageBitmap(bitmap);
            }
        }
        super.onPostExecute(bitmap);
    }
}

}

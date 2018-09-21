package com.itheima.todaynews.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Lou on 2018/6/26.
 */

public class MyBitmapUtils {
    private MyBitmapCacheUtil myBitmapCacheUtil;
    private  MyBitmapFileCacheUtil myBitmapFileCacheUtil;
    private  MyBitmapNetUtil myBitmapNetUtil;

    public MyBitmapUtils(Context context ){
         myBitmapCacheUtil = new MyBitmapCacheUtil();
        myBitmapFileCacheUtil = new MyBitmapFileCacheUtil(context ,myBitmapCacheUtil );
        myBitmapNetUtil = new MyBitmapNetUtil(context,myBitmapCacheUtil,myBitmapFileCacheUtil);


    }
public void setImageBitmap(ImageView imageView, String imgUrl){
    Bitmap bitmap = myBitmapCacheUtil.getBitmap(imgUrl);
    if(bitmap!=null){
        imageView.setImageBitmap(bitmap);
        Log.i("","内存中获取图片");
        return ;
    }
    bitmap = myBitmapFileCacheUtil.getFileBitmap(imgUrl);
    if(bitmap!=null){
        imageView.setImageBitmap(bitmap);
        Log.i("","文件中获取图片");
        return ;
    }
    myBitmapNetUtil.setBitImage(imageView,imgUrl);
}


}

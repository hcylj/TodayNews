package com.itheima.todaynews.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Lou on 2018/6/26.
 */

class MyBitmapFileCacheUtil {
    private MyBitmapCacheUtil myBitmapCacheUtil;
    private Context context ;


    public MyBitmapFileCacheUtil(Context context, MyBitmapCacheUtil myBitmapCacheUtil) {
        this.context = context ;
        this.myBitmapCacheUtil  = myBitmapCacheUtil ;
    }
    public Bitmap  getFileBitmap(String url){
        String fileName = MD5Util.Md5(url);
        File file = new File(context.getFilesDir(), fileName);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
       if(bitmap!=null){
           myBitmapCacheUtil.saveBitmap(url,bitmap);
       }


        return bitmap;
    }
    public void saveBitmap(String url,Bitmap bitmap ){
        String fileName = MD5Util.Md5(url);
        File file = new File(context.getFilesDir(), fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }





}

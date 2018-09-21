package com.itheima.todaynews.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Lou on 2018/6/26.
 */

class MyBitmapCacheUtil {

    private final LruCache<String, Bitmap> bitmapLruCache;

    public MyBitmapCacheUtil(){
        int maxSize = (int) (Runtime.getRuntime().maxMemory()/8);
        bitmapLruCache = new LruCache<String, Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
               return  value.getRowBytes()*value.getHeight();
            }
        };

    }
    public void saveBitmap(String key,Bitmap bitmap){
        bitmapLruCache .put(key,bitmap );
    }

    public Bitmap getBitmap(String key){
        return bitmapLruCache .get(key);
    }

}

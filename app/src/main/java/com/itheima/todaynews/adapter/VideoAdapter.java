package com.itheima.todaynews.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.todaynews.view.ListItemView;
import com.itheima.todaynews.view.MyMediaPlayer;

/**
 * Created by Lou on 2018/6/27.
 */

public class VideoAdapter extends BaseAdapter  {
    private String uri;
    private  Activity activity;
    private ListItemView currentView;

    public VideoAdapter(Activity activity,String uri){
        this.uri = uri;
        this.activity = activity;

    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if(convertView == null){
           final ListItemView listItemView = new ListItemView(parent.getContext());
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(currentView != null){
                currentView.removeMyMediaPlayer();
            }
                MyMediaPlayer myMediaPlayer = new MyMediaPlayer(activity);
                myMediaPlayer.begin(uri) ;
                listItemView.addMyMedaiPlayer(myMediaPlayer);
           currentView = listItemView;
            }
        });
            convertView = listItemView;
       }else if(convertView!=null && convertView==currentView){
           currentView.removeMyMediaPlayer();

       }
        return convertView;
    }
}

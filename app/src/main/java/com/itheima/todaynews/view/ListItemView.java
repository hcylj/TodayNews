package com.itheima.todaynews.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.itheima.todaynews.R;

/**
 * Created by Lou on 2018/6/27.
 */

public class ListItemView extends FrameLayout {


    private MyMediaPlayer myMediaPlayer;

    public ListItemView(Context context) {
        this(context,null);
    }

    public ListItemView( Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public ListItemView( Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.list_item_view_item, null);
        ImageView ivBackground = view.findViewById(R.id.iv_backgound);
        ImageView ivPlay =(ImageView) view.findViewById(R.id.iv_play);
        addView(view);

    }

    public void addMyMedaiPlayer(MyMediaPlayer myMediaPlayer){
        this.myMediaPlayer = myMediaPlayer;
        addView(myMediaPlayer.getRootView());
    }
    public void removeMyMediaPlayer(){
        myMediaPlayer.release();
        removeView(myMediaPlayer.getRootView());


    }

}

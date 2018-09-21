package com.itheima.todaynews.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itheima.todaynews.R;

import java.io.IOException;

/**
 * Created by Lou on 2018/6/27.
 */

public class MyMediaPlayer implements View.OnClickListener {

    private  Context context;
    private  TextureView ttv;
    private  ImageView ivPlayPause;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    private Surface surface;
    private  View view;

    public MyMediaPlayer(Context context ){
        this.context = context;
        view = View.inflate(context , R.layout.item_media_player,null);
        ttv = view.findViewById(R.id.ttv);
        progressBar = view.findViewById(R.id.progressbar);
        ivPlayPause = view.findViewById(R.id.iv_play_pause);

        ttv.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
                 mediaPlayer = new MediaPlayer();
                surface = new Surface(surfaceTexture);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });

ivPlayPause.setOnClickListener(this);
    }
        public void begin(final String uri){
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            playVideo(uri);
            }
        },1000);
        }

    private void playVideo(String uri) {
        try {
            mediaPlayer.setDataSource(context, Uri.parse(uri));
        mediaPlayer .setSurface(surface );
        mediaPlayer .setLooping(true );
        mediaPlayer .setAudioStreamType(AudioManager.STREAM_MUSIC );
        mediaPlayer .prepareAsync() ;
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void pauseOrResume(){
            if(mediaPlayer !=null&&mediaPlayer.isPlaying()){
            mediaPlayer.pause() ;
            ivPlayPause .setImageResource(R.mipmap.play_icon);
            }else{
                mediaPlayer.start();
               ivPlayPause .setImageResource(R.mipmap.pause_video );
            }
    }

    @Override
    public void onClick(View v) {
        pauseOrResume();
    }

    public void release(){
        if(mediaPlayer != null){
            mediaPlayer.release();
        }
    }
    public View getRootView(){
        return view;
    }
}

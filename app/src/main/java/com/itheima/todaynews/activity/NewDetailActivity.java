package com.itheima.todaynews.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.itheima.todaynews.R;
import com.itheima.todaynews.bean.NewsTabBean;
import com.itheima.todaynews.db.DBHelper;
import com.j256.ormlite.dao.Dao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lou on 2018/6/23.
 */

public class NewDetailActivity extends AppCompatActivity {
    private WebSettings webSettings;
    private   NewsTabBean.NewsBean newsBean;
    private String[] str = new String[]{
      "超大","大号","普通","小号","极小"
    };
    //是否正在播报
    private boolean isSpeaking = false;
    //是否从头开始
    private boolean startSpeaking = true;
    private SpeechSynthesizer mTts;

    private SynthesizerListener mSynListener
            = new SynthesizerListener(){
        @Override
        public void onSpeakBegin() {

        }

        @Override
        public void onBufferProgress(int i, int i1, int i2, String s) {

        }

        @Override
        public void onSpeakPaused() {

        }

        @Override
        public void onSpeakResumed() {

        }

        @Override
        public void onSpeakProgress(int i, int i1, int i2) {

        }

        @Override
        public void onCompleted(SpeechError speechError) {
        startSpeaking = true;
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };




        @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_fav)
    ImageView ivFav;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.iv_textsize)
    ImageView ivTextsize;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.iv_talk)
    ImageView ivTalk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);
        ButterKnife.bind(this);

        mTts= SpeechSynthesizer.createSynthesizer(this, null);




        newsBean = (NewsTabBean.NewsBean) getIntent().getSerializableExtra("newsBean");
        webView.loadUrl(newsBean.getUrl());
        webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);//出现放大和缩小按钮
        webSettings.setUseWideViewPort(true);//支持双击放大（对wap网页不支持  ，pc:web）
        webSettings.setJavaScriptEnabled(true);//让js可用

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });

        webView.setWebChromeClient(new WebChromeClient());

        //参数一:实现接口类的对象,接口名称任意定义
        //参数二:接口的别名,别名需要和html中编写调用方法的类的名称一致
        webView.addJavascriptInterface(new JsCallAndroid() {
            @Override
            @JavascriptInterface
            public void back() {
                Toast.makeText(NewDetailActivity.this, "js调用了android 结束界面的代码", Toast.LENGTH_SHORT).show();
                finish();
            }
        }, "Android");



    }

    private void speak(String content) {
        //1.创建SpeechSynthesizer 对象, 第二个参数：本地合成时传InitListener
        //2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
        //设置发音人（更多在线发音人，用户可参见 附录13.2
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
        //设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");
        //设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");
        //设置音量，范围 0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        //设置云端
        //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
        //保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
        //仅支持保存为 pcm 和 wav 格式，如果不需要保存合成音频，注释该行代码
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
        //3.开始合成
        mTts.startSpeaking(content, mSynListener);
    }

    @OnClick({R.id.iv_back, R.id.iv_fav, R.id.iv_share, R.id.iv_textsize, R.id.iv_talk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_fav:
                fav();
                break;
            case R.id.iv_share:
                break;
            case R.id.iv_textsize:
                showTextSizeDialog();
                break;
            case R.id.iv_talk:
                //调用js代码弹出对对话框
               // webView.loadUrl("javascript:wave()");
                if(!isSpeaking){
        isSpeaking = true;
        if(startSpeaking){
            initSpeak();
        }else{
            mTts.resumeSpeaking();
        }


                }else{
              isSpeaking =false ;
            mTts.pauseSpeaking();

                }

              //  speak();
                break;
        }
    }

    private void initSpeak() {
//初始化要播报的文本内容，文本内容较多，解析文本过程耗时操作
     new Thread(){
         @Override
         public void run() {

             try {
                 //解析html中标签内容过程
                 Document document = Jsoup.parse(new URL(newsBean.getUrl()), 10000);
           //针对p标签进行解析
                 Elements elements = document.select("p");
//解析每一个p标签中的内容
                 Iterator<Element> iterator = elements.iterator();
                 StringBuilder stringBuilder = new StringBuilder();
                 //判断是否解析完条件，如果没有解析完则一直向下解析，解析完了跳出死循环
                 while(iterator.hasNext()){
                     Element element = iterator.next();
                     String content = element.text();
                     stringBuilder.append(content);
                 }
             Log.i("","stringBuilder.toString() = "+stringBuilder.toString());
                 String content = stringBuilder.toString();
                 startSpeaking = false;
                 speak(content);



             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }.start();


    }

    //定义一个接口，在接口中有一个和html中点击事件同名方法，js调用Android
    public interface JsCallAndroid{
        public void back();
    }

    private void fav() {
        //1.让建库建表操作执行
        try {
        DBHelper dbHelper = new DBHelper(this);
        //2.通过dbHelper 对象获取可以对t_news表进行增删改察
        Dao<NewsTabBean.NewsBean,Integer> dao = dbHelper.getDao(NewsTabBean.NewsBean.class);
    //3.通过Dao进行查询如果现在从前一个界面传递过来的newsBean对象
        //3.1已经存在数据库中则点击取消收藏

        //3.2如果没有存在，则点击收藏


            NewsTabBean.NewsBean bean = dao.queryForId(this.newsBean.getId());
            if (bean == null){
                Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                dao.create(newsBean);
            }else{
                Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                dao.delete(newsBean);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void showTextSizeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(str, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which ) {
            switch (which){
                case 0:
   //webSettings.setTextSize(WebSettings.TextSize.LARGEST);
      webSettings.setTextZoom(200);
                    break;


                case 1:
  //   webSettings.setTextSize(WebSettings.TextSize.LARGER);
                    webSettings.setTextZoom(150);
                    break;

                case 2:
    //  webSettings.setTextSize(WebSettings.TextSize.NORMAL);
                    webSettings.setTextZoom(100);
                    break;

                case 3:
    // webSettings.setTextSize(WebSettings.TextSize.SMALLER);
                    webSettings.setTextZoom(75);
                    break;

                case 4:
     //  webSettings.setTextSize(WebSettings.TextSize.SMALLEST);
                    webSettings.setTextZoom(50);
                    break;
            }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            }
        });
        builder.show();


    }
}

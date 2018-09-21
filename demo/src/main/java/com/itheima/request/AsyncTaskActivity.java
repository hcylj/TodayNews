package com.itheima.request;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lou on 2018/6/26.
 */

public class AsyncTaskActivity extends AppCompatActivity {


    @BindView(R.id.btn_start)
    Button btnStart;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        new MyAsyncTask().execute("aaa");
    }

    class MyAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            Log.i("", "onPreExecute");
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            Log.i("", "doInBackground.params[0] = " + params[0]);
            Log.i("","id="
            +Thread.currentThread().getId());
            return "bbb";
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("", "onPreExecute s =" + s);
            Log.i("","id="
                    +Thread.currentThread().getId());
            super.onPostExecute(s);
        }
    }
}

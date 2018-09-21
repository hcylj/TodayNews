package com.itheima.todaynews.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.itheima.todaynews.R;
import com.itheima.todaynews.adapter.NewsAdapter;
import com.itheima.todaynews.bean.NewsTabBean;
import com.itheima.todaynews.db.DBHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lou on 2018/6/25.
 */

public class FavListActivity extends AppCompatActivity {
    @BindView(R.id.lv_fav_news)
    ListView lvFavNews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_list);
        ButterKnife.bind(this);

        //从数据库中将收藏过的数据查询出来
       try {
            DBHelper dbHelper = new DBHelper(this);
            Dao<NewsTabBean.NewsBean,Integer> dao = dbHelper.getDao(NewsTabBean.NewsBean.class);
            List<NewsTabBean.NewsBean> newList = dao.queryForAll();

           NewsAdapter newsAdapter = new NewsAdapter((ArrayList<NewsTabBean.NewsBean>) newList);
           lvFavNews.setAdapter(newsAdapter);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

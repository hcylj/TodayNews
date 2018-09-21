package com.itheima.todaynews.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.todaynews.R;
import com.itheima.todaynews.bean.PicBean;
import com.itheima.todaynews.utils.MyBitmapUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lou on 2018/6/26.
 */

public class PicAdapter extends BaseAdapter {

    private List<PicBean.NewsBean> data;
    private ViewHolder viewHolder;
    private  MyBitmapUtils myBitmapUtils;

    public PicAdapter(Activity activity , List<PicBean.NewsBean> newsList) {
        this.data = newsList;
        myBitmapUtils = new MyBitmapUtils(activity);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public PicBean.NewsBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_news_pic, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PicBean.NewsBean newsBean = getItem(position);
        viewHolder.tvTitle.setText(newsBean.getTitle());
        viewHolder.ivImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        myBitmapUtils.setImageBitmap(viewHolder.ivImage,newsBean.getListimage());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

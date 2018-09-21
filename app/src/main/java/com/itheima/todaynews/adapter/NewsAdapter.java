package com.itheima.todaynews.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.todaynews.R;
import com.itheima.todaynews.bean.NewsTabBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;

/**
 * Created by Lou on 2018/6/22.
 */

public class NewsAdapter extends BaseAdapter {

    private static final int ITEM_TEXT_PIC = 0;//图片加文字
    private static final int ITEM_PIC = 1;//纯图片
    private ArrayList<NewsTabBean.NewsBean> data;

    public NewsAdapter(ArrayList<NewsTabBean.NewsBean> newsList) {
        this.data = newsList;
    }
//告知目前数据适配器条目有二种

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    //判断每一个索引的条目是什么类型

    @Override
    public int getItemViewType(int position) {
        NewsTabBean.NewsBean newsBean = data.get(position);
        if (newsBean.getType() == 0) {
            //返回单张条目类型状态码
            return ITEM_TEXT_PIC;
        } else {
            //返回多张条目类型状态码
            return ITEM_PIC;
        }
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public NewsTabBean.NewsBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == ITEM_TEXT_PIC) {
//填充图片加文本
            TextPicViewHolder textPicViewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(parent
                        .getContext(), R.layout.item_text_pic, null);
                textPicViewHolder = new TextPicViewHolder(convertView);
                convertView.setTag(textPicViewHolder);
            } else {
                textPicViewHolder = (TextPicViewHolder) convertView.getTag();
            }
            NewsTabBean.NewsBean bean = getItem(position);
            textPicViewHolder.tvTitle.setText(bean.getTitle());
            textPicViewHolder.tvTime.setText(bean.getPubdate());
//Glide图片异步加载框架加载服务器中的图片图片三级缓存
            Glide.with(parent.getContext())
                    .load(bean.getListimage())
            .placeholder(R.mipmap.ic_launcher)//加载过程中展示图片
                    .crossFade(1000)
                    .bitmapTransform(new RoundedCornersTransformation(parent.getContext(),40,0))
                    .bitmapTransform(new CropCircleTransformation(parent.getContext()))
                    .bitmapTransform(new PixelationFilterTransformation(parent.getContext()))
                    .bitmapTransform(new BlurTransformation(parent.getContext()))
                    .error(R.mipmap.speak)
                    .into(textPicViewHolder.ivImage);
            if(bean.isRead()) {
                //已读为红色
                textPicViewHolder.tvTitle.setTextColor(Color.RED);
                textPicViewHolder.tvTime.setTextColor(Color.RED);

            }else{
            //未读为黑色
                textPicViewHolder.tvTitle.setTextColor(Color.BLACK);
                textPicViewHolder.tvTime.setTextColor(Color.BLACK);

            }
       return convertView;
        } else {
//填充多张图片
            PicViewHolder picViewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(parent
                        .getContext(), R.layout.item_pic, null);
            picViewHolder = new PicViewHolder(convertView);
            convertView.setTag(picViewHolder);
            }else{
             picViewHolder = (PicViewHolder) convertView.getTag();
            }
            NewsTabBean.NewsBean  bean = getItem(position);
            picViewHolder.tvTime.setText(bean.getPubdate());

            Glide.with(parent.getContext()).load(bean.getListimage())
                    .into(picViewHolder.ivImage1);
            Glide.with(parent.getContext()).load(bean.getListimage1())
                    .into(picViewHolder.ivImage2);
            Glide.with(parent.getContext()).load(bean.getListimage2())
                    .into(picViewHolder.ivImage3);
            if(bean.isRead()){
                picViewHolder.tvTime.setTextColor(Color.RED);

            }else {
                picViewHolder.tvTime.setTextColor(Color.BLACK);

            }

            return convertView;


            }

    }

    static class TextPicViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.iv_image)
        ImageView ivImage;

        TextPicViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class PicViewHolder {
        @BindView(R.id.iv_image1)
        ImageView ivImage1;
        @BindView(R.id.iv_image2)
        ImageView ivImage2;
        @BindView(R.id.iv_image3)
        ImageView ivImage3;
        @BindView(R.id.tv_time)
        TextView tvTime;

        PicViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

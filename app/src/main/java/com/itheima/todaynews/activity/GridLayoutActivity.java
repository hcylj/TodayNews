package com.itheima.todaynews.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.itheima.todaynews.R;
import com.itheima.todaynews.view.MyGridLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lou on 2018/7/18.
 */

public class GridLayoutActivity extends Activity {


    @BindView(R.id.gl_top)
    MyGridLayout glTop;
    @BindView(R.id.gl_bottom)
    MyGridLayout glBottom;
    private String[] topStr = new String[]{"标题1", "标题2", "标题3", "标题4",
            "标题5", "标题6", "标题7", "标题8", "标题9", "标题0"

    };
    private String[] bottomStr = new String[]{"NBA1", "CBA2", "意甲2", "英超3",
            "西甲4", "法甲5", "德甲3", "中超1", "澳网3", "法网2"

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        ButterKnife.bind(this);


       glTop.setEnable(true);
        glBottom.setEnable(false);

        glTop.addItems(topStr);
        glBottom.addItems(bottomStr);


        glTop.setOncustomerOnClick(new MyGridLayout.OnCustomerClick() {
          @Override
          public void customerOnClick(TextView textView) {
        glTop.removeView(textView );
        glBottom.addItemView(textView.getText().toString());

          }
      });
    glBottom.setOncustomerOnClick(new MyGridLayout.OnCustomerClick() {
        @Override
        public void customerOnClick(TextView textView) {
            glBottom.removeView(textView );
            glTop.addItemView(textView.getText().toString());

        }
    });


    }
}
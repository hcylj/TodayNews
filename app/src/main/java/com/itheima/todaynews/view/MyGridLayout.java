package com.itheima.todaynews.view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.itheima.todaynews.R;

import java.util.ArrayList;

/**
 * Created by Lou on 2018/7/18.
 */

public class MyGridLayout extends GridLayout {
    private  Context context;
    private  View currentView ;
    private ArrayList<Rect> rectList;
    private OnCustomerClick customerClick;
    private boolean isEnable;

    public MyGridLayout(Context context) {
        this(context,null);
    }

    public MyGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MyGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        this.context = context;
        setLayoutTransition(new LayoutTransition());
    }

    public void addItems(String[] str) {
        for (int i = 0; i < str.length; i++) {
            addItemView(str[i]);

        }
        if (isEnable) {
            setOnDragListener(onDragListener);
        } else {
            setOnDragListener(null);
        }
    }
    public void addItemView(String s) {
        final TextView textView = new TextView(context);
        textView.setText(s);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        textView.setBackgroundResource(R.drawable.selector_textview_bg);
        textView.setPadding(30,30,30,30);
        textView.setGravity(Gravity.CENTER);

        LayoutParams layoutParams = new LayoutParams();
        layoutParams.setMargins(30,30,30,30);
        addView(textView,layoutParams);


        if(isEnable) {
            textView.setOnLongClickListener(onLongClickListener);
        }else{
            textView.setOnClickListener(null);
        }
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
        if(customerClick!= null){
            customerClick.customerOnClick(textView);
        }
            }
        });

    }

    public void setEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }


    public interface OnCustomerClick{
        public void customerOnClick(TextView textView);

    }

    public void setOncustomerOnClick(OnCustomerClick customerClick){
        this.customerClick = customerClick;
    }
    private OnLongClickListener onLongClickListener  = new OnLongClickListener(){


        @Override
        public boolean onLongClick(View v) {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {
                v.startDragAndDrop(null, new DragShadowBuilder(v), null, 0);
            }else{
                v.startDrag(null,new DragShadowBuilder(v),null,0);

            }
            v.setEnabled(false);
            currentView = v;
            return true;
        }
    };
    public void getAllRect(){
         rectList = new ArrayList<>();
        int childCount = getChildCount();
       for(int i =0;i<childCount;i++){
           View childView = getChildAt(i);

           Rect rect = new Rect(childView.getLeft(), childView.getTop(),
                   childView.getRight(), childView.getBottom());

        rectList.add(rect);

       }
    }

    public int getIndex(DragEvent event){
        int locationX = (int) event.getX();
        int locationY = (int) event.getY();
        int index = -1;

    for(int i = 0;i<rectList.size();i++){
        Rect rect = rectList.get(i);
     if(rect.contains(locationX,locationY)){
         index = i;
         break;
     }
    }
    return index;
    }
    private OnDragListener onDragListener = new OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
           switch(event.getAction()){
               case DragEvent.ACTION_DRAG_STARTED:
                   Log.i("","开始拖拽");
                   getAllRect();
                   break;
               case DragEvent.ACTION_DRAG_ENTERED:
                   Log.i("","进入拖拽");
                   break;
               case DragEvent.ACTION_DRAG_LOCATION:
                   Log.i("","拖拽到某一位置");
                   int index = getIndex(event);
                   if(index != -1&&currentView !=null&&currentView !=getChildAt(index)){
                       MyGridLayout.this.removeView(currentView);
                       MyGridLayout.this.addView(currentView,index);
                   }
                   break;
               case DragEvent.ACTION_DRAG_ENDED:
                   Log.i("","拖拽结束");
                   if(currentView!=null){
                       currentView.setEnabled(true);
                   }
                   break;

           }
            return true;
        }
    };
}

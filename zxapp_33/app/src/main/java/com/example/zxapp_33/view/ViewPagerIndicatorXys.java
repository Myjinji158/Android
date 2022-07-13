package com.example.zxapp_33.view;

import com.example.zxapp_33.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ViewPagerIndicatorXys extends LinearLayout {
    private int jingCount;//小圆点的个数
    private int jingIndex;//当前小圆点的位置
    private Context jingContext;
    public ViewPagerIndicatorXys(Context context){
        this(context,null);
    }
    public ViewPagerIndicatorXys(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.jingContext = context;
        setGravity(Gravity.CENTER);//设置此布局居中
    }
    /**
     * 设置滑动到当前小圆点时其他圆点的位置
     */
    public void setCurrentPosition(int currentIndex){
        jingIndex = currentIndex;//当前小圆点
        removeAllViews();//移除界面上存在的view
        int pex = 5;
        for (int i = 0; i < jingCount; i++){
            //创建一个ImageView控件来放置小圆点
            ImageView imageView=new ImageView(jingContext);
            if (jingIndex == i){//滑动到的当前界面
                //设置小圆点的图片为蓝色图片
                imageView.setImageResource(R.drawable.indicaor_on);
            }else {
                //设置小圆点的图片为灰色图片
                imageView.setImageResource(R.drawable.indicator_off);
            }
            imageView.setPadding(pex,0,pex,0);//设置小圆点图片的上下左右的padding
            addView(imageView);//把此小圆点添加到自定义的ViewPagerIndicator控件上
        }
    }
    /**
     * 设置小圆点的数目
     */
    public void setCount(int count){
        this.jingCount = count;
    }
}

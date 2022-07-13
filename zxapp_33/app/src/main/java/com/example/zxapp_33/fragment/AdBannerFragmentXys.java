package com.example.zxapp_33.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.zxapp_33.R;

import androidx.fragment.app.Fragment;

public class AdBannerFragmentXys extends Fragment {
    private String jing_ab;//广告
    private ImageView jing_iv;//图片

    public static AdBannerFragmentXys newInstance(Bundle args){
        AdBannerFragmentXys af=new AdBannerFragmentXys();
        af.setArguments(args);
        return af;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        //获取广告图片名称
        jing_ab = arg.getString("ad");
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onResume(){
        super.onResume();
        if (jing_ab != null){
            if ("0".equals(jing_ab)){
                jing_iv.setImageResource(R.drawable.banner_1);
            }else if ("1".equals(jing_ab)){
                jing_iv.setImageResource(R.drawable.banner_2);
            }else if ("2".equals(jing_ab)){
                jing_iv.setImageResource(R.drawable.banner_3);
            }
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if (jing_iv != null){
            jing_iv.setImageDrawable(null);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState){
        //创建广告图片控件
        jing_iv = new ImageView(getActivity());
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT);
        jing_iv.setLayoutParams(lp);//设置图片宽高参数
        jing_iv.setScaleType(ImageView.ScaleType.FIT_XY);//把图片塞满整个控件
        return jing_iv;
    }
}
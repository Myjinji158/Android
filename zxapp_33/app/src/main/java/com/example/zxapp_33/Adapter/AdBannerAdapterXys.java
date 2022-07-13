package com.example.zxapp_33.Adapter;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import com.example.zxapp_33.fragment.AdBannerFragmentXys;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

public class AdBannerAdapterXys extends FragmentStatePagerAdapter implements View.OnTouchListener {
    private Handler jingHandler;
    private List<Integer> jingIMG;

    public AdBannerAdapterXys(FragmentManager fm) {
        super(fm);
        jingIMG = new ArrayList<Integer>();
    }
    public AdBannerAdapterXys(FragmentManager fm, Handler handler){
        super(fm);
        jingHandler = handler;
        jingIMG = new ArrayList<Integer>();
    }
    /**
     * 设置数据
     */
    public void setDatas(List<Integer> img){
        this.jingIMG = img;
        notifyDataSetChanged();
    }
    /**
     * 返回数据集的真实容量大小
     */
    public int getSize(){
        return jingIMG == null ? 0 : jingIMG.size();
    }

    @Override
    public Fragment getItem(int i){
        Bundle aegs = new Bundle();
        if (jingIMG.size() > 0)
            aegs.putString("ad",""+jingIMG.get(i % jingIMG.size()));
        return AdBannerFragmentXys.newInstance(aegs);
    }
    @Override
    public int getCount(){
        return Integer.MAX_VALUE;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event){
        jingHandler.removeMessages(1);
        return false;
    }

}

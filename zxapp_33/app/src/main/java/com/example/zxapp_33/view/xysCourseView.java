package com.example.zxapp_33.view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.zxapp_33.R;
import com.example.zxapp_33.Adapter.AdBannerAdapterXys;
import com.example.zxapp_33.bean.CourseBean;
import com.example.zxapp_33.Adapter.CourseAdapterXys;
import com.example.zxapp_33.utils.XMLUtils;
import android.widget.ListView;

public class xysCourseView {
    private FragmentActivity jingmContext;
    private ViewPager jing_adPager;//广告
    private View jing_adBannerLay;//广告条容器
    private AdBannerAdapterXys jing_ada;//适配器
    private ViewPagerIndicatorXys jing_vpi;//小圆点
    private MHandler jingmHandeler;//事件捕获
    private List<Integer> cadl;
    private LayoutInflater jingInflater;
    private View jingCurrentView;
    private List<CourseBean> jingLcb;
    private ListView lv_list;
    private CourseAdapterXys adapter;
    private List<List<CourseBean>> jingcb;

    public xysCourseView(FragmentActivity context){
        jingmContext = context;
        //为之后将Layout转化为view时用
        jingInflater = LayoutInflater.from(jingmContext);
    }

    private void createView(){
        jingmHandeler = new MHandler();
        initAdData();
        getCourseData();
        initView();
        new AdAutoSlidThread().start();
    }
    /**
     * 获取课程信息
     */
    private void getCourseData(){
        try {
            InputStream is = jingmContext.getResources().getAssets().
                    open("chaptertitle.xml");
            jingcb = XMLUtils.getCourseInfos(is);//getCourerInfos(is)方法在下面会有说明
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 获取当前在导航栏上方显示对应的View
     */
    public View getView(){
        if (jingCurrentView == null){
            createView();
        }
        return jingCurrentView;
    }
    /**
     * 显示当前在导航栏上方所对应的View界面
     */
    public void showView(){
        if (jingCurrentView == null){
            createView();
        }
        jingCurrentView.setVisibility(View.VISIBLE);
    }
    /**
     * 初始化广告中的数据
     */
    private void initAdData(){
        cadl = new ArrayList<Integer>();
        cadl.add(0);
        cadl.add(1);
        cadl.add(2);
    }
    /**
     * 初始化控件
     */
    private void initView(){
        jingCurrentView = jingInflater.inflate(R.layout.main_view_course_jing,null);
        lv_list =(ListView)jingCurrentView.findViewById(R.id.jing_lv_list);
        adapter = new CourseAdapterXys(jingmContext);
        adapter.setData(jingcb);
        lv_list.setAdapter(adapter);
        jing_adPager = (ViewPager)jingCurrentView.findViewById(R.id.jing_vp_advertBanner);
        jing_adPager.setLongClickable(false);
        jing_ada = new AdBannerAdapterXys(jingmContext.getSupportFragmentManager(),jingmHandeler);
        jing_adPager.setAdapter(jing_ada);//给ViewPager设置适配器
        jing_adPager.setOnTouchListener(jing_ada);
        jing_vpi = (ViewPagerIndicatorXys)jingCurrentView.findViewById(R.id.jing_vpi_advert_indicator);//获取广告条上的小圆点
        jing_vpi.setCount(jing_ada.getSize());//设置小圆点的个数
        jing_adBannerLay = jingCurrentView.findViewById(R.id.jing_rl_adBanner);
        jing_adPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (jing_ada.getSize() > 0){
                    //由于index数据在滑动时是累计的，因此用index % ada.getSize()来标记滑动到的当前位置
                    jing_vpi.setCurrentPosition(position % jing_ada.getSize());
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        restSize();{
            if (cadl != null) {
                if (cadl.size() > 0){
                    jing_vpi.setCount(cadl.size());
                    jing_vpi.setCurrentPosition(0);
                }
            }
            jing_ada.setDatas(cadl);
        }
    }
    /**
     * 计算广告控件大小
     */
    private void restSize(){
        int sw = getScreenWidth(jingmContext);
        int adLheight = sw / 2;//广告条宽度
        ViewGroup.LayoutParams adlp = jing_adBannerLay.getLayoutParams();
        adlp.width = sw;
        adlp.height = adLheight;
        jing_adBannerLay.setLayoutParams(adlp);
    }
    /**
     * 读取屏幕宽
     */
    public static int getScreenWidth(Activity context){
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = context.getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics.widthPixels;
    }
    /**
     * 事件捕获
     */
    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg){
            super.dispatchMessage(msg);
            switch (msg.what){
                case 1:
                    if (jing_ada.getCount() >0){
                        jing_adPager.setCurrentItem(jing_adPager.getCurrentItem() + 1);
                    }
                    break;
            }
        }
    }
    /**
     * 广告自动滑动
     */
    class AdAutoSlidThread extends Thread{
        @Override
        public void run(){
            super.run();
            while (true){
                try {
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if (jingmHandeler != null)
                    jingmHandeler.sendEmptyMessage(1);
            }
        }
    }
}

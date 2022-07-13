package com.example.zxapp_33.activity_33;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import java.util.ArrayList;
import java.util.List;
import android.graphics.Color;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.zxapp_33.Adapter.PlayHistoryAdapterJing;
import com.example.zxapp_33.R;
import com.example.zxapp_33.utils.DBUtils;
import com.example.zxapp_33.utils.SharePreferencesUtils;

public class jingPlayHistoryActivity extends AppCompatActivity {
    private TextView tv_main_title,tv_back,tv_none;
    private RelativeLayout rl_title_bar;
    private ListView lv_list;
    private PlayHistoryAdapterJing adapter;
    //private List<VideoBeam> vbl;
    private DBUtils db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        db=DBUtils.getInstance(this);
        initView();
    }
    /**
     * 初始化UI控件
     */
    private void initView(){
        tv_main_title=(TextView)findViewById(R.id.tv_title_33);
        tv_main_title.setText("播放记录");
        rl_title_bar=(RelativeLayout)findViewById(R.id.rl_bar_33);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back=(TextView)findViewById(R.id.tv_brack_33);
        lv_list=(ListView)findViewById(R.id.jing_lv_list);
        adapter= new PlayHistoryAdapterJing(this);
        lv_list.setAdapter(adapter);
        //后退键的点击事件
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jingPlayHistoryActivity.this.finish();
            }
        });
    }
}

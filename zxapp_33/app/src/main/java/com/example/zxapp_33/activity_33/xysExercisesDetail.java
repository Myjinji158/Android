package com.example.zxapp_33.activity_33;

import androidx.appcompat.app.AppCompatActivity;
import com.example.zxapp_33.R;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.zxapp_33.Adapter.ExercisesDetailAdapterXys;
import com.example.zxapp_33.bean.ExercisesDetailBean;
import com.example.zxapp_33.utils.XMLUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class xysExercisesDetail extends AppCompatActivity {
    private TextView tv_main_title;//标题栏标题控件变量
    private TextView tv_back_33;//标题栏返回控件变量
    private RelativeLayout rl_title_bar;//标题栏控件变量
    private ListView jing_lv_list;
    private String jing_title;
    private int jing_id;
    private List<ExercisesDetailBean> jing_ebl;
    private ExercisesDetailAdapterXys jing_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_exercises_detail);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        jing_id = getIntent().getIntExtra("id",0);
        //获取从习题界面传递过来的章节标题
        jing_title = getIntent().getStringExtra("title");
        jing_ebl = new ArrayList<ExercisesDetailBean>();
        initData();
        initView();
    }
    private void initData(){
        try{
            //从xml文件中获取习题数据
            InputStream is= getResources().getAssets().open("chapter" + jing_id + ".xml");
            jing_ebl = XMLUtils.getExercisesInfos(is);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void initView(){
        tv_main_title=(TextView)findViewById(R.id.tv_title_33);//关联习题详情页面标题栏标题控件tv_title
        tv_back_33=(TextView)findViewById(R.id.tv_brack_33);//关联习题详情页面标题栏返回控件tv_brack
        rl_title_bar=(RelativeLayout)findViewById(R.id.rl_bar_33);//关联习题详情页面标题栏控件rl_bar
        jing_lv_list=(ListView)findViewById(R.id.jing_lv_list);
        TextView tv= new TextView(this);
        tv.setTextColor(Color.parseColor("#000000"));
        tv.setTextSize(16.0f);
        tv.setText("一、选择题");
        tv.setPadding(10,15,0,0);
        jing_lv_list.addHeaderView(tv);
        tv_main_title.setText(jing_title);
        //标题栏返回控件添加点击事件
        tv_back_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xysExercisesDetail.this.finish();//关闭页面
            }
        });
        jing_adapter = new ExercisesDetailAdapterXys(xysExercisesDetail.this);
        jing_adapter.setData(jing_ebl);
        jing_lv_list.setAdapter(jing_adapter);
    }
}

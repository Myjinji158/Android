package com.example.zxapp_33.activity_33;

import androidx.appcompat.app.AppCompatActivity;
import com.example.zxapp_33.R;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.zxapp_33.Adapter.VideoListAdapterJing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class jingVideoList extends AppCompatActivity implements View.OnClickListener {
    private TextView jing_tv_intro,jing_tv_video,jing_tv_chapter_intro;
    private ListView jing_lv_video_list;
    private ScrollView jing_sv_chapter_intro;
    private VideoListAdapterJing jing_adapter;
    private String jing_intro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_video_list);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //从课程界面传递过来的章节简介
        jing_intro = getIntent().getStringExtra("intro");

        init();
    }

    /**
     * 读取数据流，参数in是数据流
     */
    private String read(InputStream in) {
        BufferedReader reader = null;
        StringBuilder sb = null;
        String line=null;
        try {
            sb = new StringBuilder();//实例化一个StringBuilder对象
            //用InputStreamReader把in这个字节流转换成字符流BufferedReader
            reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine())!=null){//从reader中读取一行的内容判断是否为空
                sb.append(line);
                sb.append("\n");
            }
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }finally {
            try {
                if (in != null)
                    in.close();
                if (reader != null)
                    reader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    /**
     * 初始化界面UI控件
     */
    private void init(){
        jing_tv_intro = (TextView) findViewById(R.id.jing_tv_intro);

        jing_lv_video_list =(ListView)findViewById(R.id.jing_lv_video_list);
        jing_tv_chapter_intro =(TextView)findViewById(R.id.jing_tv_chapter_intro);
        jing_sv_chapter_intro =(ScrollView) findViewById(R.id.jing_sv_chapter_intro);
        jing_adapter = new VideoListAdapterJing(this);
        jing_lv_video_list.setAdapter(jing_adapter);
        jing_tv_intro.setOnClickListener(this);

        jing_tv_chapter_intro.setText(jing_intro);
        jing_tv_intro.setBackgroundColor(Color.parseColor("#30B4FF"));

        jing_tv_intro.setTextColor(Color.parseColor("#FFFFFF"));

    }
    /**
     * 控件的点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jing_tv_intro://简介
                jing_sv_chapter_intro.setVisibility(View.VISIBLE);
                jing_tv_intro.setBackgroundColor(Color.parseColor("#30B4FF"));
                jing_tv_intro.setTextColor(Color.parseColor("#FFFFFF"));
                break;

            default:
                break;
        }
    }
}

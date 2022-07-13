package com.example.zxapp_33.activity_33;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import com.example.zxapp_33.R;

public class jingVideoPlayActivity extends AppCompatActivity {
    private VideoView jing_videoView;
    private MediaController jing_controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_video_play);
        //设置界面全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置次界面为横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        initView();
    }
    /**
     * 初始化界面控件
     */
    private void initView(){
        jing_videoView=(VideoView)findViewById(R.id.jing_videoView);
        jing_controller=new MediaController(this);
        jing_videoView.setMediaController(jing_controller);
        play();
    }
    /**
     * 播放视频
     */
    private void play(){
            String uri = "android.resource://"+getPackageName()+"/"+R.raw.video11;
            jing_videoView.setVideoPath(uri);
            jing_videoView.start();
    }
}

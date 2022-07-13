package com.example.zxapp_33.activity_33;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

import com.example.zxapp_33.xysMainActivity;
import com.example.zxapp_33.R;

public class hyActivity_33 extends AppCompatActivity {
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hy_33);
        initView();
    }
    private void initView() {




        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(hyActivity_33.this, xysMainActivity.class);
                startActivity(intent);
                hyActivity_33.this.finish();
            }
        };
        timer.schedule(task,5000);
    }
}

package com.example.zxapp_33.activity_33;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.zxapp_33.R;
import com.example.zxapp_33.activity_33.jingEditPswActivity;
import com.example.zxapp_33.activity_33.jingSecuritySettingActivity;

public class jingSettingActivity extends AppCompatActivity {
    private TextView tv_main_title;//标题栏标题控件变量
    private TextView tv_back;//标题栏返回控件变量
    private RelativeLayout rl_title_bar;//标题栏控件变量
    private RelativeLayout jing_rl_edit_psw,jing_rl_security_setting,jing_rl_logout;//修改密码选项变量、设置密保选项变量、退出登录选项变量
    public static jingSettingActivity instance=null;//设置activity变量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_setting);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        instance=this;//将变量与当前activity关联
        initView();
    }
    private void initView(){
        tv_main_title=(TextView)findViewById(R.id.tv_title_33);
        tv_main_title.setText("设置");
        tv_back=(TextView)findViewById(R.id.tv_brack_33);
        rl_title_bar=(RelativeLayout) findViewById(R.id.rl_bar_33);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        jing_rl_edit_psw=(RelativeLayout)findViewById(R.id.jing_rl_edit_psw);
        jing_rl_security_setting=(RelativeLayout)findViewById(R.id.jing_rl_security_setting);
        jing_rl_logout=(RelativeLayout)findViewById(R.id.jing_rl_logout);
        //标题栏返回控件添加点击事件
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jingSettingActivity.this.finish();//关闭设置界面
            }
        });
        //修改密码的点击事件
        jing_rl_edit_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo:跳转到修改密码页面
                Intent intent=new Intent(jingSettingActivity.this,jingEditPswActivity.class);
                startActivity(intent);//从设置页面打开修改密码页面
                Log.i("setting","setting");
            }
        });
        //设置密保的点击事件
        jing_rl_security_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo:跳转到设置密保页面
                Intent intent=new Intent(jingSettingActivity.this,jingSecuritySettingActivity.class);
                startActivity(intent);//打开设置密保页面
            }
        });
        //退出登录的点击事件
        jing_rl_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(jingSettingActivity.this,"退出登录成功",Toast.LENGTH_SHORT).show();
                clearLoginStatus();//清除登录状态和登录时的用户名
                //退出登录成功后把退出成功的状态传递到MainActiviyt中
                Intent data =new Intent();
                data.putExtra("isLogin",false);
                setResult(RESULT_OK,data);
                jingSettingActivity.this.finish();
            }
        });
    }
    //清除SharedPreferences中的登录状态和登录时的用户名
    private void clearLoginStatus(){
        SharedPreferences sp=getSharedPreferences("loginInfo",Context.MODE_PRIVATE);//定义sp对象并与loginInfo.xml文件关联
        SharedPreferences.Editor editor=sp.edit();//获取编辑器
        editor.putBoolean("isLogin",false);//清除用户登录状态
        editor.putString("loginUserName","");//清除用户登录名称
        editor.commit();//提交修改
    }
}

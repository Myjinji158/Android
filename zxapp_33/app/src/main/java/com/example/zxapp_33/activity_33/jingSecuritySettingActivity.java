package com.example.zxapp_33.activity_33;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.zxapp_33.R;
import com.example.zxapp_33.utils.SharePreferencesUtils;

public class jingSecuritySettingActivity extends AppCompatActivity {
    private TextView tv_main_title_33;
    private TextView tv_back_33;
    private RelativeLayout rl_title_bar_33;
    private EditText jing_et_validate_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_security_setting);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();//初始化界面
    }
    //初始化界面
    private void initView(){
        tv_main_title_33=(TextView)findViewById(R.id.tv_title_33);
        tv_main_title_33.setText("设置密保");
        tv_back_33=(TextView)findViewById(R.id.tv_brack_33);
        rl_title_bar_33=(RelativeLayout)findViewById(R.id.rl_bar_33);
        jing_et_validate_name=(EditText)findViewById(R.id.jing_et_validate_name);
        Button jing_btn_save=(Button)findViewById(R.id.jing_btn_save);
        //标题栏返回控件添加点击事件
        tv_back_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jingSecuritySettingActivity.this.finish();//关闭设置密保页面
            }
        });
        jing_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String validateName=jing_et_validate_name.getText().toString().trim();//获取用户输入的姓名
                if(TextUtils.isEmpty(validateName)){
                    Toast.makeText(jingSecuritySettingActivity.this,"请输入要验证的姓名",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(jingSecuritySettingActivity.this,"密保设置成功",Toast.LENGTH_SHORT).show();
                    //保存密保到SharedPreferences
                    saveSecurity(validateName);
                    jingSecuritySettingActivity.this.finish();//关闭设置密保
                }
            }
        });
    }
    //将用户密保到SharedPreferences中
    private void saveSecurity(String validateName){
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);//loginInfo表示文件名
        SharedPreferences.Editor editor=sp.edit();//获取编辑器
        editor.putString(SharePreferencesUtils.readLoginUserName(this)+"_security",validateName);//存入账号对应密保
        editor.commit();//提交修改
    }
}

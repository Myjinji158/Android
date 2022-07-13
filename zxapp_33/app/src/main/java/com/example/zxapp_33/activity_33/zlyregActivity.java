package com.example.zxapp_33.activity_33;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.zxapp_33.R;
import com.example.zxapp_33.utils.MD5Utils;

public class zlyregActivity extends AppCompatActivity {
    private TextView tv_main_title_33;
    private TextView tv_back_33;
    private Button btn_register_33;
    private EditText et_user_name_33, et_psw_33, et_psw_again_33;
    private String userName_33, psw_33, pswAgain_33;
    private RelativeLayout rl_title_bar_33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zly_reg);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
    }

    private void initView() {
        tv_main_title_33 = (TextView) findViewById(R.id.tv_title_33);
        tv_main_title_33.setText("注册");
        tv_back_33 = (TextView) findViewById(R.id.tv_brack_33);
        rl_title_bar_33 = (RelativeLayout) findViewById(R.id.rl_bar_33);
        rl_title_bar_33.setBackgroundColor(Color.TRANSPARENT);
        //从activity_register.xml页面布局中获得对应的UI控件
        btn_register_33 = (Button) findViewById(R.id.btn_register_33);
        et_user_name_33 = (EditText) findViewById(R.id.et_user_name_33);
        et_psw_33 = (EditText) findViewById(R.id.et_psw_33);
        et_psw_again_33 = (EditText) findViewById(R.id.et_psw_again_33);
        //标题栏返回功能实现
        tv_back_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zlyregActivity.this.finish();
            }
        });
        btn_register_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClick();
            }
        });
    }

    private void getEditString() {
        userName_33 = et_user_name_33.getText().toString().trim();
        psw_33 = et_psw_33.getText().toString().trim();
        pswAgain_33 = et_psw_again_33.getText().toString().trim();
    }

    private boolean isExistUserName(String userName_33) {
        boolean has_userName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName_33, "");
        if (!TextUtils.isEmpty(spPsw)) {
            has_userName = true;
        }
        return has_userName;
    }

    private void saveRegisterInfo(String userName_33,String psw_33){
        String md5Psw= MD5Utils.md5(psw_33);//把密码用MD5加密
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(userName_33,md5Psw);
        editor.commit();
    }

    private void btnClick(){
        //获取输入在相应控件中的字符串
        getEditString();
        if(TextUtils.isEmpty(userName_33)){
            Toast.makeText(zlyregActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(psw_33)){
            Toast.makeText(zlyregActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(pswAgain_33)){
            Toast.makeText(zlyregActivity.this,"请在次输入密码",Toast.LENGTH_SHORT).show();
            return;
        }else if(!psw_33.equals(pswAgain_33)){
            Toast.makeText(zlyregActivity.this,"输入两次密码不一样",Toast.LENGTH_SHORT).show();
            return;
        }else if(isExistUserName(userName_33)){
            Toast.makeText(zlyregActivity.this,"此账户名已经存在",Toast.LENGTH_SHORT).show();
            return;
        }else{
            Toast.makeText(zlyregActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            //把账号、密码和账号标识保存到sp里面
            saveRegisterInfo(userName_33,psw_33);
            //注册成功后把账号传递到LoginActivity.java中
            Intent data=new Intent();
            data.putExtra("userName_33",userName_33);
            setResult(RESULT_OK,data);
            zlyregActivity.this.finish();
        }
    }
}

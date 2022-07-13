package com.example.zxapp_33.activity_33;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.zxapp_33.R;
import com.example.zxapp_33.utils.MD5Utils;

public class zlyloginActivity extends AppCompatActivity {
    private TextView tv_main_title_33;
    private TextView tv_back_33,tv_register_33,tv_find_psw_33;
    private Button btn_login_33;
    private String userName_33,psw_33,spPsw_33;
    private EditText et_user_name_33,et_psw_33;
    //标题布局
    private RelativeLayout rl_title_bar_33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zly_login);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
    }
    private void initView(){
        rl_title_bar_33=(RelativeLayout)findViewById(R.id.rl_bar_33);
        tv_main_title_33=(TextView) findViewById(R.id.tv_title_33);
        tv_main_title_33.setText("登录");
        tv_back_33=(TextView) findViewById(R.id.tv_brack_33);
        tv_register_33=(TextView) findViewById(R.id.tv_reg_33);
        tv_find_psw_33=(TextView) findViewById(R.id.tv_find_psw_33);
        btn_login_33=(Button)findViewById(R.id.btn_login_33);
        et_user_name_33=(EditText)findViewById(R.id.et_user_name_33);
        et_psw_33=(EditText)findViewById(R.id.et_psw_33);
        tv_back_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zlyloginActivity.this.finish();
            }
        });
        //立即注册控件点击事件
        tv_register_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(zlyloginActivity.this, zlyregActivity.class);
                startActivityForResult(intent,1);
            }
        });
        //找回密码控件的点击事件
        tv_find_psw_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转找回密码页面
                Intent intent=new Intent(zlyloginActivity.this,jingFindPswActivity.class);
                startActivity(intent);
            }
        });
        //登录按钮的点击事件
        btn_login_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_click();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(data!=null){
            //从注册界面传递过来的用户名
            String userName_33 =data.getStringExtra("userName_33");
            if(!TextUtils.isEmpty(userName_33)){
                et_user_name_33.setText(userName_33);
                //设置光标的位置
                et_user_name_33.setSelection(userName_33.length());
                et_psw_33.setText("");//清除登录页面的密码内容
            }
        }
    }
    private void getEditString(){
        userName_33=et_user_name_33.getText().toString().trim();
        psw_33=et_psw_33.getText().toString().trim();
    }
    //从SharePreferences中根据用户名读取密码
    private String readPsw(String userName_33){
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        return sp.getString(userName_33,"");
    }
    //保存登录状态和登录用户名到SharePreferences中
    private void saveLoginStatus(boolean status,String userName_33){
        //loginInfo表示文件名
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();//获取编辑器
        editor.putBoolean("isLogin",status);//存入boolean类型的登录状态
        editor.putString("loginUserName",userName_33);//存入登录状态的用户名
        editor.commit();//提交修改
    }
    private void btn_click() {
        getEditString();
        String md5Psw= MD5Utils.md5(psw_33);//把密码用MD5加密
        spPsw_33=readPsw(userName_33);
        if (TextUtils.isEmpty(userName_33)) {
            Toast.makeText(zlyloginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(psw_33)) {
            Toast.makeText(zlyloginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        } else if ((spPsw_33!= null&&!TextUtils.isEmpty(spPsw_33)&&!md5Psw.equals(spPsw_33))) {
            Toast.makeText(zlyloginActivity.this, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }else if (md5Psw.equals(spPsw_33)) {
            Toast.makeText(zlyloginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            //保存登录状态
            saveLoginStatus(true, userName_33);
            Intent data=new Intent();
            data.putExtra("isLogin", true);
            setResult(RESULT_OK, data);
            zlyloginActivity.this.finish();
            return;
        } else{
            Toast.makeText(zlyloginActivity.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
        }
    }
}


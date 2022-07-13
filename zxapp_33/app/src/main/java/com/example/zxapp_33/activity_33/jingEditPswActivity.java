package com.example.zxapp_33.activity_33;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.zxapp_33.utils.SharePreferencesUtils;
import com.example.zxapp_33.R;
import com.example.zxapp_33.utils.MD5Utils;

public class jingEditPswActivity extends AppCompatActivity {
    private TextView tv_main_title_33;
    private TextView tv_back_33;
    private RelativeLayout rl_title_bar_33;
    private Button jing_btn_save;
    private EditText jing_et_old_psw,jing_et_new_psw,jing_et_new_psw_again;
    private String jing_oldPsw,jing_newPsw,jing_newPswAgain;
    private String jing_userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("editpsw","editpsw");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_edit_psw);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Log.i("123","123");
        initView();//初始化界面
        //获取当前登录用户的用户名
        jing_userName=SharePreferencesUtils.readLoginUserName(this);
    }
    private void initView(){
        tv_main_title_33=(TextView)findViewById(R.id.tv_title_33);
        tv_main_title_33.setText("修改密码");
        tv_back_33=(TextView)findViewById(R.id.tv_brack_33);
        rl_title_bar_33=(RelativeLayout)findViewById(R.id.rl_bar_33);
        jing_et_old_psw=(EditText)findViewById(R.id.jing_et_old_psw);
        jing_et_new_psw=(EditText)findViewById(R.id.jing_et_new_psw);
        jing_et_new_psw_again=(EditText)findViewById(R.id.jing_et_new_psw_again);
        jing_btn_save=(Button)findViewById(R.id.jing_btn_save);
        //标题栏返回控件添加点击事件
        tv_back_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jingEditPswActivity.this.finish();//关闭修改密码页面
            }
        });
        jing_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
    }
    /**
     * 获取用户输入字符串
     */
    private void getEditString(){
        jing_oldPsw=jing_et_old_psw.getText().toString().trim();
        jing_newPsw=jing_et_new_psw.getText().toString().trim();
        jing_newPswAgain=jing_et_new_psw_again.getText().toString().trim();
    }
    /**
     * 根据用户名从SharedPreferences中读取用户名原始密码
     */
    private String readPsw(){
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw=sp.getString(jing_userName,"");//读取用户名的密码
        return spPsw;
    }
    /**
     * 修改用户名保存在SharedPreferences中的密码
     */
    private void editPsw(String newPsw){
        String md5Psw= MD5Utils.md5(newPsw);//把密码用MD5加密
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();//获取编辑器
        editor.putString(jing_userName,md5Psw);//保存新密码
        editor.commit();//提交修改
    }
    private void click(){
        getEditString();
        if(TextUtils.isEmpty(jing_oldPsw)){
            Toast.makeText(jingEditPswActivity.this,"请输入旧密码",Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(jing_newPsw)){
            Toast.makeText(jingEditPswActivity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(jing_newPswAgain)){
            Toast.makeText(jingEditPswActivity.this,"请在次输入新密码",Toast.LENGTH_SHORT).show();
            return;
        }else if(!jing_newPsw.equals(jing_newPswAgain)){
            Toast.makeText(jingEditPswActivity.this,"两次输入的新密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }else if(!MD5Utils.md5(jing_oldPsw).equals(readPsw())){
            Toast.makeText(jingEditPswActivity.this,"输入的密码与原始密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }else if(MD5Utils.md5(jing_newPsw).equals(readPsw())){
            Toast.makeText(jingEditPswActivity.this,"输入的新密码与旧密码不能一样",Toast.LENGTH_SHORT).show();
            return;
        }else{
            Toast.makeText(jingEditPswActivity.this,"新密码设置成功",Toast.LENGTH_SHORT).show();
            editPsw(jing_newPsw);//修改用户保存在SharedPreferences中的密码
            Intent intent=new Intent(jingEditPswActivity.this, zlyloginActivity.class);//定义用户登录意图
            startActivity(intent);//打开登录页面
            jingSettingActivity.instance.finish();//关闭设置页面
            jingEditPswActivity.this.finish();//关闭修改密码页面
        }
    }
}

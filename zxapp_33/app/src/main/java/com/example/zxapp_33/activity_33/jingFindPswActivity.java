package com.example.zxapp_33.activity_33;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Analyzer;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import com.example.zxapp_33.R;
import com.example.zxapp_33.utils.SharePreferencesUtils;
import com.example.zxapp_33.utils.MD5Utils;

public class jingFindPswActivity extends AppCompatActivity {
    private EditText jing_et_user_name,jing_et_validate_name;
    private Button jing_btn_validate;
    private TextView tv_main_title_33;
    private TextView tv_back_33;
    private TextView jing_tv_user_name,jing_tv_reset_psw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_find_psw);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();//初始化界面
    }
    private void initView(){
        tv_main_title_33=(TextView)findViewById(R.id.tv_title_33);
        tv_main_title_33.setText("找回密码");
        tv_back_33=(TextView)findViewById(R.id.tv_brack_33);
        jing_tv_reset_psw=(TextView)findViewById(R.id.jing_tv_reset_psw);
        jing_tv_user_name=(TextView)findViewById(R.id.jing_tv_user_name);
        jing_et_user_name=(EditText)findViewById(R.id.jing_et_user_name);
        jing_et_validate_name=(EditText)findViewById(R.id.jing_et_validate_name);
        jing_btn_validate=(Button)findViewById(R.id.jing_btn_validate);
        tv_back_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jingFindPswActivity.this.finish();//关闭修改密码页面
            }
        });
        jing_btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String validateName=jing_et_validate_name.getText().toString().trim();//获取用户输入的姓名
                String userName_33=jing_et_user_name.getText().toString().trim();
                String sp_security=readSecurity(userName_33);
                if(TextUtils.isEmpty(userName_33)){
                    Toast.makeText(jingFindPswActivity.this,"请输入您的用户名",Toast.LENGTH_SHORT).show();
                    return;
                }else if(!isExistUserName(userName_33)){
                    Toast.makeText(jingFindPswActivity.this,"您输入的用户名不存在",Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(validateName)){
                    Toast.makeText(jingFindPswActivity.this,"请输入要验证的姓名",Toast.LENGTH_SHORT).show();
                    return;
                }else if(!validateName.equals(sp_security)){
                    Toast.makeText(jingFindPswActivity.this,"输入的密保不正确",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    //输入的密保正确，重新给用户设置一个密码
                    jing_tv_reset_psw.setVisibility(View.VISIBLE);
                    jing_tv_reset_psw.setText("初始密码：123456");
                    savePsw(userName_33);
                }
            }
        });
    }
    /**
     * 保存初始化密码
     */
    private void savePsw(String userName_33){
        String md5Psw= MD5Utils.md5("123456");//把密码用MD5加密
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();//获取编辑器
        editor.putString(userName_33,md5Psw);
        editor.commit();//提交修改
    }
    /**
     * 保存在SharedPreferences中
     */
    //private void saveSecurity(String validateName){
    //   SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
    //    SharedPreferences.Editor editor=sp.edit();//获取编辑器
    //    editor.putString(SharePreferencesUtils.readLoginUserName(this)+"_security",validateName);//存入用户对应密保
    //    editor.commit();//提交修改
    //}
    /**
     * 从SharedPreferences中读取密保
     */
    private String readSecurity(String userName_33){
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        String security=sp.getString(userName_33+"_security","");
        return security;
    }
    /**
     * 从SharedPreferences中根据用户输入的用户名来判断是非由此用户
     */
    private boolean isExistUserName(String userName_33){
        boolean hasUserName=false;
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw=sp.getString(userName_33,"");
        if(!TextUtils.isEmpty(spPsw)){
            hasUserName=true;
        }
        return hasUserName;
    }
}

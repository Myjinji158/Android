package com.example.zxapp_33.activity_33;

import androidx.appcompat.app.AppCompatActivity;
import com.example.zxapp_33.R;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class zlyChangUserInfoActivity extends AppCompatActivity {
    private TextView tv_main_title;//标题栏标题控件变量
    private TextView tv_back_33;//标题栏返回控件变量
    private RelativeLayout rl_title_bar;//标题栏控件变量
    private TextView jing_tv_save;//标题栏保存控件变量
    private String jing_title, jing_content;
    private int jing_flag;//flag为1时表示修改昵称，为2时表示修改签名
    private EditText jing_et_content;
    private ImageView jing_iv_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_chang_user_info);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        getData();
        contentlistener();
    }
    private void initView(){
        tv_main_title=(TextView) findViewById(R.id.tv_title_33);//关联设置页面标题栏标题控件tv_title
        tv_back_33=(TextView) findViewById(R.id.tv_brack_33);//关联设置页面标签栏返回控件tv_brack
        rl_title_bar=(RelativeLayout) findViewById(R.id.rl_bar_33);//关联设置页面标题栏控件rl_bar
        jing_tv_save=(TextView) findViewById(R.id.jing_tv_save);//关联设置页面标题栏保存控件jing_tv_save
        jing_et_content=(EditText) findViewById(R.id.jing_et_content);
        jing_iv_delete=(ImageView) findViewById(R.id.jing_iv_delete);
        //标题栏返回控件添加点击事件
        tv_back_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zlyChangUserInfoActivity.this.finish();//关闭页面
            }
        });
        //删除文本框中所有内容
        jing_iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jing_et_content.setText("");
            }
        });
        //保存用户修改信息
        jing_tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        //显示保存控件
        jing_tv_save.setVisibility(View.VISIBLE);
        //设置标题栏背景颜色
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
    }
    private  void save(){
        Intent data = new Intent();
        String etContent = jing_et_content.getText().toString().trim();
        boolean is_save=false;
        switch (jing_flag){
            case 1:
                if (!TextUtils.isEmpty(etContent)){
                    data.putExtra("nickName",etContent);
                    is_save=true;
                }else {
                    Toast.makeText(zlyChangUserInfoActivity.this,"昵称不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (!TextUtils.isEmpty(etContent)){
                    data.putExtra("signature",etContent);
                    is_save=true;
                }else {
                    Toast.makeText(zlyChangUserInfoActivity.this,"签名不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if (!TextUtils.isEmpty(etContent)){
                    data.putExtra("name",etContent);
                    is_save=true;
                }else {
                    Toast.makeText(zlyChangUserInfoActivity.this,"姓名不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
            case 4:
                if (!TextUtils.isEmpty(etContent)){
                    data.putExtra("sno",etContent);
                    is_save=true;
                }else {
                    Toast.makeText(zlyChangUserInfoActivity.this,"学号不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        if (is_save){
            setResult(RESULT_OK,data);
            Toast.makeText(zlyChangUserInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
            zlyChangUserInfoActivity.this.finish();
        }
    }

    /**
     * 获取个人资料传递过来的数据
     */
    private void getData(){
        jing_title=getIntent().getStringExtra("title");
        jing_content=getIntent().getStringExtra("content");
        jing_flag=getIntent().getIntExtra("flag",0);
        if (!TextUtils.isEmpty(jing_content)){
            jing_et_content.setText(jing_content);
            jing_et_content.setSelection(jing_content.length());
        }
    }
    /**
     * 字符截取功能
     */
    private void cutOut(Editable editable,int len,int maxLen)
    {
        if (len>maxLen){
            int selEndIndex = Selection.getSelectionEnd(editable);
            String str = editable.toString();
            //截取新字符串
            String newStr = str.substring(0,maxLen);
            jing_et_content.setText(newStr);
            editable = jing_et_content.getText();
            //新字符串的长度
            int newLen = editable.length();
            //旧光标位置超过字符串长度
            if(selEndIndex > newLen){
                selEndIndex = editable.length();
            }
            //设置新光标所在的位置
            Selection.setSelection(editable,selEndIndex);
        }
    }
    /**
     * 文本框内容修改监听功能
     */
    private void contentlistener(){
        jing_et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s,int start,int before,int count) {
                Editable editable = jing_et_content.getText();
                int len = editable.length();//输入的文本的长度
                if (len>0){
                    jing_iv_delete.setVisibility(View.VISIBLE);
                }else {
                    jing_iv_delete.setVisibility(View.GONE);
                }
                switch (jing_flag) {
                    case 1://昵称
                        cutOut(editable, len, 8);
                        break;
                    case 2://签名
                        cutOut(editable, len, 16);
                        break;
                    case 3://姓名
                        cutOut(editable, len, 8);
                        break;
                    case 4://学号
                        cutOut(editable, len, 16);
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s,int start,int count,int after) {
            }
            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });
    }
}

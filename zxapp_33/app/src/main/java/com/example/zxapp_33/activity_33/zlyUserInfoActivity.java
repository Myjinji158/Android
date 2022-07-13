package com.example.zxapp_33.activity_33;

import androidx.appcompat.app.AppCompatActivity;
import com.example.zxapp_33.R;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.zxapp_33.bean.UserBean;
import com.example.zxapp_33.utils.SharePreferencesUtils;
import com.example.zxapp_33.utils.DBUtils;

public class zlyUserInfoActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_main_title;//标题栏标题控件变量
    private TextView tv_back_33;//标题栏返回控件变量
    private RelativeLayout rl_title_bar;//标题栏控件变量
    private TextView zly_tv_nickName,zly_tv_signature,zly_tv_userName,zly_tv_sex,zly_tv_sno,zly_tv_name;
    private RelativeLayout zly_rl_nickName,zly_rl_sex,zly_rl_signature,zly_rl_name,zly_rl_sno;
    private String spUserName;
    private String zly_new_info;//最新数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zly_user_info);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //从SharedPreferences中获取登录时的用户名
        spUserName = SharePreferencesUtils.readLoginUserName(this);
        initView();
        initData();
        setListener();
    }
    private void initView(){
        tv_main_title=(TextView)findViewById(R.id.tv_title_33);//关联设置页面标题栏标题控件tv_title
        tv_main_title.setText("个人资料");//设置标题栏标题为个人资料
        tv_back_33=(TextView)findViewById(R.id.tv_brack_33);//关联设置页面标签栏返回控件tv_brack
        rl_title_bar=(RelativeLayout)findViewById(R.id.rl_bar_33);//关联设置页面标题栏控件rl_bar
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        zly_rl_nickName=(RelativeLayout)findViewById(R.id.zly_rl_nickName);
        zly_rl_sex=(RelativeLayout)findViewById(R.id.zly_rl_sex);
        zly_rl_signature=(RelativeLayout)findViewById(R.id.zly_rl_signature);
        zly_rl_name=(RelativeLayout)findViewById(R.id.zly_rl_name);
        zly_rl_sno=(RelativeLayout)findViewById(R.id.zly_rl_sno);
        zly_tv_nickName=(TextView)findViewById(R.id.zly_tv_nickName);
        zly_tv_userName=(TextView)findViewById(R.id.zly_tv_userName);
        zly_tv_sex=(TextView)findViewById(R.id.zly_tv_sex);
        zly_tv_signature=(TextView)findViewById(R.id.zly_tv_signature);
        zly_tv_name=(TextView)findViewById(R.id.zly_tv_name);
        zly_tv_sno=(TextView)findViewById(R.id.zly_tv_sno);
    }
    /**
     *将用户信息显示到界面上
     */
    private void setValue(UserBean bean){
        zly_tv_nickName.setText(bean.nickName);
        zly_tv_userName.setText(bean.userName);
        zly_tv_sex.setText(bean.sex);
        zly_tv_signature.setText(bean.signature);
        zly_tv_name.setText(bean.name);
        zly_tv_sno.setText(bean.sno);
    }
    /**
     *初始化用户资料
     */
    private void initData(){
        UserBean bean = null;
        bean = DBUtils.getInstance(this).getUserInfo(spUserName);
        //首先判断一下数据库是否有数据
        if(bean == null){
            bean = new UserBean();
            bean.userName=spUserName;
            bean.nickName="驾考小能手";
            bean.sex="女";
            bean.signature="驾考模拟";
            bean.name="第二组";
            bean.sno="33+41+12+46";
            //保存用户信息到数据库
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        setValue(bean);
    }
    /**
     *设置控件的点击监听事件
     */
    private void setListener(){
        tv_back_33.setOnClickListener(this);
        zly_rl_nickName.setOnClickListener(this);
        zly_rl_sex.setOnClickListener(this);
        zly_rl_signature.setOnClickListener(this);
        zly_rl_name.setOnClickListener(this);
        zly_rl_sno.setOnClickListener(this);
    }
    /**
     *更新数据库中的性别信息并显示到页面
     */
    private void setSex(String sex){
        zly_tv_sex.setText(sex);
        //更新数据库中的性别字段
        DBUtils.getInstance(zlyUserInfoActivity.this).updateUserInfo("sex",sex,spUserName);
    }
    /**
     *设置性别的弹出框
     */
    private void sexDialog(String sex){
        int sexFlag=0;
        if("男".equals(sex)){
            sexFlag=0;
        }else if("女".equals(sex)){
            sexFlag=1;
        }
        final String items[]={"男","女"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);//先得到构造器
        builder.setTitle("性别");//设置标题
        builder.setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {//第二个参数是默认选中的哪个项
                dialog.dismiss();
                Toast.makeText(zlyUserInfoActivity.this,items[which],Toast.LENGTH_SHORT).show();
                setSex(items[which]);
            }
        });
        builder.create().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_brack_33://返回键的点击事件
                this.finish();
                break;
            case R.id.zly_rl_nickName://昵称的点击事件
                //todo:跳转到个人资料修改页面
                String nickname = zly_tv_nickName.getText().toString();//获取昵称控件上的数据
                Bundle bdNickname = new Bundle();
                bdNickname.putString("content",nickname);//传递界面上的昵称数据
                bdNickname.putString("title","昵称");
                bdNickname.putInt("flag",1);//flag传递1时表示是修改昵称
                enterActivityForResult(zlyChangUserInfoActivity.class,1,bdNickname);//跳转到个人资料修改界面
                break;
            case R.id.zly_rl_sex://性别的点击事件
                String sex = zly_tv_sex.getText().toString();//获取性别控件上的数据
                sexDialog(sex);
                break;
            case R.id.zly_rl_signature://签名的点击事件
                //todo:跳转到个人资料修改页面
                String signature = zly_tv_signature.getText().toString();//获取签名控件上的数据
                Bundle bdSignature = new Bundle();
                bdSignature.putString("content",signature);//传递界面上的签名数据
                bdSignature.putString("title","签名");
                bdSignature.putInt("flag",2);//flag传递1时表示是修改签名
                enterActivityForResult(zlyChangUserInfoActivity.class,2,bdSignature);//跳转到个人资料修改界面
                break;
            case R.id.zly_rl_name://姓名的点击事件
                //todo:跳转到个人资料修改页面
                String name = zly_tv_name.getText().toString();//获取姓名控件上的数据
                Bundle bdName = new Bundle();
                bdName.putString("content",name);//传递界面上的姓名数据
                bdName.putString("title","姓名");
                bdName.putInt("flag",3);//flag传递1时表示是修改姓名
                enterActivityForResult(zlyChangUserInfoActivity.class,3,bdName);//跳转到个人资料修改界面
                break;
            case R.id.zly_rl_sno://学号的点击事件
                //todo:跳转到个人资料修改页面
                String sno = zly_tv_sno.getText().toString();//获取学号控件上的数据
                Bundle bdSno = new Bundle();
                bdSno.putString("content",sno);//传递界面上的学号数据
                bdSno.putString("title","签名");
                bdSno.putInt("flag",4);//flag传递1时表示是修改学号
                enterActivityForResult(zlyChangUserInfoActivity.class,4,bdSno);//跳转到个人资料修改界面
                break;
            default:
                break;
        }
    }
    /**
     * 获取回传数据时需使用的跳转方法
     * 第一个参数to表示需要跳转到的界面，第二个参数requestCode表示一个请求码，第三个参数b表示跳转时传递的数据
     */
    public void enterActivityForResult(Class<?> to,int requestCode,Bundle b){
        Intent i = new Intent(this,to);
        i.putExtras(b);
        startActivityForResult(i,requestCode);
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case 1://个人资料修改界面回传过来的昵称数据
                if(data != null){
                    zly_new_info = data.getStringExtra("nickName");
                    if (TextUtils.isEmpty(zly_new_info) || zly_new_info == null){
                        return;
                    }
                    zly_tv_nickName.setText(zly_new_info);
                    //更新数据库中的昵称字段
                    DBUtils.getInstance(zlyUserInfoActivity.this).updateUserInfo("nickName",zly_new_info,spUserName);
                }
                break;
            case 2://个人资料修改界面回传过来的签名数据
                if(data != null){
                    zly_new_info = data.getStringExtra("signature");
                    if (TextUtils.isEmpty(zly_new_info) || zly_new_info == null){
                        return;
                    }
                    zly_tv_signature.setText(zly_new_info);
                    //更新数据库中的签名字段
                    DBUtils.getInstance(zlyUserInfoActivity.this).updateUserInfo("signature",zly_new_info,spUserName);
                }
                break;
            case 3://个人资料修改界面回传过来的姓名数据
                if(data != null){
                    zly_new_info = data.getStringExtra("name");
                    if (TextUtils.isEmpty(zly_new_info) || zly_new_info == null){
                        return;
                    }
                    zly_tv_name.setText(zly_new_info);
                    //更新数据库中的姓名字段
                    DBUtils.getInstance(zlyUserInfoActivity.this).updateUserInfo("name",zly_new_info,spUserName);
                }
                break;
            case 4://个人资料修改界面回传过来的学号数据
                if(data != null){
                    zly_new_info = data.getStringExtra("sno");
                    if (TextUtils.isEmpty(zly_new_info) || zly_new_info == null){
                        return;
                    }
                    zly_tv_sno.setText(zly_new_info);
                    //更新数据库中的学号字段
                    DBUtils.getInstance(zlyUserInfoActivity.this).updateUserInfo("sno",zly_new_info,spUserName);
                }
                break;
        }
    }
}

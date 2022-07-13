package com.example.zxapp_33.view;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.zxapp_33.R;
import com.example.zxapp_33.activity_33.zlyloginActivity;
import com.example.zxapp_33.utils.SharePreferencesUtils;
import com.example.zxapp_33.activity_33.jingSettingActivity;
import com.example.zxapp_33.activity_33.zlyUserInfoActivity;
import com.example.zxapp_33.activity_33.jingcommunication;

public class jingMyInfoView {
    private ImageView jing_iv_head_icon;
    private LinearLayout jing_head;
    private RelativeLayout jing_rl_course_history,jing_rl_setting;
    private TextView jing_tv_user_name;
    private Activity jing_mContext;
    private LayoutInflater jing_mInflater;
    private View jing_mCurrentView;
    public jingMyInfoView(Activity context){
        jing_mContext = context;
        //为之后将Layout转化view时用
        jing_mInflater = LayoutInflater.from(jing_mContext);
    }
    //设置我的界面用户名
    public void setLoginParams(boolean isLogin){
        if (isLogin){//判断用户是否登录，没有登录显示“点击登录”，登录则直接显示用户名
            jing_tv_user_name.setText(SharePreferencesUtils.readLoginUserName(jing_mContext));//显示用户名
        }else {
            jing_tv_user_name.setText("点击登录");
        }
    }
    //初始化界面
    private void initView(){
        //设置布局文件
        jing_mCurrentView=jing_mInflater.inflate(R.layout.main_view_myinfo_jing,null);
        jing_head=(LinearLayout)jing_mCurrentView.findViewById(R.id.jing_head);
        jing_iv_head_icon=(ImageView)jing_mCurrentView.findViewById(R.id.jing_head_icon);
        jing_rl_course_history=(RelativeLayout)jing_mCurrentView.findViewById(R.id.jing_rl_course_history);
        jing_rl_setting=(RelativeLayout)jing_mCurrentView.findViewById(R.id.jing_rl_setting);
        jing_tv_user_name=(TextView)jing_mCurrentView.findViewById(R.id.jing_tv_user_name);
        jing_mCurrentView.setVisibility(View.VISIBLE);
        setLoginParams(SharePreferencesUtils.readLoginStatus(jing_mContext));//设置登录时界面控件的状态
        jing_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否已登录
                if (SharePreferencesUtils.readLoginStatus(jing_mContext)) {
                    //todo:已登录跳转到个人资料界面
                    Intent intent = new Intent(jing_mContext, zlyUserInfoActivity.class);
                    jing_mContext.startActivity(intent);
                } else {
                    //todo:为登录跳转到登录界面
                    Intent intent = new Intent(jing_mContext, zlyloginActivity.class);
                    jing_mContext.startActivityForResult(intent, 1);
                }
            }
        });
        jing_rl_course_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharePreferencesUtils.readLoginStatus(jing_mContext)) {
                    //todo:跳转到播放记录界面
                    Intent intent = new Intent(jing_mContext,jingcommunication.class);
                    jing_mContext.startActivity(intent);
                } else {
                    Toast.makeText(jing_mContext, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        jing_rl_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharePreferencesUtils.readLoginStatus(jing_mContext)) {
                    //todo:跳转到设置界面
                    Intent intent = new Intent(jing_mContext,jingSettingActivity.class);
                    jing_mContext.startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(jing_mContext, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //创建view界面
    private void createView(){
        initView();
    }
    //获取当前View
    public View getView(){
        //判断当前view是否存在，不存在则创建view
        if(jing_mCurrentView == null){
            createView();//创建view
        }
        return jing_mCurrentView;//返回当前view对象
    }
    //显示当前view界面
    public void showView(){
        //判断当前view是否存在，不存在则创建view
        if(jing_mCurrentView == null){
            createView();//创建view
        }
        jing_mCurrentView.setVisibility(View.VISIBLE);//显示view
    }
}

package com.example.zxapp_33;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.zxapp_33.view.jingMyInfoView;
import androidx.annotation.Nullable;
import com.example.zxapp_33.view.jingExercisesView;
import com.example.zxapp_33.view.xysCourseView;

public class xysMainActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     * 底部按钮栏
     */
    private FrameLayout mBodyLayout_33;//中间内容栏
    public LinearLayout mBottomLayout_33;//底部按钮栏
    /**
     * 底部按钮
     */
    private View mCourseBtn_33;//课程
    private View mExercisesBtn_33;//习题
    private View mMyInfoBtn_33;//我
    private TextView tv_course_33;
    private TextView tv_exercises_33;
    private TextView tv_myInfo_33;
    private ImageView iv_course_33;//课程图
    private ImageView iv_exercises_33;
    private ImageView iv_myInfo_33;
    private TextView tv_back_33;
    private TextView tv_main_title_33;//顶部标题栏
    private RelativeLayout rl_title_bar_33;
    private jingMyInfoView jing_MyInfoView;
    private jingExercisesView jing_ExercisesView;
    private xysCourseView jing_CourseView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xys_main);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initTitleBar();//初始化标题栏
        initBodyLayout();//初始化主体布局
        initBottomBar();//初始化底部导航栏
        setInitStatus();//设置界面view的初始化状态
        setListener();//绑定底部导航栏按钮点击事件
    }
    //绑定底部导航栏按钮点击事件
    private void setListener(){
        for (int i = 0;i <mBottomLayout_33.getChildCount(); i++){
            mBottomLayout_33.getChildAt(i).setOnClickListener(this);
        }
    }
    /**
     * 设置界面view的初始化状态
     */
    private void setInitStatus(){
        clearBottomImageState();//清除底部栏
        setSelectedStatus(0);//选中课程
        createView(0);//创建view
    }
    /**
     * 显示对应的页面
     */
    private void selectDisplayView(int index){
        removeAllView();
        createView(index);
        setSelectedStatus(index);
    }
    /**
     * 初始化底部导航栏
     */
    private void initBottomBar(){
        mBottomLayout_33=(LinearLayout)findViewById(R.id.xys_main_bottom_bar_33);
        mCourseBtn_33=findViewById(R.id.bottom_bar_course_btn_33);
        mExercisesBtn_33=findViewById(R.id.xys_bottom_bar_exercises_btn_33);
        mMyInfoBtn_33=findViewById(R.id.xys_bottom_bar_my_btn_33);
        tv_course_33=(TextView)findViewById(R.id.xys_bottom_bar_text_course_33);
        tv_exercises_33=(TextView)findViewById(R.id.xys_bottom_bar_text_exercises_33);
        tv_myInfo_33=(TextView)findViewById(R.id.xys_bottom_bar_text_my_33);
        iv_course_33=(ImageView)findViewById(R.id.xys_bottom_bar_image_course_33);
        iv_exercises_33=(ImageView)findViewById(R.id.xys_bottom_bar_image_exercises_33);
        iv_myInfo_33=(ImageView)findViewById(R.id.xys_bottom_bar_image_my_33);
    }
    /**
     * 初始化主体布局
     */
    private void initBodyLayout(){
        mBodyLayout_33=(FrameLayout)findViewById(R.id.xys_main_body_33);
    }
    /**
     * 初始化标题栏
     */
    private void initTitleBar(){
        tv_back_33=(TextView)findViewById(R.id.tv_brack_33);
        tv_main_title_33=(TextView)findViewById(R.id.tv_title_33);
        tv_main_title_33.setText("博学谷课程");
        rl_title_bar_33=(RelativeLayout)findViewById(R.id.rl_bar_33);
        rl_title_bar_33.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back_33.setVisibility(View.GONE);
    }
    /**
     * 设置底部按钮选中状态
     */
    public void setSelectedStatus(int index){
        switch (index){
            case 0:
                mCourseBtn_33.setSelected(true);//选中课程
                iv_course_33.setImageResource(R.drawable.main_course_icon_selected);//课程图片
                tv_course_33.setTextColor(Color.parseColor("#0097F7"));//课程文字颜色
                rl_title_bar_33.setVisibility(View.VISIBLE);//标题
                tv_main_title_33.setText("驾考一点通");
                break;
            case 1:
                mExercisesBtn_33.setSelected(true);
                iv_exercises_33.setImageResource(R.drawable.main_exercises_icon_selected);
                tv_exercises_33.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar_33.setVisibility(View.VISIBLE);
                tv_main_title_33.setText("驾考学习视频");
                break;
            case 2:
                mMyInfoBtn_33.setSelected(true);
                tv_main_title_33.setText("我的");
                iv_myInfo_33.setImageResource(R.drawable.main_my_icon_selected);
                tv_myInfo_33.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar_33.setVisibility(View.VISIBLE);
        }
    }
    /**
     * 清除底部按钮的选中状态
     */
    private void clearBottomImageState(){
        tv_course_33.setTextColor(Color.parseColor("#666666"));
        tv_exercises_33.setTextColor(Color.parseColor("#666666"));
        tv_myInfo_33.setTextColor(Color.parseColor("#666666"));
        iv_course_33.setImageResource(R.drawable.main_course_icon);
        iv_exercises_33.setImageResource(R.drawable.main_exercises_icon);
        iv_myInfo_33.setImageResource(R.drawable.main_my_icon);
        for(int i = 0;i < mBottomLayout_33.getChildCount();i++){
            mBottomLayout_33.getChildAt(i).setSelected(false);
        }
    }
    /**
     *创建显示视图
     */
    private void createView(int viewIndex){
        switch (viewIndex){
            case 0:
                //todo:打开课程界面
                if(jing_CourseView==null)//判断课程列表页面是否存在，不存在创建，存在获取
                {
                    jing_CourseView=new xysCourseView(this);//创建
                    mBodyLayout_33.addView(jing_CourseView.getView());//将我的页面添加到mBodyLayout布局中
                }
                else
                    jing_CourseView.getView();//获取课程列表页面
                jing_CourseView.showView();//显示课程列表页面
                break;
            case 1:
                //todo:打开习题界面
                if(jing_ExercisesView==null)//判断习题列表页面是否存在，不存在创建，存在获取
                {
                    jing_ExercisesView=new jingExercisesView(this);//创建
                    mBodyLayout_33.addView(jing_ExercisesView.getView());//将习题列表页面添加到mBodyLayout布局中
                }
                else
                    jing_ExercisesView.getView();//获取习题列表页面
                jing_ExercisesView.showView();//显示习题列表页面
                break;
            case 2:
                //todo:我的界面
                if(jing_MyInfoView==null)//判断我的页面是否存在，不存在创建，存在获取
                {
                    jing_MyInfoView=new jingMyInfoView(this);//创建
                    mBodyLayout_33.addView(jing_MyInfoView.getView());//将我的页面添加到mBodyLayout布局中
                }
                else
                    jing_MyInfoView.getView();//获取我的页面
                jing_MyInfoView.showView();//显示我的页面
                break;
        }
    }
    /**
     * 移除所有视图
     */
    private void removeAllView(){
        for (int i = 0;i < mBodyLayout_33.getChildCount();i++){
            mBodyLayout_33.getChildAt(i).setVisibility(View.GONE);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //课程的点击事件
            case R.id.bottom_bar_course_btn_33:
                clearBottomImageState();
                selectDisplayView(0);
                break;
            //习题的点击事件
            case R.id.xys_bottom_bar_exercises_btn_33:
                clearBottomImageState();
                selectDisplayView(1);
                break;
            //我的点击事件
            case R.id.xys_bottom_bar_my_btn_33:
                clearBottomImageState();
                selectDisplayView(2);
                break;
             default:
                 break;
        }
    }
    /**
     * 清除SharedPreferences中的登录状态
     */
    private void clearLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();//获取编辑器
        editor.putBoolean("isLogin",false);//清除登录状态
        editor.putString("loginUserName","");//清除登录时的用户名
        editor.commit();//提交修改
    }
    /**
     * 获取SharedPreferences中的登录状态
     */
    private boolean readLoginStatus(){
        SharedPreferences sp = getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin,",false);
        return isLogin;
    }

    protected long exitTime;//记录第一次点击时的时间
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis() - exitTime) > 2000){
                Toast.makeText(xysMainActivity.this,"再按一次退出驾校一考通",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                xysMainActivity.this.finish();
                if (readLoginStatus()){
                    //如果退出次应用时是登录状态，则需要清除登录状态，同时清除登录时的用户名
                    clearBottomImageState();
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    /**
     * 登录成功后回调方法
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        //判断回传数据是否空
        if(data!=null)
        {
            boolean isLogin=data.getBooleanExtra("isLogin",false);//判断回传数据中的登录状态是否已登录
            if (isLogin){
                clearBottomImageState();//清除底部按钮选中状态
                selectDisplayView(0);//显示课程页面
            }
            if (jing_MyInfoView!=null)//判断我的页面是否存在，如果存在则更新用户名显示内容
            {
                jing_MyInfoView.setLoginParams(isLogin);
            }
        }
    }
}

package com.example.zxapp_33.utils;
import android.content.Context;
import android.content.SharedPreferences;
public class SharePreferencesUtils {
    //从SharedPreferences中读取登录用户名
    public static String readLoginUserName(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String userName_33=sp.getString("loginUserName","");
        return userName_33;
    }
    //从SharedPreferences中读取登录状态
    public static boolean readLoginStatus(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        boolean isLogin=sp.getBoolean("isLogin",false);
        return isLogin;
    }

}

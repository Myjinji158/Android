package com.example.zxapp_33.utils;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.zxapp_33.bean.UserBean;
import com.example.zxapp_33.sqlite.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;
public class DBUtils {
    private static DBUtils instance =null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;
    public DBUtils(Context context){
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }
    public static DBUtils getInstance(Context context){
        if (instance == null){
            instance = new DBUtils(context);
        }
        return instance;
    }
    /**
     * 保存个人资料信息
     */
    public void saveUserInfo(UserBean bean){
        ContentValues cv=new ContentValues();
        cv.put("userName",bean.userName);
        cv.put("nickName",bean.nickName);
        cv.put("sex",bean.sex);
        cv.put("signature",bean.signature);
        cv.put("name",bean.name);
        cv.put("sno",bean.sno);
        db.insert(SQLiteHelper.U_USERINFO,null,cv);
    }
    /**
     * 判断视频记录是否存在
     */

    /**
     * 删除已经存在的视频记录
     */
    public boolean delVideoPlay(int chapterId, int videoId,String userName){
        boolean delSuccess=false;
        int row = db.delete("videoplaylist",
                "chapterId=? AND videoId=? AND userName=?",new String[]{chapterId+"",
                        videoId + "",userName});
        if (row >0){
            delSuccess=true;
        }
        return delSuccess;
    }
    /**
     * 保存视频播放记录
     */

    /**
     * 获取视频记录信息
     */

    /**
     * 获取个人资料信息
     */
    public UserBean getUserInfo(String userName){
        String sql = "SELECT * FROM "+SQLiteHelper.U_USERINFO + " WHERE userName=?";
        Cursor cursor = db.rawQuery(sql,new String[]{userName});
        UserBean bean = null;
        while (cursor.moveToNext()){
            bean = new UserBean();
            bean.userName=cursor.getString(cursor.getColumnIndex("userName"));
            bean.nickName=cursor.getString(cursor.getColumnIndex("nickName"));
            bean.sex=cursor.getString(cursor.getColumnIndex("sex"));
            bean.signature=cursor.getString(cursor.getColumnIndex("signature"));
            bean.name=cursor.getString(cursor.getColumnIndex("name"));
            bean.sno=cursor.getString(cursor.getColumnIndex("sno"));
        }
        cursor.close();
        return bean;
    }
    /**
     * 修改个人资料
     */
    public void updateUserInfo(String key,String value,String userName){
        ContentValues cv= new ContentValues();
        cv.put(key,value);
        db.update(SQLiteHelper.U_USERINFO,cv,"userName=?",new String[]{userName});
    }
}

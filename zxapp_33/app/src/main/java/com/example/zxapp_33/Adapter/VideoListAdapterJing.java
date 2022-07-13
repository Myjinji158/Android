package com.example.zxapp_33.Adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.text.TextUtils;
import com.example.zxapp_33.activity_33.jingVideoPlayActivity;
import java.util.List;
import com.example.zxapp_33.R;
import com.example.zxapp_33.bean.VideoBeam;
import com.example.zxapp_33.utils.DBUtils;
import com.example.zxapp_33.utils.SharePreferencesUtils;

public class VideoListAdapterJing extends BaseAdapter {
    private Context jingContext;
    private List<VideoBeam> jing_vbl;//视频列表数据
    private int jing_selectedPosition = -1;//点击时选中的位置

    public VideoListAdapterJing(Context context){
        this.jingContext = context;
    }

    public void setSelectedPosition(int position){
        jing_selectedPosition = position;
    }

    /**
     * 设置数据更新界面
     */
    public void setData(List<VideoBeam> vbl) {
        this.jing_vbl = vbl;
        notifyDataSetChanged();
    }

    class ViewHolder {
        public TextView tv_title;
        public ImageView iv_icon;
    }

    @Override
    public int getCount() {
        return jing_vbl== null ? 0 : jing_vbl.size();
    }

    @Override
    public VideoBeam getItem(int position) {
        return jing_vbl==null ? null : jing_vbl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(jingContext).inflate(
                    R.layout.jing_video_list_item, null);
            vh.tv_title = (TextView) convertView
                    .findViewById(R.id.jing_tv_video_title);
            vh.iv_icon = (ImageView) convertView
                    .findViewById(R.id.jing_iv_left_icon);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final VideoBeam bean = getItem(position);
        Log.i("jimjim",""+position+"##"+bean.videoPath);
        vh.iv_icon.setImageResource(R.drawable.course_bar_icon);
        vh.tv_title.setTextColor(Color.parseColor("#333333"));
        if (bean != null) {
            //vh.tv_title.setText(bean.secondTitle);
            //设置选中效果
            if (jing_selectedPosition == position) {
                vh.iv_icon.setImageResource(R.drawable.course_intro_icon);
                vh.tv_title.setTextColor(Color.parseColor("#009958"));
            } else {
                vh.iv_icon.setImageResource(R.drawable.course_bar_icon);
                vh.tv_title.setTextColor(Color.parseColor("#333333"));
            }
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean == null)
                    return;
                //todo:播放视频
                String videoPath = bean.videoPath;
                if (TextUtils.isEmpty(videoPath)){
                    Toast.makeText(jingContext,
                            "本地没有此视频，暂无法播放",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    //判断用户是否登录，若登录则把此视频添加到数据库
                    if(SharePreferencesUtils.readLoginStatus(jingContext)){
                        String userName=SharePreferencesUtils.readLoginUserName(jingContext);
                        DBUtils db = DBUtils.getInstance(jingContext);
                        //db.saveVideoPlayList(bean,userName);
                    }
                    //跳转到视频播放界面
                    Intent intent=new Intent(jingContext,jingVideoPlayActivity.class);
                    intent.putExtra("videoPath",videoPath);
                    jingContext.startActivity(intent);
                }
            }
        });
        return convertView;
    }
}

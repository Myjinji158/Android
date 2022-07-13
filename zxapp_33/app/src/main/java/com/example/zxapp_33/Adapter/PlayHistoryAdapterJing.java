package com.example.zxapp_33.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import com.example.zxapp_33.activity_33.jingVideoPlayActivity;
import com.example.zxapp_33.bean.VideoBeam;
import com.example.zxapp_33.R;
public class PlayHistoryAdapterJing extends BaseAdapter {
    private Context jingContext;
    private List<VideoBeam> jing_vbl;
    private int[] icon={R.drawable.video_play_icon1,R.drawable.video_play_icon2,R.drawable.video_play_icon3, R.drawable.video_play_icon4};
    public PlayHistoryAdapterJing(Context context){
        this.jingContext =context;
    }

    /**
     * 设置数据源
     */
    public void setData(List<VideoBeam> vbl){
        this.jing_vbl =vbl;
        notifyDataSetChanged();
    }
    class ViewHolder{
        public TextView tv_title,tv_video_title;
        public ImageView iv_icon;
    }
    @Override
    public int getCount() {
        return jing_vbl == null ? 0 : jing_vbl.size();
    }

    @Override
    public VideoBeam getItem(int position) {
        return jing_vbl == null ? null : jing_vbl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        final ViewHolder vh;
        if (converView == null) {
            vh = new ViewHolder();
            converView = LayoutInflater.from(jingContext).inflate(
                    R.layout.jing_history_list_item, null);
            vh.tv_title = (TextView) converView.findViewById(R.id.jing_tv_adapter_title);
            vh.tv_video_title = (TextView) converView.findViewById(R.id.jing_tv_video_title);
            vh.iv_icon = (ImageView) converView.findViewById(R.id.jing_iv_video_icon);
            converView.setTag(vh);
        } else {
            vh = (ViewHolder) converView.getTag();
        }
        final VideoBeam bean = getItem(position);
        if (bean != null) {
            //vh.tv_title.setText(bean.title);
            //vh.tv_video_title.setText(bean.secondTitle);
            //vh.iv_icon.setImageResource(icon[bean.chapterId - 1]);
        }
        converView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean == null) return;
                //跳转到播放视频界面
                Intent intent = new Intent(jingContext, jingVideoPlayActivity.class);
                intent.putExtra("videoPath", bean.videoPath);
                jingContext.startActivity(intent);
            }
        });
        return converView;
    }
}

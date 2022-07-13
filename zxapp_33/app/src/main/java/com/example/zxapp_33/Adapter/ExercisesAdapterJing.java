package com.example.zxapp_33.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import com.example.zxapp_33.bean.ExercisesBean;
import com.example.zxapp_33.R;
import com.example.zxapp_33.activity_33.jingVideoPlayActivity;
public class ExercisesAdapterJing extends  BaseAdapter{
    private Context jing_mContext;
    private List<ExercisesBean> jing_ebl;
    /**
     *构造函数
     */
    public ExercisesAdapterJing(Context context){
        this.jing_mContext = context;
    }
    /**
     *设置数据来源
     */
    public void setData(List<ExercisesBean> ebl){
        this.jing_ebl = ebl;
        notifyDataSetChanged();
    }
    class ViewHolder {
        public TextView title, content;
        public TextView order;
    }

    /**
     * 获取Item的总数
     */
    @Override
    public int getCount(){
        return jing_ebl == null ? 0 :jing_ebl.size();
    }
    /**
     *根据position得到对应Item的对象
     */
    @Override
    public ExercisesBean getItem(int position){
        return jing_ebl == null ? null :jing_ebl.get(position);
    }
    /**
     *根据position得到对应Item的id
     */
    @Override
    public long getItemId(int position){
        return position ;
    }
    /**
     *得到相应position对应Item视图，position是当前Item的位置，
     * convertView参数就是滚出屏幕的Item的View
     */
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        final ViewHolder vh;
        //复用converView
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(jing_mContext).inflate(
                    R.layout.jing_exercises_list_item, null);
            vh.title = (TextView) convertView.findViewById(R.id.jing_tv_video_title);
            //vh.content = (TextView) convertView.findViewById(R.id.jing_tv_content);
            vh.order = (TextView) convertView.findViewById(R.id.jing_tv_order);
            convertView.setTag(vh);
        }else {
            vh =(ViewHolder)convertView.getTag();
        }
        //获取position对应的Item的数据对象
        final ExercisesBean bean = getItem(position);
        if (bean != null){
            //vh.order.setText(position + 1 + "");
            vh.title.setText(bean.title);
           // vh.content.setText(bean.content);
            vh.order.setBackgroundResource(bean.background);
        }
        //每个Item的点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean == null)
                    return;
                //todo:跳转到习题详情页面
                Intent intent=new Intent(jing_mContext,jingVideoPlayActivity.class);
                intent.putExtra("id",bean.id);
                intent.putExtra("title",bean.title);
                jing_mContext.startActivity(intent);
            }
        });
        return convertView;
    }
}

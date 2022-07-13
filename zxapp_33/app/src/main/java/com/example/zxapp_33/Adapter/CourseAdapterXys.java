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
import com.example.zxapp_33.activity_33.jingVideoList;
import com.example.zxapp_33.bean.CourseBean;
import com.example.zxapp_33.R;
import com.example.zxapp_33.activity_33.xysExercisesDetail;
import com.example.zxapp_33.activity_33.jingVideoPlayActivity;

public class CourseAdapterXys extends BaseAdapter {
    private Context jingContext;
    private List<List<CourseBean>>jing_cbl;

    private int[] jing_iv={R.drawable.chapter_1_icon,R.drawable.chapter_2_icon,R.drawable.chapter_3_icon,
            R.drawable.chapter_4_icon};

    public CourseAdapterXys(Context context){
        this.jingContext = context;
    }

    /**
     * 设置数据来源
     */
    public void setData(List<List<CourseBean>> cbl){
        this.jing_cbl = cbl;
        notifyDataSetChanged();
    }

    class ViewHolder{
        public TextView tv_left_img_title,tv_right_img_title,tv_left_title,
                tv_right_title,tv_left_titles, tv_right_titles;
        public ImageView iv_left_img,iv_right_img,iv_left_imgs,iv_right_imgs;
    }
    @Override
    public int getCount() {
        return jing_cbl == null ? 0 : jing_cbl.size();
    }

    @Override
    public List<CourseBean> getItem(int position) {
        return jing_cbl == null ? null : jing_cbl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(jingContext).inflate(R.layout.xys_course_list_item,null);
            vh.iv_left_img =(ImageView)convertView.findViewById(R.id.xys_iv_left_img);
            vh.iv_right_img =(ImageView)convertView.findViewById(R.id.xys_iv_right_img);
            vh.iv_left_imgs =(ImageView)convertView.findViewById(R.id.xys_iv_left_imgs);
            vh.iv_right_imgs =(ImageView)convertView.findViewById(R.id.xys_iv_right_imgs);
            //vh.tv_left_img_title =(TextView) convertView.findViewById(R.id.jing_tv_left_img_title);
            //vh.tv_right_img_title =(TextView) convertView.findViewById(R.id.jing_tv_right_img_title);
            vh.tv_left_title =(TextView) convertView.findViewById(R.id.xys_tv_left_title);
            vh.tv_right_title =(TextView) convertView.findViewById(R.id.xys_tv_right_title);
            vh.tv_left_titles =(TextView) convertView.findViewById(R.id.xys_tv_left_titles);
            vh.tv_right_titles =(TextView) convertView.findViewById(R.id.xys_tv_right_titles);
            convertView.setTag(vh);
        }else {
            //复用convetView
            vh = (ViewHolder) convertView.getTag();
        }
        final List<CourseBean>list = getItem(position);
        if (list != null){
            for (int i = 0;i < list.size(); i++){
                final CourseBean bean = list.get(i);
                switch (i){
                    case 0://设置第一张图片与标题信息
                        //vh.tv_left_img_title.setText(bean.imgTitle);
                        vh.tv_left_title.setText(bean.title);
                        vh.iv_left_img.setImageResource(jing_iv[bean.id-1]);
                        vh.iv_left_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //todo:跳转到第一个界面
                                Intent intent=new Intent(jingContext, xysExercisesDetail.class);
                                intent.putExtra("id",bean.id);
                                intent.putExtra("intro",bean.intro);
                                jingContext.startActivity(intent);
                            }
                        });
                        break;
                    case 1://设置第二张图片与标题信息
                        //vh.tv_right_img_title.setText(bean.imgTitle);
                        vh.tv_right_title.setText(bean.title);
                        vh.iv_right_img.setImageResource(jing_iv[bean.id-1]);
                        vh.iv_right_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //todo:跳转到第二个界面
                                Intent intent=new Intent(jingContext, xysExercisesDetail.class);
                                intent.putExtra("id",bean.id);
                                intent.putExtra("intro",bean.intro);
                                jingContext.startActivity(intent);
                            }
                        });
                        break;
                    case 2://设置第三张图片与标题信息
                        //vh.tv_right_img_title.setText(bean.imgTitle);
                        vh.tv_left_titles.setText(bean.title);
                        vh.iv_left_imgs.setImageResource(jing_iv[bean.id-1]);
                        vh.iv_left_imgs.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //todo:跳转到第三个界面
                                Intent intent=new Intent(jingContext,jingVideoList.class);
                                intent.putExtra("id",bean.id);
                                intent.putExtra("intro",bean.intro);
                                jingContext.startActivity(intent);
                            }
                        });
                        break;
                    case 3://设置第四张图片与标题信息
                        //vh.tv_right_img_title.setText(bean.imgTitle);
                        vh.tv_right_titles.setText(bean.title);
                        vh.iv_right_imgs.setImageResource(jing_iv[bean.id-1]);
                        vh.iv_right_imgs.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //todo:跳转到第四个界面
                                Intent intent=new Intent(jingContext, jingVideoPlayActivity.class);
                                intent.putExtra("id",bean.id);
                                intent.putExtra("intro",bean.intro);
                                jingContext.startActivity(intent);
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        }
        return convertView;
    }
}

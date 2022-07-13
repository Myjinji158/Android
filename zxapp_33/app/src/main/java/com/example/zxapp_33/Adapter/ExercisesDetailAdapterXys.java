package com.example.zxapp_33.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import com.example.zxapp_33.bean.ExercisesDetailBean;
import com.example.zxapp_33.R;
public class ExercisesDetailAdapterXys extends BaseAdapter {
    private Context jing_mContext;
    private List<ExercisesDetailBean> jing_ebl;
    private int[] jing_iv = {R.drawable.exercises_a, R.drawable.exercises_b, R.drawable.exercises_c,
            R.drawable.exercises_d};
    private ArrayList<String> selectedPosition= new ArrayList<String>();//记录点击的位置

    public ExercisesDetailAdapterXys(Context context) {
        this.jing_mContext = context;
    }

    public void setData(List<ExercisesDetailBean> ebl) {
        this.jing_ebl = ebl;
        notifyDataSetChanged();
    }

    class ViewHolder {
        public TextView subject, tv_a, tv_b, tv_c, tv_d;
        public ImageView[] iv = new ImageView[4];
    }
    /**
     * 设置A、B、C、D选项是否可点击
     */
    public void setABCDEnable(boolean value, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
        iv_a.setEnabled(value);
        iv_b.setEnabled(value);
        iv_c.setEnabled(value);
        iv_d.setEnabled(value);
    }
    public void setABCDDefault(ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
        iv_a.setImageResource(jing_iv[0]);
        iv_b.setImageResource(jing_iv[1]);
        iv_c.setImageResource(jing_iv[2]);
        iv_d.setImageResource(jing_iv[3]);
    }
    /**
     * 获取Item的总数
     */
    @Override
    public int getCount() {
        return jing_ebl == null ? 0 : jing_ebl.size();
    }

    @Override
    public ExercisesDetailBean getItem(int position) {
        return jing_ebl == null ? null : jing_ebl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(jing_mContext).inflate(R.layout.xys_exercises_detail_list_item, null);
            vh.subject = (TextView) convertView.findViewById(R.id.xys_tv_subject);
            vh.tv_a = (TextView) convertView.findViewById(R.id.xys_tv_a);
            vh.tv_b = (TextView) convertView.findViewById(R.id.xys_tv_b);
            vh.tv_c = (TextView) convertView.findViewById(R.id.xys_tv_c);
            vh.tv_d = (TextView) convertView.findViewById(R.id.xys_tv_d);
            vh.iv[0] = (ImageView) convertView.findViewById(R.id.xys_iv_a);
            vh.iv[1] = (ImageView) convertView.findViewById(R.id.xys_iv_b);
            vh.iv[2] = (ImageView) convertView.findViewById(R.id.xys_iv_c);
            vh.iv[3] = (ImageView) convertView.findViewById(R.id.xys_iv_d);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final ExercisesDetailBean bean = getItem(position);
        if (bean != null){
            vh.subject.setText(bean.subject);
            vh.tv_a.setText(bean.a);
            vh.tv_b.setText(bean.b);
            vh.tv_c.setText(bean.c);
            vh.tv_d.setText(bean.d);
        }
        if (!selectedPosition.contains("" + position)){
            setABCDDefault( vh.iv[0], vh.iv[1], vh.iv[2] ,vh.iv[3]);
            setABCDEnable(true,vh.iv[0],vh.iv[1],vh.iv[2],vh.iv[3]);
        } else {
            setABCDEnable(false, vh.iv[0], vh.iv[1], vh.iv[2], vh.iv[3]);
            setABCDDefault( vh.iv[0], vh.iv[1], vh.iv[2], vh.iv[3]);
            if (bean.select==bean.answer){
                vh.iv[bean.answer-1].setImageResource(R.drawable.exercises_right_icon);
            }
            else {
                vh.iv[bean.answer-1].setImageResource(R.drawable.exercises_right_icon);
                vh.iv[bean.select-1].setImageResource(R.drawable.exercises_error_icon);
            }
        }
        //当用户点击A选项的点击事件
        vh.iv[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition.contains(""+position)){
                    selectedPosition.remove(""+position);
                } else {
                    selectedPosition.add(position+"");
                }
                jing_ebl.get(position).select =1;
                if (jing_ebl.get(position).answer == 1){
                    vh.iv[0].setImageResource(R.drawable.exercises_right_icon);
                } else {
                    vh.iv[bean.answer - 1].setImageResource(R.drawable.exercises_right_icon);
                    vh.iv[0].setImageResource(R.drawable.exercises_error_icon);
                }
                setABCDEnable(false, vh.iv[0], vh.iv[1], vh.iv[2], vh.iv[3]);
            }
        });
        //当用户点击B 选项的点击事件
        vh.iv[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition.contains(""+position)){
                    selectedPosition.remove(""+position);
                }else {
                    selectedPosition.add(position+"");
                }
                jing_ebl.get(position).select =2;
                if (jing_ebl.get(position).answer == 2){
                    vh.iv[1].setImageResource(R.drawable.exercises_right_icon);
                }else {
                    vh.iv[bean.answer - 1].setImageResource(R.drawable.exercises_right_icon);
                    vh.iv[1].setImageResource(R.drawable.exercises_error_icon);
                }
                setABCDEnable(false, vh.iv[0], vh.iv[1], vh.iv[2], vh.iv[3]);
            }
        });
        //当用户点击C 选项的点击事件
        vh.iv[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition.contains("" + position)){
                    selectedPosition.remove(""+ position);
                }else {
                    selectedPosition.add(position + "");
                }
                jing_ebl.get(position).select =3;
                if (jing_ebl.get(position).answer ==3){
                    vh.iv[2].setImageResource(R.drawable.exercises_right_icon);
                }else {
                    vh.iv[bean.answer - 1].setImageResource(R.drawable.exercises_right_icon);
                    vh.iv[2].setImageResource(R.drawable.exercises_error_icon);
                }
                setABCDEnable(false, vh.iv[0], vh.iv[1], vh.iv[2], vh.iv[3]);
            }
        });
        //当用户点击D 选项的点击事件
        vh.iv[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition.contains("" + position)){
                    selectedPosition.remove(""+position);
                }else {
                    selectedPosition.add(position+"");
                }
                jing_ebl.get(position).select =4;
                if (jing_ebl.get(position).answer ==4){
                    vh.iv[3].setImageResource(R.drawable.exercises_right_icon);
                }else {
                    vh.iv[bean.answer - 1].setImageResource(R.drawable.exercises_right_icon);
                    vh.iv[3].setImageResource(R.drawable.exercises_error_icon);
                }
                setABCDEnable(false, vh.iv[0], vh.iv[1], vh.iv[2], vh.iv[3]);
            }
        });
        return convertView;
    }
}

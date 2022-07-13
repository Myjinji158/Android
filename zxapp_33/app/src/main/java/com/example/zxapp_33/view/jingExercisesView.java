package com.example.zxapp_33.view;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import com.example.zxapp_33.Adapter.ExercisesAdapterJing;
import com.example.zxapp_33.bean.ExercisesBean;
import com.example.zxapp_33.R;
public class jingExercisesView {
    private ListView jing_lv_list;
    private ExercisesAdapterJing jing_adapter;
    private List<ExercisesBean>jing_ebl;
    private Activity jing_mContent;
    private LayoutInflater jing_mInflater;
    private View jing_mCurrentView;
    private String[] jing_title={"新手学车技巧","驾考新规则","新手曲线行驶详细操作步骤","技巧讲解学车考试"};
    private int[] jing_content={5,5,5,5};
    private int[] backgrount={R.drawable.exercises_bg_1,R.drawable.exercises_bg_2,R.drawable.exercises_bg_3,
            R.drawable.exercises_bg_4};


    /**
     * 构造函数
     * @param content
     */
    public jingExercisesView(Activity content){
        jing_mContent = content;
        //为之后将Layout转化为view时用
        jing_mInflater = LayoutInflater.from(jing_mContent);
    }
    /**
     * 初始化数据
     */
    private void initData(){
        jing_ebl = new ArrayList<ExercisesBean>();
        for (int i =0;i < jing_title.length;i++){
            ExercisesBean bean = new ExercisesBean();
            bean.id = (i + 1);
            bean.title=jing_title[i];
            bean.content="共计"+jing_content[i]+"题";
            bean.background=backgrount[i];
            jing_ebl.add(bean);
        }
    }
    /**
     * 初始化界面
     */
    private void initView(){
        jing_mCurrentView = jing_mInflater.inflate(R.layout.main_view_exercises_jing,null);
        jing_lv_list =(ListView)jing_mCurrentView.findViewById(R.id.jing_lv_list);
        jing_adapter =new ExercisesAdapterJing(jing_mContent);
        initData();
        jing_adapter.setData(jing_ebl);
        jing_lv_list.setAdapter(jing_adapter);
    }
    /**
     *创建界面
     */
    private void createView(){
        initView();
    }
    /**
     *获取当前在导航栏上方显示对应的View
     */
    public View getView(){
        if (jing_mCurrentView == null){
            createView();
        }
        return jing_mCurrentView;
    }
    /**
     *显示当前导航栏上方所对应的view界面
     */
    public void showView(){
        if (jing_mCurrentView == null){
            createView();
        }
        jing_mCurrentView.setVisibility(View.VISIBLE);
    }
}

package com.example.zxapp_33.utils;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Xml;
import android.widget.ImageView;
import com.example.zxapp_33.bean.ExercisesDetailBean;
import org.xmlpull.v1.XmlPullParser;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.example.zxapp_33.bean.CourseBean;
public class XMLUtils {
    /**
     * 解析每章的习题
     */
    public static List<ExercisesDetailBean> getExercisesInfos(InputStream is)throws Exception{
        XmlPullParser parser= Xml.newPullParser();
        parser.setInput(is,"utf-8");
        List<ExercisesDetailBean> exercisesInfos=null;
        ExercisesDetailBean exercisesInfo=null;
        int type=parser.getEventType();
        while (type!=XmlPullParser.END_DOCUMENT){
            switch (type){
                case XmlPullParser.START_TAG:
                if ("infos".equals(parser.getName())){
                    exercisesInfos=new ArrayList<ExercisesDetailBean>();
                }else if ("exercises".equals(parser.getName())){
                    exercisesInfo=new ExercisesDetailBean();
                    String ids=parser.getAttributeValue(0);
                    exercisesInfo.subjectId=Integer.parseInt(ids);
                }else if ("subject".equals(parser.getName())){
                    String subject=parser.nextText();
                    exercisesInfo.subject=subject;
                }else if ("a".equals(parser.getName())){
                    String a=parser.nextText();
                    exercisesInfo.a=a;
                }else if ("b".equals(parser.getName())){
                    String b=parser.nextText();
                    exercisesInfo.b=b;
                }else if ("c".equals(parser.getName())){
                    String c=parser.nextText();
                    exercisesInfo.c=c;
                }else if ("d".equals(parser.getName())){
                    String d=parser.nextText();
                    exercisesInfo.d=d;
                }else if ("answer".equals(parser.getName())){
                    String answer=parser.nextText();
                    exercisesInfo.answer=Integer.parseInt(answer);
                }
                break;
            case XmlPullParser.END_TAG:
                if ("exercises".equals(parser.getName())){
                    exercisesInfos.add(exercisesInfo);
                    exercisesInfo=null;
                }
                break;
            }
            type=parser.next();
        }
        return  exercisesInfos;
    }
    /**
     * 解析每章的课程视频信息
     */
    public static List<List<CourseBean>> getCourseInfos(InputStream is)throws Exception{
        XmlPullParser parser= Xml.newPullParser();
        parser.setInput(is,"utf-8");
        List<List<CourseBean>> courseInfos=null;
        List<CourseBean> courseList=null;
        CourseBean courseInfo=null;
        int count=0;
        int type=parser.getEventType();
        while (type!=XmlPullParser.END_DOCUMENT){
            switch (type){
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())){
                        courseInfos=new ArrayList<List<CourseBean>>();
                        courseList=new ArrayList<CourseBean>();
                    }else if ("course".equals(parser.getName())){
                        courseInfo=new CourseBean();
                        String ids=parser.getAttributeValue(0);
                        courseInfo.id=Integer.parseInt(ids);
                    }//else if ("imgtitle".equals(parser.getName())){
                        //String imgtitle=parser.nextText();
                        //courseInfo.imgTitle=imgtitle;}
                    else if ("title".equals(parser.getName())){
                        String title=parser.nextText();
                        courseInfo.title=title;
                    }else if ("intro".equals(parser.getName())){
                        String intro=parser.nextText();
                        courseInfo.intro=intro;
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("course".equals(parser.getName())){
                        count++;
                        courseList.add(courseInfo);
                        if (count%4==0){//课程界面每两个数据是一组放在List集合中
                            courseInfos.add(courseList);
                            courseList=null;
                            courseList=new ArrayList<CourseBean>();
                        }
                        courseInfo=null;
                    }
                    break;
            }
            type=parser.next();
        }
        return  courseInfos;
    }

}

package com.example.zxapp_33.bean;

public class ExercisesDetailBean {
    public int id;//每章习题id
    public int subjectId;//每道习题的id
    public String subject;//每道习题的题干
    public String a;//每道题的A选项
    public String b;//每道题的B选项
    public String c;//每道题的C选项
    public String d;//每道题的D选项
    public int answer;//每道题的正确答案
    public int select;//用户选中的那项（1表示选择A选项，2表示选择B选项，13表示选择C选项，4表示选择D选项，）
}

package com.sms.utils;

import com.sms.dto.CascaderDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SIDUtil {
    //学院键值对 名字：编号
    public static HashMap<String,String> collegeNum = new HashMap<>();
    //专业键值对 名字：编号
    public static HashMap<String,String> majorNum = new HashMap<>();

    //专业键值对 名字：id
    public static HashMap<String, Integer> majorList = new HashMap<>();

    //后端批量插入时辅助生成sid
    public static int count = 0;

    public static void initParam(Map data){
        List<CascaderDto> major = (List<CascaderDto>) data.get("major");
        List<CascaderDto> college = (List<CascaderDto>) data.get("college");
        for (CascaderDto cascaderDto:college) {
            collegeNum.put(cascaderDto.getName(),cascaderDto.getId());
        }
        for (CascaderDto cas:major) {
            majorNum.put(cas.getName(),cas.getMajorNum());
        }
        for(CascaderDto cas:major){
            majorList.put(cas.getName(), Integer.valueOf(cas.getId()));
        }
    }

    public static String returnGrade(String temp){
        String grade;
        switch (temp){
            case "1" :
                grade =  "一班";
                break;
            case "2" :
                grade =  "二班";
                break;
            case "3" :
                grade =  "三班";
                break;
            case "4" :
                grade =  "四班";
                break;
            case "5" :
                grade =  "五班";
                break;
            default:
                grade = null;
        }
        return grade;
    }



}

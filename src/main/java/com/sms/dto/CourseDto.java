package com.sms.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class CourseDto {
    private Integer id;//开课编号
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;//课程开始时间
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;//课程结束时间
    private Integer classHour;//课时
    private String testMode;//考试模式
    private Integer studentNum;//学生选课上限
    private Integer complete;//结课标志 1表示结课，0表示未结课
    private Integer choiceNum;//选课人数
    private String tId;//开课老师id
    private Integer baseCourseId;//基础课程id
    private String courseName;//课程名
    private String teacherName;//教师名
    private String majorId;//所属专业id
    private String majorName;//所属专业名
    private String result;//结果
    private Float score;//成绩

    public Integer getBaseCourseId() {
        return baseCourseId;
    }

    public void setBaseCourseId(Integer baseCourseId) {
        this.baseCourseId = baseCourseId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public CourseDto() {
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getClassHour() {
        return classHour;
    }

    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    public String getTestMode() {
        return testMode;
    }

    public void setTestMode(String testMode) {
        this.testMode = testMode;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public Integer getChoiceNum() {
        return choiceNum;
    }

    public void setChoiceNum(Integer choiceNum) {
        this.choiceNum = choiceNum;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}

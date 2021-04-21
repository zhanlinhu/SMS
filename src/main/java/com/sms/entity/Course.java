package com.sms.entity;



public class Course {
    private Integer id;//开课编号
    private String startDate;//课程开始时间
    private String endDate;//课程结束时间
    private Integer classHour;//课时
    private String testMode;//考试模式
    private Integer studentNum;//学生选课上限
    private Integer complete;//结课标志 1表示结课，0表示未结课
    private Integer choiceNum;//选课人数
    private String tId;//开课老师id
    private Integer baseCourseId;//基础课程id
    private Integer majorId;//开课专业ID

    public Course() {
    }

    public Course(Integer id, String startDate, String endDate, Integer classHour, String testMode, Integer studentNum, Integer complete, Integer choiceNum, String tId, Integer baseCourseId, Integer majorId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.classHour = classHour;
        this.testMode = testMode;
        this.studentNum = studentNum;
        this.complete = complete;
        this.choiceNum = choiceNum;
        this.tId = tId;
        this.baseCourseId = baseCourseId;
        this.majorId = majorId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

    public String getTId() {
        return tId;
    }

    public void setTId(String tId) {
        this.tId = tId;
    }

    public Integer getBaseCourseId() {
        return baseCourseId;
    }

    public void setBaseCourseId(Integer baseCourseId) {
        this.baseCourseId = baseCourseId;
    }
}
